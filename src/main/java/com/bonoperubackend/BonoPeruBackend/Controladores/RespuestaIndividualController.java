package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Beneficiario;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaEncuesta;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaIndividual;
import com.bonoperubackend.BonoPeruBackend.Repositorios.RespuestaEncuestaRepository;
import com.bonoperubackend.BonoPeruBackend.Repositorios.RespuestaIndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/encuesta")
public class RespuestaIndividualController {

    @Autowired
    RespuestaEncuestaRepository respuestaEncuestaRepository;

    @Autowired
    RespuestaIndividualRepository respuestaRep;

    @PostMapping("/responder/{id}")
    public void responderEncuesta(@RequestBody List<RespuestaIndividual> respuestas, @PathVariable Integer id) {
        Optional<RespuestaEncuesta> resp = respuestaEncuestaRepository.findById(id);
        if(resp.isPresent()){
            Integer i = 0;
            for (RespuestaIndividual respuesta: respuestaRep.findAllByRespuestaencuesta(resp.get())) {
                respuesta.setRespuesta(respuestas.get(i).getRespuesta());
                respuesta.setPuntaje(respuestas.get(i).getPuntaje());
                respuestaRep.save(respuesta);
                i++;
            }
            resp.get().setEstado("RES");
            respuestaEncuestaRepository.save(resp.get());
        }
    }
}
