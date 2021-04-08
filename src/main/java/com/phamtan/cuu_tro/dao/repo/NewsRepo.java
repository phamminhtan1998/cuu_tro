package com.phamtan.cuu_tro.dao.repo;

import com.phamtan.cuu_tro.dao.entity.News;
import jdk.jfr.Registered;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface NewsRepo extends MongoRepository<News,String> {
    public News findByTitleLike(String title);
    public News findByTitle(String title);
    public List<News> findAllByAuthor(String author);
    public List<News> findAllByCreateDateAfter(LocalDate startDate);
    public List<News> findAllByCreateDateBefore(LocalDate startDate);
    public List<News> findAllByCreateDateBetween(LocalDate startDate,LocalDate endDate);
}
