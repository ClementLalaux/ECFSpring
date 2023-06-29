package com.example.demo.exception;

public class TournoiExistException extends Exception{
    public TournoiExistException(){
        super("Tournoi Exist Deja");
    }
}
