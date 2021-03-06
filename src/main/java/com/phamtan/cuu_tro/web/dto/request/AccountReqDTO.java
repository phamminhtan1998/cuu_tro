package com.phamtan.cuu_tro.web.dto.request;

import com.phamtan.cuu_tro.common.enumeration.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountReqDTO {
    private String id;
    @NotNull
    @NotBlank
    private String fullName;
    @DateTimeFormat()
    private LocalDateTime dob;
    @NotNull
    @NotBlank
    private String location;
    @NotNull
    @NotBlank
    @NumberFormat
    private Double lat;
    @NotNull
    @NotBlank
    @NumberFormat
    private Double lon;
    @NotBlank
    @NotNull
    @NotEmpty
    private String accountIdf;
    private String password;
    private AccountType accountType;
    @NotNull
    @NotEmpty
    @NotBlank
    private String phoneNumber;
    private String playerId;
}
