package com.phamtan.cuu_tro.dao.entity;

import com.phamtan.cuu_tro.common.enumeration.ImageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class Image {
    private String id;
    private String name;
    private String url;
    private String imagePath;
    private String description;
    private String idParent;
    private ImageType type;
    private String specifyType;
}
