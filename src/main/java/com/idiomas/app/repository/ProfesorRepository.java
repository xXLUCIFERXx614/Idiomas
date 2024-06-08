package com.idiomas.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.idiomas.app.entity.Profesor;

@Repository
public interface ProfesorRepository extends MongoRepository<Profesor, String> {
    Profesor findByCorreoProfAndPasswordProf(String correoProf, String passwordProf);
    
   
    
    
}


