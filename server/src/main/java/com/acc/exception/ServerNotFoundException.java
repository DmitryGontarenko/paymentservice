package com.acc.exception;

public class ServerNotFoundException extends RuntimeException {
    ServerNotFoundException(String[] args) {
        super("Port " + args + " is invalid" );
    }
}
