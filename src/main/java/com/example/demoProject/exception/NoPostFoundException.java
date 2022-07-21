package com.example.demoProject.exception;

public class NoPostFoundException extends RuntimeException{
    private String msg;

    public NoPostFoundException(){};

    public NoPostFoundException(String msg){
        super(msg);

        this.msg = msg;
    }
}
