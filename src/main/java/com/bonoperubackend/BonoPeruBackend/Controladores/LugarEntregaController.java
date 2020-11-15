package com.bonoperubackend.BonoPeruBackend.Controladores;
import com.bonoperubackend.BonoPeruBackend.Modelos.Beneficiario;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaEncuesta;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaIndividual;
import com.bonoperubackend.BonoPeruBackend.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lugarentrega")
public class LugarEntregaController {

    @Autowired
    LugarEntregaRepository lugarEntregaRepository;

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    HorarioLugarEntregaRepository horarioLugarEntregaRepository;

    @PostMapping("/consultarCodigoFamilia")
    public String consultarCodigoFamilia(@RequestParam String codigo) {
        return "hola";
    }
}
