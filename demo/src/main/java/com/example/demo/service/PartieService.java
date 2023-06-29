package com.example.demo.service;

import com.example.demo.entity.Joueur;
import com.example.demo.entity.Partie;
import com.example.demo.entity.Tournoi;
import com.example.demo.exception.EmptyFieldsException;
import com.example.demo.exception.NotAdminException;
import com.example.demo.exception.NotSignInException;
import com.example.demo.exception.PartieNotExistException;
import com.example.demo.repository.PartieRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PartieService {

    @Autowired
    private LoginService loginService;

    @Autowired
    HttpSession httpSession;

    @Autowired
    private PartieRepository partieRepository;

    public boolean addPartie(Date date, Integer resultat, Joueur joueur1 , Joueur joueur2, Tournoi tournoi) throws NotAdminException, NotSignInException {
        if(loginService.isLogged()){
            if(loginService.isAdmin()){
                if(date == null || resultat == null || joueur1 == null || joueur2 == null || tournoi == null){
                    EmptyFieldsException.with("date","resultat","joueur1","joueur2","tournoi");
                }
                Partie partie = Partie.builder().date(date).resultat(resultat).joueur1(joueur1).joueur2(joueur2).partieTournois(tournoi).build();
                partieRepository.save(partie);
                if(partie.getId()>0){
                    return true;
                }
                return false;
            }
            throw new NotAdminException();
        }
        throw new NotSignInException();
    }

    public boolean updatePartie(int id,Integer resultat) throws NotAdminException, NotSignInException, EmptyFieldsException, PartieNotExistException {
        if(loginService.isLogged()){
            if(loginService.isAdmin()){
                if(resultat>=0 && resultat<=3){
                    try {
                        Partie partie = partieRepository.findById(id).get();
                        partie.setResultat(resultat);
                        partieRepository.save(partie);
                        return true;
                    } catch (Exception e){
                        throw new PartieNotExistException();
                    }
                }
                throw EmptyFieldsException.with("resultat");
            }
            throw new NotAdminException();
        }
        throw new NotSignInException();
    }

    public List<Partie> getParties() throws NotSignInException {
        if(loginService.isLogged()){
            return (List<Partie>) partieRepository.findAll();
        }
        throw new NotSignInException();
    }

    public List<Partie> getPartiesByIdJoueur(Joueur joueur) throws NotSignInException {
        if(loginService.isLogged()){
            return partieRepository.findByJoueur1OrJoueur2(joueur,joueur);
        }
        throw new NotSignInException();
    }

//    public List<Partie> getPartiesByUser(Integer id) throws NotSignInException {
//        if(loginService.isLogged()){
//            return (List<Partie>) partieRepository.findByJoueur1OrJoueur2();
//        }
//        throw new NotSignInException();
//    }

//    public List<Partie> getPartiesAfterDate() throws NotSignInException {
//        if(loginService.isLogged()){
//            return (List<Partie>) partieRepository.findByDateHeureAfterNow();
//        }
//        throw new NotSignInException();
//    }
//
//    public List<Partie> getPartiesBeforeDate() throws NotSignInException {
//        if(loginService.isLogged()){
//            return (List<Partie>) partieRepository.findByDateHeureBeforeNowOrderByDateDesc();
//        }
//        throw new NotSignInException();
//    }

    public Partie getPartieById(int id) throws NotSignInException {
        if(loginService.isLogged()){
            return partieRepository.findById(id).get();
        }
        throw new NotSignInException();
    }

}
