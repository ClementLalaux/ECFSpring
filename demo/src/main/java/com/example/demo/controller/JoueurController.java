package com.example.demo.controller;

import com.example.demo.exception.NotSignInException;
import com.example.demo.exception.UserExistException;
import com.example.demo.exception.UserNotExistException;
import com.example.demo.service.ClassementService;
import com.example.demo.service.JoueurService;
import com.example.demo.service.LoginService;
import com.example.demo.service.PartieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("joueur")
public class JoueurController {

    @Autowired
    private JoueurService joueurService;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession session;

    @Autowired
    private PartieService partieService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ClassementService classementService;

    @GetMapping("connexion")
    public ModelAndView connexion(){
        ModelAndView modelAndView = new ModelAndView("connexion");
        return modelAndView;
    }

    @PostMapping("connexion")
    public String connexion(@RequestParam String mail,@RequestParam String mdp) throws UserNotExistException {
        if(joueurService.connexion(mail,mdp)){
            return "redirect:/joueur/profil";
        }
        return null;
    }

    @ExceptionHandler(UserNotExistException.class)
    public ModelAndView handleUserNotExist(UserNotExistException ex) {
        ModelAndView mv = new ModelAndView("connexion");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

    @GetMapping("inscription")
    public ModelAndView inscription(){
        ModelAndView modelAndView = new ModelAndView("inscription");
        return modelAndView;
    }

    @GetMapping("profil")
    public ModelAndView profil() throws UserNotExistException, NotSignInException {
        ModelAndView modelAndView = new ModelAndView("profil");
        if(joueurService.findJoueurById((Integer) session.getAttribute("joueur")) != null){
            modelAndView.addObject("joueur",joueurService.findJoueurById((Integer) session.getAttribute("joueur")));
            modelAndView.addObject("parties",partieService.getPartiesByIdJoueur(joueurService.findJoueurById(loginService.getUserId())));
            modelAndView.addObject("classement",classementService.getClassementById(loginService.getUserId()));
        }
        return modelAndView;
    }

    @PostMapping("inscription")
    public String inscription(@RequestParam String nom, @RequestParam String prenom, @RequestParam String mail, @RequestParam String mdp) throws UserExistException {
        if(joueurService.inscription(nom,prenom,mail,mdp)){
            return "redirect:/joueur/connexion";
        }
        return null;
    }

    @ExceptionHandler(UserExistException.class)
    public ModelAndView handleUserExist(UserExistException ex) {
        ModelAndView mv = new ModelAndView("inscription");
        mv.addObject("message", ex.getMessage());
        return mv;
    }

}
