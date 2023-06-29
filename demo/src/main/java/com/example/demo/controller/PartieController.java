package com.example.demo.controller;

import com.example.demo.entity.Joueur;
import com.example.demo.entity.Tournoi;
import com.example.demo.exception.NotAdminException;
import com.example.demo.exception.NotSignInException;
import com.example.demo.service.PartieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("partie")
public class PartieController {

    @Autowired
    PartieService partieService;

    @GetMapping("")
    public ModelAndView getParties() throws NotSignInException {
        ModelAndView modelAndView = new ModelAndView("parties");
        modelAndView.addObject("parties",partieService.getParties());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addPartie() throws NotSignInException {
        ModelAndView modelAndView = new ModelAndView("partie-form");
        modelAndView.addObject("parties",partieService.getParties());
        return modelAndView;
    }

    @PostMapping("/add")
    public String submitAddPartie(@RequestParam Date date, @RequestParam Joueur joueur1 , @RequestParam Joueur joueur2 , @RequestParam Tournoi tournoi) throws NotSignInException, NotAdminException {
        if(partieService.addPartie(date,0,joueur1,joueur2,tournoi)){
            return "redirect:/partie";
        }
        return null;
    }

}
