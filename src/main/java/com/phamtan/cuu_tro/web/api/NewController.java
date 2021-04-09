package com.phamtan.cuu_tro.web.api;

import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.entity.News;
import com.phamtan.cuu_tro.servie.NewsService;
import com.phamtan.cuu_tro.web.dto.request.NewsDto;
import com.phamtan.cuu_tro.web.dto.response.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/news")
public class NewController {
    private final NewsService newsService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<News>>findAll(){
        return new ResponseEntity<>( newsService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/authors/{name}")
    public ResponseEntity<List<News>>findAllByAuthor(@PathVariable("name") String name){
        return new ResponseEntity<>( newsService.getAllByAuthor(name),HttpStatus.OK);
    }
    @GetMapping("/date_gte")
    public ResponseEntity<List<News>>findAllByDateGte(@RequestParam("date") LocalDate date){
        return new ResponseEntity<>( newsService.getAllByGteDate(date),HttpStatus.OK);
    }
    @GetMapping("/date_lte")
    public ResponseEntity<List<News>>findAllByDateLte(@RequestParam("date") LocalDate date){
        return new ResponseEntity<>( newsService.getAllByLteDate(date),HttpStatus.OK);
    }
    @GetMapping("/date")
    public ResponseEntity<List<News>>findAllByDateWithin(@RequestParam("startDate") LocalDate startDate,@RequestParam("endDate") LocalDate endDate){
        return new ResponseEntity<>( newsService.getAllByWithin(startDate,endDate),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<NewsDto> create(@Valid @RequestBody NewsDto newsDto) {
        News news = new News();
        modelMapper.map(newsDto, news);
        try {
            newsService.create(news);
            return new ResponseEntity<>(newsDto, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping
    public ResponseEntity<NewsDto> update(@Valid @RequestBody NewsDto newsDto) {
        News news = new News();
        modelMapper.map(newsDto, news);
        try {
            newsService.update(news);
            return new ResponseEntity<>(newsDto, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
    @DeleteMapping
    public ResponseEntity<StatusBasic> delete(@Valid @RequestBody NewsDto newsDto) {
        News news = new News();
        modelMapper.map(newsDto, news);
        try {
            StatusBasic res = newsService.delete(news);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
