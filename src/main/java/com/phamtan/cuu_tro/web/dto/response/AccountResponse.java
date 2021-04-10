package com.phamtan.cuu_tro.web.dto.response;

import com.phamtan.cuu_tro.common.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountResponse implements Serializable {
    private String id;
    private String fullName;
    private LocalDate dob;
    private String location;
    private GeoJsonPoint coordinates;
    private String accountIdf;
    private AccountType accountType;
}
