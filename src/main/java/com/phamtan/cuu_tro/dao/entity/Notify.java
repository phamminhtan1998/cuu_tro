package com.phamtan.cuu_tro.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Notify extends BaseEntity{
    private String id;
    private String name;
    private String description;
    private Map<String,String> content;
}
