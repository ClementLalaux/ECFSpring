package com.example.demo.repository;

import com.example.demo.entity.Tournoi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournoiRepository extends CrudRepository<Tournoi,Integer> {

    public boolean existsTournoiByNom(String nom);

}
