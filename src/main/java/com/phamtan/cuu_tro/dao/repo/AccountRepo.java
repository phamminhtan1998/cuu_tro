package com.phamtan.cuu_tro.dao.repo;

import com.phamtan.cuu_tro.dao.entity.Account;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountRepo extends MongoRepository<Account,String> {
    public Account findByAccountIdf(String accountIdf);
    public Account findByFullName(String fullName);
    public List<Account> findAllByCoordinatesNear(Point destination, Distance distance);
    public List<Account> findAllByDob(LocalDate dob);
}
