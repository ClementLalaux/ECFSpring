package com.example.demo.controller;

import com.example.demo.service.ClassementService;
import com.example.demo.service.JoueurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("classement")
public class ClassementController {
    @Autowired
    private JoueurService joueurService;

    @Autowired
    private ClassementService classementService;

    @Autowired
    private HttpSession session;

    @GetMapping("classement")
    public ModelAndView classement(){
        ModelAndView modelAndView = new ModelAndView("classement");

        modelAndView.addObject("joueurs",joueurService.findAllJoueurs());
        modelAndView.addObject("classements",classementService.getClassements());
        return modelAndView;
    }
}
