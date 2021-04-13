package com.phamtan.cuu_tro.web.api;

import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.entity.RequestRescue;
import com.phamtan.cuu_tro.servie.RequestRescueService;
import com.phamtan.cuu_tro.util.GeoJsonConvert;
import com.phamtan.cuu_tro.web.dto.request.GeoJsonDTO;
import com.phamtan.cuu_tro.web.dto.request.RequestRescueDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/request-rescue")
public class RequestRescueController {

    private final  RequestRescueService requestRescueService;
    private final ModelMapper modelMapper;
    @GetMapping
    public List<RequestRescue> getAll(){
       return  requestRescueService.getAll();
    }
    @GetMapping("/person/{id}")
    public List<RequestRescue> getByIdPerson(@PathVariable("id")String id){
        return requestRescueService.getByPerson(id);
    }
    @PostMapping("/location")
    public List<RequestRescue> getByLocation(@RequestBody GeoJsonDTO geoJsonDTO){
        GeoJsonPoint point = GeoJsonConvert.convertLatLonToGeoPoint(geoJsonDTO.getLon(), geoJsonDTO.getLat());
        return requestRescueService.getByLocation(point, geoJsonDTO.getDistance());
    }
    @PostMapping
    public RequestRescue create(@RequestBody @Valid RequestRescueDTO requestRescueDTO){
        RequestRescue requestRescue = new RequestRescue();
        modelMapper.map(requestRescueDTO,requestRescue);
        requestRescue.setCoordinates(GeoJsonConvert.convertLatLonToGeoPoint(requestRescueDTO.getLon(),
                requestRescueDTO.getLat()));
        return  requestRescueService.create(requestRescue);
    }
    @PatchMapping
    public RequestRescue update(@RequestBody RequestRescue requestRescue){
        return  requestRescueService.update(requestRescue);
    }
    @DeleteMapping
    public StatusBasic delete(@RequestBody RequestRescue requestRescue){
        return  requestRescueService.delete(requestRescue);
    }
}
