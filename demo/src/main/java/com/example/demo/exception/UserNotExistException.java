package com.example.demo.exception;

public class UserNotExistException extends Exception{
    public UserNotExistException(){
        super("User not exist");
    }
}
