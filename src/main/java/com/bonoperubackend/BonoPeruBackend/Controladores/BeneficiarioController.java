package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Beneficiario;
import com.bonoperubackend.BonoPeruBackend.Modelos.Horario;
import com.bonoperubackend.BonoPeruBackend.Repositorios.BeneficiarioRepository;
import com.bonoperubackend.BonoPeruBackend.Repositorios.HorarioRepository;
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
    @Autowired
    HorarioRepository horarioRepository;

    @PostMapping("/listar")
    public List<Beneficiario> listarDepartamentos() {
        List<Beneficiario> ben;
        ben = beneficiarioRepository.findAll();
        return ben;
    }
    //EL beneficiario consulta su cod de familia para saber su horario
    @PostMapping("/consultarHorario")
    public void buscarXCodFam(@RequestParam String cod) {
        int i=1;
//        List<Beneficiario> ben;
//        Optional<Horario> abc= beneficiarioRepository.findById(id);
        //Optional<Beneficiario> beneficiario = beneficiarioRepository.findByCodigoFamilia(id);

//        Optional<Beneficiario>  h= beneficiarioRepository.findByCodigo_familia(cod);
//
//        if(h.isPresent()){
//            return h.get();
//        }
//        return null;
    }


}
