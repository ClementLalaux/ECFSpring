package com.example.demo.repository;

import com.example.demo.entity.Joueur;
import com.example.demo.entity.Partie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartieRepository extends CrudRepository<Partie,Integer> {

//    public List<Partie> findByJoueur1OrJoueur2(Joueur idJoueur1 , Joueur idJoueur2);

//    @Query("SELECT p FROM Partie p WHERE p.date < CURRENT_TIMESTAMP ORDER BY p.date DESC LIMIT 5")
//    List<Partie> findByDateHeureBeforeNowOrderByDateDesc();
//
//    @Query("SELECT p FROM Partie p WHERE p.date > CURRENT_TIMESTAMP LIMIT 5")
//    List<Partie> findByDateHeureAfterNow();
}
