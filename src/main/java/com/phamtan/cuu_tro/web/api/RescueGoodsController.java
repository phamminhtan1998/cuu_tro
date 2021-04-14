package com.phamtan.cuu_tro.web.api;

import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.entity.RescueGoods;
import com.phamtan.cuu_tro.servie.RescueGoodService;
import com.phamtan.cuu_tro.web.dto.request.RescueGoodsDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rescue-goods")
public class RescueGoodsController {
    private final RescueGoodService rescueGoodService;
    private final ModelMapper modelMapper;
    @GetMapping
    public List<RescueGoods> getAll(){
        return rescueGoodService.getAll();
    }
    @GetMapping("/rescuer/{idRescuer}")
    public List<RescueGoods> getByIdRescuer(@PathVariable("idRescuer") String idRescuer){
        return rescueGoodService.getByIdRescuer(idRescuer);
    }
    @GetMapping("/{id}")
    public RescueGoods getById(@PathVariable("id") String id){
        return rescueGoodService.getById(id);
    }
    @GetMapping("/not-archived")
    public List<RescueGoods> getAllNotArchived(){
        return rescueGoodService.getByNotArchived();
    }
    @GetMapping("/archived")
    public List<RescueGoods> getAllArchived(){
        return rescueGoodService.getByArchived();
    }
    @GetMapping("/name/{name}")
    public List<RescueGoods> getAllArchived(@PathVariable("name") String name){
        return rescueGoodService.getByNameArea(name);
    }
    @PostMapping
    public RescueGoods create(@RequestBody RescueGoodsDTO rescueGoodsDTO){
        RescueGoods rescueGoods = new RescueGoods();
        modelMapper.map(rescueGoodsDTO,rescueGoods);
        return rescueGoodService.create(rescueGoods);
    }
    @PutMapping
    public RescueGoods update(@RequestBody RescueGoodsDTO rescueGoodsDTO){
        RescueGoods rescueGoods = new RescueGoods();
        modelMapper.map(rescueGoodsDTO,rescueGoods);
        return rescueGoodService.update(rescueGoods);
    }
    @DeleteMapping
    public StatusBasic delete(@RequestBody RescueGoodsDTO rescueGoodsDTO){
        RescueGoods rescueGoods = new RescueGoods();
        modelMapper.map(rescueGoodsDTO,rescueGoods);
        return rescueGoodService.delete(rescueGoods);
    }
}
