package com.phamtan.cuu_tro.web.api;

import com.phamtan.base.email.data_structure.EmailContentData;
import com.phamtan.base.email.request.EmailRequest;
import com.phamtan.base.enumeration.EmailEnum;
import com.phamtan.cuu_tro.common.enumeration.DisasterType;
import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.custom.CustomMongoQuery;
import com.phamtan.cuu_tro.dao.entity.Account;
import com.phamtan.cuu_tro.dao.entity.NaturalDisaster;
import com.phamtan.cuu_tro.servie.NaturalDisasterService;
import com.phamtan.cuu_tro.util.GeoJsonConvert;
import com.phamtan.cuu_tro.util.mail.GmailService;
import com.phamtan.cuu_tro.web.dto.request.DisasterReqDTO;
import com.phamtan.cuu_tro.web.dto.request.GeoJsonDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@AllArgsConstructor
@RequestMapping("/api/natural")
public class NaturalDisasterController {
    private final NaturalDisasterService disasterService;
    private final ModelMapper modelMapper;


    @GetMapping
    public List<NaturalDisaster> findAll(){
       return  disasterService.getAll();
    }
    @GetMapping("/{id}")
    public NaturalDisaster findById(@PathVariable("id")String id){
        return  disasterService.getById(id);
    }
    @PostMapping("/near")
    public List<NaturalDisaster> findNear(@Valid  @RequestBody GeoJsonDTO point){
        GeoJsonPoint desPoint =  GeoJsonConvert.convertLatLonToGeoPoint(point.getLon(),point.getLat());
        return  disasterService.getAllNear(desPoint,point.getDistance());
    }
    @GetMapping("/disasterType")
    public List<NaturalDisaster> findByDisasterType(@RequestParam DisasterType disasterType){
        return  disasterService.getAllByDisasterType(disasterType);
    }
    @PostMapping("/disasterTypeNear")
    public List<NaturalDisaster> findByDisasterTypeNear(@RequestParam("type") DisasterType disasterType,@RequestBody GeoJsonDTO point){
        GeoJsonPoint desPoint =  GeoJsonConvert.convertLatLonToGeoPoint(point.getLon(),point.getLat());
        return  disasterService.getAllByDisasterTypeAndDistance(desPoint,point.getDistance(),disasterType);
    }

    @PostMapping
    public NaturalDisaster create(@RequestBody DisasterReqDTO disasterReqDTO){
        NaturalDisaster disaster = new NaturalDisaster();
        modelMapper.map(disasterReqDTO,disaster);
        disaster.setCoordinate(GeoJsonConvert.convertLatLonToGeoPoint(disasterReqDTO.getLon(), disasterReqDTO.getLat()));
        disasterService.create(disaster);

        return disaster;
    }
    @PatchMapping
    public NaturalDisaster update(@RequestBody DisasterReqDTO disasterReqDTO){
        NaturalDisaster disaster = new NaturalDisaster();
        modelMapper.map(disasterReqDTO,disaster);
        disasterService.update(disaster);
        return disaster;
    }
    @DeleteMapping
    public StatusBasic delete(@RequestBody DisasterReqDTO disasterReqDTO){
        NaturalDisaster disaster = new NaturalDisaster();
        modelMapper.map(disasterReqDTO,disaster);
       return disasterService.delete(disaster);

    }
}
