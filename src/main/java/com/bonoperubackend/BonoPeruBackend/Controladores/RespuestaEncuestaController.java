package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Beneficiario;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaEncuesta;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaIndividual;
import com.bonoperubackend.BonoPeruBackend.Repositorios.BeneficiarioRepository;
import com.bonoperubackend.BonoPeruBackend.Repositorios.RespuestaEncuestaRepository;
import com.bonoperubackend.BonoPeruBackend.Repositorios.RespuestaIndividualRepository;
import com.bonoperubackend.BonoPeruBackend.Repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/encuesta")
public class RespuestaEncuestaController {

    @Autowired
    RespuestaEncuestaRepository respuestaEncuestaRepository;

    @Autowired
    RespuestaIndividualRepository respuestaRep;

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    public class Respuestas extends RespuestaEncuesta {
        private List<RespuestaIndividual> respuestas;

        public List<RespuestaIndividual> getRespuestas() {
            return respuestas;
        }

        public void setRespuestas(List<RespuestaIndividual> respuestas) {
            this.respuestas = respuestas;
        }
    }

    @PostMapping("/listar")
    public List<Respuestas> listarRespuestasEncuesta() {
        List<RespuestaEncuesta> val;
        val = respuestaEncuestaRepository.findAll();
        List<Respuestas> resultado = new ArrayList<>();
        for (RespuestaEncuesta resp : val) {
            Respuestas respuesta = buscarRespuestas(resp);
            resultado.add(respuesta);
        }
        return resultado;
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
    public Respuestas respuestaEncuesta(@PathVariable Integer id) {
        Optional<RespuestaEncuesta> resp = respuestaEncuestaRepository.findById(id);
        if(resp.isPresent()){
            return buscarRespuestas(resp.get());
        }
        return null;
    }

    @PostMapping("/usuario/{id}")
    public Respuestas respuestaUsuarioEncuesta(@PathVariable Integer id) {
        Optional<Beneficiario> beneficiario = beneficiarioRepository.findById(id);
        if(beneficiario.isPresent()){
            Optional<RespuestaEncuesta> resp = respuestaEncuestaRepository.findById(beneficiario.get().getId_beneficiario());
            if(resp.isPresent()){
                return buscarRespuestas(resp.get());
            }
        }
        return null;
    }

    public Respuestas buscarRespuestas(RespuestaEncuesta encuesta){

            Respuestas respuesta = new Respuestas();
            respuesta.setBeneficiario(encuesta.getBeneficiario());
            respuesta.setEstado(encuesta.getEstado());
            respuesta.setFechaActualizacion(encuesta.getFechaActualizacion());
            respuesta.setFechaCreacion(encuesta.getFechaCreacion());
            respuesta.setHorario(encuesta.getHorario());
            respuesta.setIdRespuestaencuesta(encuesta.getIdRespuestaencuesta());
            List<RespuestaIndividual> respuestas = respuestaRep.findAllByRespuestaencuesta(encuesta);
            respuesta.setRespuestas(respuestas);

            return  respuesta;
    }
}
