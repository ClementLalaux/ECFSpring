package com.example.demo.service;

import com.example.demo.entity.Joueur;

public interface LoginService {

    public boolean login(Joueur joueur);
    public boolean isLogged();
    public boolean isAdmin();
    public int getUserId();

}
