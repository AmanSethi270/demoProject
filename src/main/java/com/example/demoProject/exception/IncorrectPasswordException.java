package com.example.demoProject.exception;

public class IncorrectPasswordException extends RuntimeException{
    private String msg;

    public IncorrectPasswordException(){};

    public IncorrectPasswordException(String msg){
        super(msg);

        this.msg = msg;
    }
}
