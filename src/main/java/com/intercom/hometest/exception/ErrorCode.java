package com.intercom.hometest.exception;

import java.io.Serializable;

public interface ErrorCode extends Serializable {
    String id();

    String message();

    ErrorCategory category();
}
