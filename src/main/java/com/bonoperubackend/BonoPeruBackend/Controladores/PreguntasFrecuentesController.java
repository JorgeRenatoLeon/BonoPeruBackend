package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.PreguntasFrecuentes;
import com.bonoperubackend.BonoPeruBackend.Repositorios.PreguntasFrecuentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/preguntasfrecuentes")
public class PreguntasFrecuentesController {

    @Autowired
    PreguntasFrecuentesRepository pregFrecRepository;
    @PostMapping("/listar")
    public List<PreguntasFrecuentes> listarPreguntasFrec() {
        List<PreguntasFrecuentes> val= new ArrayList<>();
        pregFrecRepository.findAll().forEach(val::add);
        return val;
    }
}
