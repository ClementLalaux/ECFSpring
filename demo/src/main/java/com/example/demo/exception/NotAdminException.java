package com.example.demo.exception;

public class NotAdminException extends Exception{
    public NotAdminException(){
        super("User not admin");
    }
}
