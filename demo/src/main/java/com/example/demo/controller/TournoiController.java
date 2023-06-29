package com.example.demo.controller;

import com.example.demo.exception.EmptyFieldsException;
import com.example.demo.exception.NotAdminException;
import com.example.demo.exception.NotSignInException;
import com.example.demo.exception.TournoiExistException;
import com.example.demo.service.TournoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tournoi")
public class TournoiController {

    @Autowired
    private TournoiService tournoiService;

    @GetMapping("")
    public ModelAndView tournoi(){
        ModelAndView modelAndView = new ModelAndView("tournoi-form");
        return modelAndView;
    }

    @PostMapping("add")
    public String submitAddTournoi(@RequestParam String nom, @RequestParam String statut) throws NotSignInException, TournoiExistException, EmptyFieldsException, NotAdminException {
        if(tournoiService.addTournoi(nom,statut)){
            return "redirect:/partie";
        }
        return null;
    }

    @ExceptionHandler(NotSignInException.class)
    public ModelAndView handleException(NotSignInException ex) {
        ModelAndView mv = new ModelAndView("tournoi-form");
        mv.addObject("message", ex.getMessage());
        return mv;
    }
}
