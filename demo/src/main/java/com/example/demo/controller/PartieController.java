package com.example.demo.controller;

import com.example.demo.entity.Joueur;
import com.example.demo.entity.Partie;
import com.example.demo.entity.Tournoi;
import com.example.demo.exception.NotAdminException;
import com.example.demo.exception.NotSignInException;
import com.example.demo.exception.UserNotExistException;
import com.example.demo.service.JoueurService;
import com.example.demo.service.LoginService;
import com.example.demo.service.PartieService;
import com.example.demo.service.TournoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("partie")
public class PartieController {

    @Autowired
    PartieService partieService;

    @Autowired
    TournoiService tournoiService;

    @Autowired
    JoueurService joueurService;

    @Autowired
    LoginService loginService;

    @GetMapping("")
    public ModelAndView getParties() throws NotSignInException, UserNotExistException {
        ModelAndView modelAndView = new ModelAndView("parties");
        modelAndView.addObject("parties",partieService.getParties());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addPartie(){
        ModelAndView modelAndView = new ModelAndView("partie-form");
        modelAndView.addObject("partie", Partie.builder().build());
        return modelAndView;
    }

    @PostMapping("/add")
    public String submitAddPartie(@ModelAttribute Partie partie, @RequestParam String joueur1 , @RequestParam String joueur2 , @RequestParam String tournoi) throws NotSignInException, NotAdminException, UserNotExistException {
        Tournoi tournoiA = tournoiService.getTournoiById(Integer.parseInt(tournoi));
        Joueur joueurA = joueurService.findJoueurById(Integer.parseInt(joueur1));
        Joueur joueurB = joueurService.findJoueurById(Integer.parseInt(joueur2));
        if(partieService.addPartie(partie.getDate(),0,joueurA,joueurB,tournoiA)){
            return "redirect:/partie";
        }
        return null;
    }

}
