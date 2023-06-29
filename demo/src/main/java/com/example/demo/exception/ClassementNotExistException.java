package com.example.demo.exception;

public class ClassementNotExistException extends Exception{
    public ClassementNotExistException(){
        super("Classement not exist");
    }
}
