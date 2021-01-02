package com.bonoperubackend.BonoPeruBackend.Controladores;
import com.bonoperubackend.BonoPeruBackend.Modelos.Pregunta;
import com.bonoperubackend.BonoPeruBackend.Repositorios.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/pregunta")

public class PreguntaController {
    @Autowired
    PreguntaRepository preguntaRepository;

    @PostMapping("/listar_pregs")
    public List<Pregunta> listarPreguntas() {
        List<Pregunta> preguntas;
        preguntas = preguntaRepository.findAllByEstado("ACT");
        return preguntas;
    }
}
