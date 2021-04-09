package com.phamtan.cuu_tro.web.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response<T> extends ResponseEntity<T> {

    public Response(T body, HttpStatus status) {
        super(body, status);
    }
}
