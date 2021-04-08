package com.phamtan.cuu_tro.common.enumeration;

public enum AccountType {
    FACEBOOK("Facebook"),
    GMAIL("Gmail"),
    NORMAL("Normal"),
    ;


    private final String type;

    AccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
