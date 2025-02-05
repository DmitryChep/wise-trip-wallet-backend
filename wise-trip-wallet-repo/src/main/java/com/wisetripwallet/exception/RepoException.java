package com.wisetripwallet.exception;

public class RepoException extends RuntimeException {
    public RepoException(String message) {
        super(message);
    }
    public RepoException(String massage, Throwable cause) {
      super(cause);
    }
}
