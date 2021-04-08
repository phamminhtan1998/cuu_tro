package com.phamtan.cuu_tro.dao.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

public abstract  class BaseEntity {
    @CreatedDate
    private LocalDate createDate;
    @CreatedBy
    private String createBy;
    @LastModifiedDate
    private LocalDate updateDate;
    @LastModifiedBy
    private String updateBy;
}
