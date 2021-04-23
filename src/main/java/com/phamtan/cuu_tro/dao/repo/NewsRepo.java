package com.phamtan.cuu_tro.dao.repo;

import com.phamtan.cuu_tro.dao.entity.News;
import jdk.jfr.Registered;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface NewsRepo extends MongoRepository<News,String> {
    public News findByTitleLike(String title);
    public News findByTitle(String title);
    public List<News> findAllByAuthor(String author);
    public List<News> findAllByCreateDateAfter(LocalDateTime startDate);
    public List<News> findAllByCreateDateBefore(LocalDateTime startDate);
    public List<News> findAllByCreateDateBetween(LocalDateTime startDate,LocalDateTime endDate);
}
