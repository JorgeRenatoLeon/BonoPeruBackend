package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Departamento;
import com.bonoperubackend.BonoPeruBackend.Repositorios.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<Departamento> val= new ArrayList<>();
        departamentoRepository.findAll().forEach(val::add);
        return val;
    }
    @PostMapping("/insertar")
    public String insertarDepartamento(Departamento dep) {
        //departamentoRepository.save(dep);
        return dep.getESTADO();
    }
}
