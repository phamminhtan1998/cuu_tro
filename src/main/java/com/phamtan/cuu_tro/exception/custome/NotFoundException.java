package com.phamtan.cuu_tro.exception.custome;

import org.springframework.data.crossstore.ChangeSetPersister;

public class NotFoundException extends ChangeSetPersister.NotFoundException {
    public NotFoundException() {
    }
}
