package com.intercom.hometest.exception;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;

    public ApplicationException(ErrorCode errorCode) {
        this(errorCode, null);
    }

    public ApplicationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.toString(), cause);
        this.errorCode = errorCode;
    }

}