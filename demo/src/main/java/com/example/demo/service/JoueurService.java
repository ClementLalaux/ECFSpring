package com.example.demo.service;


import com.example.demo.entity.Classement;
import com.example.demo.entity.Joueur;
import com.example.demo.exception.UserExistException;
import com.example.demo.exception.UserNotExistException;
import com.example.demo.repository.ClassementRepository;
import com.example.demo.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JoueurService {

    @Autowired
    private JoueurRepository joueurRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ClassementRepository classementRepository;

    public boolean inscription(String nom,String prenom,String mail,String mdp) throws UserExistException {
        try {
            joueurRepository.findByMail(mail);
            throw new UserExistException();
        } catch (Exception e){
            Joueur joueur = Joueur.builder().nom(nom).prenom(prenom).mail(mail).mdp(mdp).statut(false).build();
            joueurRepository.save(joueur);
            Classement classement = Classement.builder().score(0).joueur(joueur).build();
            classementRepository.save(classement);
            return joueur.getId()>0 && classement.getId()>0;
        }
    }

    public boolean connexion(String mail , String mdp) throws UserNotExistException {
        try {
            Joueur joueur = joueurRepository.findByMailAndMdp(mail,mdp);
            return loginService.login(joueur);
        } catch (Exception e){
            throw new UserNotExistException();
        }
    }

    public Optional<Joueur> findJoueurById(Integer id) throws UserNotExistException {
        try {
            Optional<Joueur> joueur = joueurRepository.findById(id);
            return joueur;
        } catch (Exception e){
            throw new UserNotExistException();
        }
    }

}
