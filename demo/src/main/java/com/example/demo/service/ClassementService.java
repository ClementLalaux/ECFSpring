package com.example.demo.service;

import com.example.demo.entity.Classement;
import com.example.demo.entity.Partie;
import com.example.demo.exception.ClassementNotExistException;
import com.example.demo.exception.NotSignInException;
import com.example.demo.repository.ClassementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassementService {

    @Autowired
    ClassementRepository classementRepository;

    public boolean updateClassement(int id, int point) throws ClassementNotExistException {
        if(classementRepository.findById(id).get() != null){
            try {
                Classement classement = classementRepository.findById(id).get();
                classement.addPoints(point);
                classementRepository.save(classement);
                return true;
            } catch (Exception e){
                throw new ClassementNotExistException();
            }
        }
        return false;
    }

    public Classement getClassementById(int id) {
        return classementRepository.findById(id).get();
    }

    public List<Classement> getClassements(){
        return (List<Classement>) classementRepository.findAll();
    }

}
