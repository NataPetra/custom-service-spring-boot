package it.academy.exception;

public class ValidationUserException extends IllegalArgumentException{
    public ValidationUserException() {
    }
    public ValidationUserException(String message) {
        super(message);
    }
}
