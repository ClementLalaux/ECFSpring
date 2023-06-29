package com.example.demo.repository;

import com.example.demo.entity.Classement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassementRepository extends CrudRepository<Classement, Integer> {

}
