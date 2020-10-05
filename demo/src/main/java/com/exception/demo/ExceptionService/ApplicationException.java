package com.exception.demo.ExceptionService;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Error error;
    private String debugMessage;
    private HttpStatus httpStatus;

    public ApplicationException(HttpStatus httpStatus, Error error) {
        super(error.getMessage(), null);
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public ApplicationException(HttpStatus httpStatus, String debugMessage) {
        super(debugMessage, null);
        this.httpStatus = httpStatus;
        this.debugMessage = debugMessage;
    }

    public ApplicationException(HttpStatus httpStatus, Error error, String debugMessage) {
        super(error.getMessage(), null);
        this.httpStatus = httpStatus;
        this.error = error;
        this.debugMessage = debugMessage;
    }


}
