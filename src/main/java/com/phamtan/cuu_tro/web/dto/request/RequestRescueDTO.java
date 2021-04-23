package com.phamtan.cuu_tro.web.dto.request;

import com.phamtan.cuu_tro.common.enumeration.DangerLevel;
import com.phamtan.cuu_tro.dao.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestRescueDTO extends BaseEntity {
    private String id;
    @NotNull
    @NotBlank
    @NotEmpty
    private String location;
    private String description;
    private LocalDateTime time ;
    @NotNull
    @NotBlank
    @NotEmpty
    @NumberFormat
    private double lat;
    @NotNull
    @NotBlank
    @NotEmpty
    @NumberFormat
    private double lon;
    @NotNull
    @NotBlank
    @NotEmpty
    private String idPerson;
    @NotNull
    @NotBlank
    @NotEmpty
    private String message;
    private DangerLevel level=DangerLevel.HIGH;
}
