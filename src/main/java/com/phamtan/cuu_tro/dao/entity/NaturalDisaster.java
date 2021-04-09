package com.phamtan.cuu_tro.dao.entity;

import com.phamtan.cuu_tro.common.enumeration.DisasterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class NaturalDisaster {
    @Id
    private String id;
    private String area;
    private Double lat;
    private GeoJsonPoint coordinate;
    private DisasterType disasterType;



}
