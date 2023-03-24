package it.academy.exception;

public class AppUserNotFoundException extends RuntimeException {

    public AppUserNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}
