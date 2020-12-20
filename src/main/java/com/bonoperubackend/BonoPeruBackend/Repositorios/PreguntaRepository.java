package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Pregunta;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaIndividual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PreguntaRepository extends JpaRepository<Pregunta,Integer> {

    ArrayList<Pregunta> findAllByEstado(String estado);
}
