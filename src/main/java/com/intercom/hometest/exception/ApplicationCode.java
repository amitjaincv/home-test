package com.intercom.hometest.exception;

public enum ApplicationCode implements ErrorCode {
    ENTITY_ALREADY_EXIST("1001", "Entity already exists."),
    ENTITY_AS_STRING_NOT_JSON_FORMATTED("1002", "String not JSON formatted."),
    FILE_NOT_FOUND("1003", "File not found."),
    UNEXPECTED_ERROR_IN_READING_FILE("1004", "Unexpected error in reading file."),
    CUSTOMER_NAME_IS_EMPTY_OR_NULL("1005", "Customer name should not be null.", ErrorCategory.VALIDATION),
    MISSING_ATTRIBUTE_IN_JSONOBJECT("1006", "Missing attribute in JSON object.", ErrorCategory.VALIDATION),
    WRONG_VALUE_FOR_NAME_ATTRIBUTE_IN_JSONOBJECT("1007", "Incorrect value of name attribute in JSON object.",
            ErrorCategory.VALIDATION),
    WRONG_VALUE_FOR_USER_ID_ATTRIBUTE_IN_JSONOBJECT("1008", "Incorrect value of user_id attribute in JSON object.",
            ErrorCategory.VALIDATION),
    WRONG_VALUE_FOR_LONGITUDE_OR_LATITUDE_ATTRIBUTE_IN_JSONOBJECT("1009",
            "Incorrect value of longitude or lotitude attribute in JSON object.", ErrorCategory.VALIDATION);

    private final String id;
    private final String message;
    private final ErrorCategory category;

    ApplicationCode(String id, String message) {
        this.id = id;
        this.message = message;
        this.category = ErrorCategory.APPLICATION;
    }

    ApplicationCode(String id, String message, ErrorCategory errorCategory) {
        this.id = id;
        this.message = message;
        this.category = errorCategory;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public ErrorCategory category() {
        return category;
    }
}
