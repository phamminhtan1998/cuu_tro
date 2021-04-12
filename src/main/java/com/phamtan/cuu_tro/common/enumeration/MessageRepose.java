package com.phamtan.cuu_tro.common.enumeration;

public enum MessageRepose {
    SUCCESSFUL("Yêu cầu của bạn thành công"),
    FAILURE("Yêu cầu của bạn thất bại");
    private final String message;

    MessageRepose(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
