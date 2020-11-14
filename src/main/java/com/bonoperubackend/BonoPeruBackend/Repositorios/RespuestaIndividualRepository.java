package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaIndividual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RespuestaIndividualRepository extends JpaRepository<RespuestaIndividual,Integer> {
}

