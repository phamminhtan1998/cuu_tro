package com.phamtan.cuu_tro.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CusExceptionHandle {
    @ExceptionHandler(IllegalArgumentException.class)
    public void notFoundHandler(){

    }
}
