package com.bank.management.system.exception;

public class IncorrectCardNumberException extends RuntimeException{
    public IncorrectCardNumberException(String message) {
        super(message);
    }
}
