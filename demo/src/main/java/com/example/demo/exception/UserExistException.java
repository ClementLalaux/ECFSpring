package com.example.demo.exception;

public class UserExistException extends Exception{
    public UserExistException(){
        super("User exist");
    }
}
