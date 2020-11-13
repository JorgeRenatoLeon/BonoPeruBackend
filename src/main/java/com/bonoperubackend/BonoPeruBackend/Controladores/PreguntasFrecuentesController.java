package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.PreguntasFrecuentes;
import com.bonoperubackend.BonoPeruBackend.Repositorios.PreguntasFrecuentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insertar")
    public void insertarPreguntasFrec(@RequestBody PreguntasFrecuentes preg) {
        pregFrecRepository.save(preg);
    }
}
