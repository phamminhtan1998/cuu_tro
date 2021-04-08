package com.phamtan.cuu_tro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.geo.GeoJsonModule;
import org.springframework.data.web.config.SpringDataJacksonModules;
@Configuration
public class GeoJsonConfiguration implements SpringDataJacksonModules {
    @Bean
    public GeoJsonModule geoJsonSerializers() {
        return new GeoJsonModule();
    }
}
