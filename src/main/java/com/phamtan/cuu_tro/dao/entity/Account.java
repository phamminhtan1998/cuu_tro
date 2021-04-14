package com.phamtan.cuu_tro.dao.entity;


import com.phamtan.cuu_tro.common.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "account")

public class Account extends  BaseEntity implements Serializable {
    @Id
    private String id;
    private String fullName;
    private LocalDate dob;
    private String location;

    private GeoJsonPoint coordinates;
    private String accountIdf;
    private String password;
    private AccountType accountType;
    private String phoneNumber;
    private String playerId;

}
