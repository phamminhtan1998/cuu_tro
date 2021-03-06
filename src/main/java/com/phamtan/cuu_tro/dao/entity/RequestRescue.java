package com.phamtan.cuu_tro.dao.entity;

import com.phamtan.cuu_tro.common.enumeration.DangerLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document()
public class RequestRescue extends BaseEntity{
    @Id
    private String id;
    private String description;
    private LocalDateTime time;
    private GeoJsonPoint coordinates;
    private String location;
    private String idPerson;
    private String message;
    private DangerLevel level;

}
