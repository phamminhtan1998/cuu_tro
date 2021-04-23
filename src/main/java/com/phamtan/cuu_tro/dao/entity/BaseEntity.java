package com.phamtan.cuu_tro.dao.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract  class BaseEntity {
    @CreatedDate
    private LocalDateTime createDate;
    @CreatedBy
    private String createBy;
    @LastModifiedDate
    private LocalDateTime updateDate;
    @LastModifiedBy
    private String updateBy;
}
