package com.phamtan.cuu_tro.dao.entity;

import com.phamtan.cuu_tro.common.enumeration.UsualGoods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.Map;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RescueGoods extends BaseEntity {

    private String id ;
    private String idRescuer;
//  Specific short description for list goods
    private String nameArea;
    private Map<UsualGoods,Integer> coreGoods;
    private Map<String,Integer> additionalGoods;
    private boolean isArchived;
}
