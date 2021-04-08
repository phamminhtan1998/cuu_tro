package com.phamtan.cuu_tro.servie;

import com.phamtan.cuu_tro.common.enumeration.StatusBasic;
import com.phamtan.cuu_tro.dao.entity.News;
import com.phamtan.cuu_tro.dao.repo.NewsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsService {
    private final NewsRepo newsRepo;

    public List<News> getAll(){
        return newsRepo.findAll();
    }
    public List<News> getAllByAuthor(String author){
        return newsRepo.findAllByAuthor(author);
    }
    public List<News> getAllByGteDate(LocalDate startDate){
        return newsRepo.findAllByCreateDateAfter(startDate);
    }
    public List<News> getAllByLteDate(LocalDate endDate){
        return newsRepo.findAllByCreateDateBefore(endDate);
    }
    public List<News> getAllByWithin(LocalDate startDate,LocalDate endDate){
        return newsRepo.findAllByCreateDateBetween(startDate, endDate);
    }

    public News create(News news){
        News tmpNew = new News();
        try {
            tmpNew = newsRepo.save(news);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return tmpNew;
    }
    public News update(News news){
        News tmpNew = new News();
        try {
            if(newsRepo.findByTitleLike(news.getTitle())!=null){
                return tmpNew;
            }
            tmpNew = newsRepo.save(news);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return tmpNew;
    }
    public StatusBasic delete(News news){

        try {
            if(newsRepo.findByTitle(news.getTitle())!=null){
                newsRepo.delete(news);
                return StatusBasic.SUCCESSFUL;
            }
            else
                return StatusBasic.ERROR;
        }catch (Exception ex){
            ex.printStackTrace();
            return  StatusBasic.ERROR;
        }
    }

}
