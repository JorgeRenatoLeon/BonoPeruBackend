package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaEncuesta;
import com.bonoperubackend.BonoPeruBackend.Repositorios.RespuestaEncuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/encuesta")
public class RespuestaEncuestaController {

    @Autowired
    RespuestaEncuestaRepository respuestaEncuestaRepository;

    @PostMapping("/listar")
    public List<RespuestaEncuesta> listarRespuestasEncuesta() {
        List<RespuestaEncuesta> val;
        val = respuestaEncuestaRepository.findAll();
        return val;
    }

    @PostMapping("/insertar")
    public void insertarRespuestaEncuesta(@RequestBody RespuestaEncuesta resp) {
        respuestaEncuestaRepository.save(resp);
    }

    @PostMapping("/eliminar")
    public void eliminarRespuestaEncuesta(@RequestParam Integer id) {
        Optional<RespuestaEncuesta> resp = respuestaEncuestaRepository.findById(id);
        if(resp.isPresent()){
            resp.get().setEstado("ELI");
            respuestaEncuestaRepository.save(resp.get());
        }
    }

    @PostMapping("/{id}")
    public RespuestaEncuesta respuestaEncuesta(@PathVariable Integer id) {
        Optional<RespuestaEncuesta> resp = respuestaEncuestaRepository.findById(id);
        if(resp.isPresent()){
            return resp.get();
        }
        return null;
    }
}
