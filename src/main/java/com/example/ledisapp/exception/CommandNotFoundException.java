package com.example.ledisapp.exception;

public class CommandNotFoundException extends Exception{
    public CommandNotFoundException(String message) {
        super(message);
    }
}
