package com.bank.management.system.exception;

public class CardAlreadyExistException extends RuntimeException{
    public CardAlreadyExistException(String message) {
        super(message);
    }
}
