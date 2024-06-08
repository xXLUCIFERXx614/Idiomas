package com.idiomas.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.idiomas.app.entity.Coordinador;

@Repository
public interface CoordinadorRepository extends MongoRepository<Coordinador, String> {
    Coordinador findByCorreoCordAndPasswrdCord(String correoCord, String passwrdCord);
}

