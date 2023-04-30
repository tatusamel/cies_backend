package com.cies.cies_project.model.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String message)  {
        super(message);
    }
}
