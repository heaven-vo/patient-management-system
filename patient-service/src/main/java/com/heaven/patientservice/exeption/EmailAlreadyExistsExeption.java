package com.heaven.patientservice.exeption;

public class EmailAlreadyExistsExeption extends RuntimeException{
    public EmailAlreadyExistsExeption(String message) { super(message);}
}
