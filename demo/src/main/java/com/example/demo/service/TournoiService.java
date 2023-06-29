package com.example.demo.service;

import com.example.demo.entity.Tournoi;
import com.example.demo.exception.EmptyFieldsException;
import com.example.demo.exception.NotAdminException;
import com.example.demo.exception.NotSignInException;
import com.example.demo.exception.TournoiExistException;
import com.example.demo.repository.TournoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournoiService {

    @Autowired
    private TournoiRepository tournoiRepository;

    @Autowired
    private LoginService loginService;

    public boolean addTournoi(String nom, String statut) throws NotSignInException, NotAdminException, EmptyFieldsException, TournoiExistException {
        if(loginService.isLogged()){
            if(loginService.isAdmin()){
                if(nom != null && statut != null){
                    if(!tournoiRepository.existsTournoiByNom(nom)){
                        Tournoi tournoi = Tournoi.builder().nom(nom).statut(statut).build();
                        tournoiRepository.save(tournoi);
                        return tournoi.getId()>0;
                    }
                    throw new TournoiExistException();
                }
                throw EmptyFieldsException.with("nom","statut");
            }
            throw new NotAdminException();
        }
        throw new NotSignInException();
    }

    public boolean deleteTournoi(int id) throws NotAdminException, NotSignInException {
        if(loginService.isLogged()){
            if(loginService.isAdmin()){
                if(tournoiRepository.findById(id).get() != null){
                    Tournoi tournoi = tournoiRepository.findById(id).get();
                    tournoiRepository.delete(tournoi);
                    return true;
                }
            }
            throw new NotAdminException();
        }
        throw new NotSignInException();
    }

    public List<Tournoi> getTournois() throws NotSignInException {
        if(loginService.isLogged()){
            return (List<Tournoi>) tournoiRepository.findAll();
        }
        throw new NotSignInException();
    }

    public Tournoi getTournoiById(int id) throws NotSignInException {
        if(loginService.isLogged()){
            return tournoiRepository.findById(id).get();
        }
        throw new NotSignInException();
    }

}
