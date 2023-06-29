package com.example.demo.service.impl;

import com.example.demo.entity.Joueur;
import com.example.demo.repository.JoueurRepository;
import com.example.demo.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    HttpSession httpSession;

    @Autowired
    JoueurRepository joueurRepository;

    @Override
    public boolean login(Joueur joueur) {
        httpSession.setAttribute("isLogged",true);
        httpSession.setAttribute("isAdmin",joueur.isStatut());
        httpSession.setAttribute("joueur",joueur.getId());
        return true;
    }

    @Override
    public boolean isLogged() {
        return httpSession.getAttribute("isLogged") != null && (boolean)httpSession.getAttribute("isLogged") == true;
    }

    @Override
    public boolean isAdmin() {
        return httpSession.getAttribute("isLogged") != null && (boolean)httpSession.getAttribute("isAdmin") == true;
    }

    @Override
    public int getUserId() {
        return (int)httpSession.getAttribute("userId");
    }



}
