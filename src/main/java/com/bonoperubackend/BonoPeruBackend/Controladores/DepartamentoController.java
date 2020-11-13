package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Departamento;
import com.bonoperubackend.BonoPeruBackend.Payload.Request.SignupRequest;
import com.bonoperubackend.BonoPeruBackend.Repositorios.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/departamento")
public class DepartamentoController {

    @Autowired
    DepartamentoRepository departamentoRepository;
    @PostMapping("/listar")
    public List<Departamento> listarDepartamentos() {
        List<Departamento> val;
        val = departamentoRepository.findAll();
        return val;
    }
    @PostMapping("/insertar")
    public void insertarDepartamento(Departamento dep) {
        departamentoRepository.save(dep);
    /*public String insertarDepartamento(@Valid @RequestBody SignupRequest signUpRequest) {
        Departamento dep = new Departamento(signUpRequest.getUsername(),signUpRequest.getZona(),signUpRequest.getPassword());
        departamentoRepository.save(dep);
        return dep.getESTADO();*/
    }
}
