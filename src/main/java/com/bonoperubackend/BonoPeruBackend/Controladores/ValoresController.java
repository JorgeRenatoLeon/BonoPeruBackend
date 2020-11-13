package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Departamento;
import com.bonoperubackend.BonoPeruBackend.Modelos.Valores;
import com.bonoperubackend.BonoPeruBackend.Repositorios.ValoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/valores")
public class ValoresController {

    @Autowired
    ValoresRepository valoresRepository;

    @PostMapping("/listar")
    public List<Valores> listarValores() {
        List<Valores> val= new ArrayList<>();
        valoresRepository.findAll().forEach(val::add);
        return val;
    }
    @PostMapping("/insertar")
    public void insertarValores(@RequestBody Valores val) {
        valoresRepository.save(val);
    }
}
