package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Departamento;
import com.bonoperubackend.BonoPeruBackend.Repositorios.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public void insertarDepartamento(@RequestBody Departamento dep) {
        departamentoRepository.save(dep);
    }

    @PostMapping("/eliminar")
    public void eliminarDepartamento(@RequestParam Integer id) {
        Optional<Departamento> dep=departamentoRepository.findById(id);
        if(dep.isPresent()){
            dep.get().setEstado("ELI");
            departamentoRepository.save(dep.get());
        }
    }
}
