package com.jonnie254.book_network_2.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


public enum BusinessErrorCodes {

    NO_CODE(0, NOT_IMPLEMENTED, "No code provided"),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Incorrect current password"),
    NEW_PASSWORD_MISMATCH(301, BAD_REQUEST, "New password mismatch"),
    ACCOUNT_LOCKED(302, FORBIDDEN, "Account is locked"),
    ACCOUNT_DISABLED(303, FORBIDDEN, "Account is disabled"),
    BAD_CREDENTIALS(304, FORBIDDEN, "login credentials are invalid"),

    ;
    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

}
