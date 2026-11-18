package com.dataman.base.exception;

/**
 * Business/validation exception. Same idea as the legacy DmException: an error number
 * (used by the client to focus a field) and a user-visible message.
 */
public class DmException extends RuntimeException {

    private final int errorNumber;

    public DmException(int errorNumber, String message) {
        super(message);
        this.errorNumber = errorNumber;
    }

    public DmException(String message) {
        this(0, message);
    }

    public int getErrorNumber() {
        return errorNumber;
    }
}
