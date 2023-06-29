package com.example.demo.exception;

public class PartieNotExistException extends Exception{
    public PartieNotExistException(){
        super("Partie not exist");
    }
}
