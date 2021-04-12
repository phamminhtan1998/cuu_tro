package com.phamtan.cuu_tro.dao.entity;

import com.phamtan.cuu_tro.common.enumeration.NewWayType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class NewWay extends BaseEntity{
    @Id
    private String id;
    private String name;
    private String description;
    private String idPerson;
    private String location;
    private NewWayType wayType;
    private List<GeoJsonPoint> wayCoordinate;
}
