package com.example.demoProject.exception;

public class DoesNotFollowException extends RuntimeException{
    private String msg;

    public DoesNotFollowException(){};

    public DoesNotFollowException(String msg){
        super(msg);

        this.msg = msg;
    }
}
