
package com.exception.demo.ExceptionService;

import lombok.Getter;

@Getter
public enum Error {

    INVLID_INPUT(101, "invalid_input");

    private Integer statusCode;
    private String message;

    Error(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
