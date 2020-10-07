package org.tuv01.service;

public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException() {
        super("Email is already in use!");
    }
}
