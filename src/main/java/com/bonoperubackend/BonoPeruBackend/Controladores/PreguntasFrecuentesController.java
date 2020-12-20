package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.PreguntasFrecuentes;
import com.bonoperubackend.BonoPeruBackend.Repositorios.PreguntasFrecuentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/preguntasfrecuentes")
public class PreguntasFrecuentesController {

    @Autowired
    PreguntasFrecuentesRepository pregFrecRepository;

    @PostMapping("/listar")
    public List<PreguntasFrecuentes> listarPreguntasFrec() {
        List<PreguntasFrecuentes> preguntas;
        preguntas = pregFrecRepository.findAllACTPreguntasFrec();
        return preguntas;
    }

    @PostMapping("/insertar")
    public void insertarPreguntaFrec(@RequestBody PreguntasFrecuentes pregfrec) {
        pregFrecRepository.save(pregfrec);
    }

    @PostMapping("/modificar")
    public void modificarPreguntaFrec(@RequestBody List<PreguntasFrecuentes> preguntasfrec) {
        for (PreguntasFrecuentes pregfrec: preguntasfrec) {
            Optional<PreguntasFrecuentes> preg = pregFrecRepository.findById(pregfrec.getIdpreguntasfrecuentes());
            if (preg.isPresent()) {
                if (pregfrec.getPregunta() != null) {
                    preg.get().setPregunta(pregfrec.getPregunta());
                }
                if(pregfrec.getRespuesta() != null){
                    preg.get().setRespuesta(pregfrec.getRespuesta());
                }
                if(pregfrec.getUsuarioactualizacion() != 0){
                    preg.get().setUsuarioactualizacion(pregfrec.getUsuarioactualizacion());
                }
                pregFrecRepository.save(preg.get());
            }
        }
    }

    @PostMapping("/eliminar")
    public void eliminarPreguntaFrec(@RequestParam Integer id) {
        Optional<PreguntasFrecuentes> pregfrec = pregFrecRepository.findById(id);
        if (pregfrec.isPresent()) {
            pregfrec.get().setEstado("ELI");
            pregFrecRepository.save(pregfrec.get());
        }
    }
}
