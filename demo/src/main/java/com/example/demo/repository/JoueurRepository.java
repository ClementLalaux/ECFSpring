package com.example.demo.repository;

import com.example.demo.entity.Joueur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JoueurRepository extends CrudRepository<Joueur,Integer> {


    public Joueur findByMailAndMdp(String mail, String mdp);

    public Joueur findByMail(String mail);
}
