package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Beneficiario;
import com.bonoperubackend.BonoPeruBackend.Repositorios.BeneficiarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/beneficario")
public class BeneficiarioController {

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    @PostMapping("/listar")
    public List<Beneficiario> listarDepartamentos() {
        List<Beneficiario> ben;
        ben = beneficiarioRepository.findAll();
        return ben;
    }
    //EL beneficiario consulta su cod de familia para saber su cronograma
    @PostMapping("/consultarHorario")
    public List<Beneficiario> buscarXCodFam() {
        List<Beneficiario> ben;
        ben = beneficiarioRepository.findAll();
        return ben;
    }


}
