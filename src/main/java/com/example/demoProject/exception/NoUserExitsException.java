package com.example.demoProject.exception;

public class NoUserExitsException extends RuntimeException{
    private String msg;

    public NoUserExitsException(){};

    public NoUserExitsException(String msg){
        super(msg);

        this.msg = msg;
    }
}
