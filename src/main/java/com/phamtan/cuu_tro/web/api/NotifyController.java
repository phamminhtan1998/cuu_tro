package com.phamtan.cuu_tro.web.api;

import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.entity.Notify;
import com.phamtan.cuu_tro.servie.NotifyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notify")
public class NotifyController {
    private final NotifyService notifyService;
    @GetMapping
    public List<Notify> getAll(){
        return notifyService.getAll();
    }
    @PostMapping
    public Notify create(Notify notify){
      return   notifyService.create(notify);
    }
    @PutMapping
    public Notify update(Notify notify){
        return   notifyService.update(notify);
    }
    @DeleteMapping
    public StatusBasic delete(Notify notify){
        return   notifyService.delete(notify);
    }

}
