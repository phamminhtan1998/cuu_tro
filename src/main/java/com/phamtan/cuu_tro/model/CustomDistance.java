package com.phamtan.cuu_tro.model;

import com.phamtan.cuu_tro.common.enumeration.MetricType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomDistance {
    private MetricType type;
    private double value;

    public double getValueByMeter(){
        if(this.type.equals(MetricType.KM)) return this.value*1000;
        else if(this.type.equals(MetricType.MILE)) return this.value*1609.34;
        else return this.value;
    }
}
