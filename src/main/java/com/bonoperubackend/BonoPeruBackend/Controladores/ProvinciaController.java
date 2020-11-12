package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Provincia;
import com.bonoperubackend.BonoPeruBackend.Repositorios.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/provincia")
public class ProvinciaController {

    @Autowired
    ProvinciaRepository provinciaRepository;
    @PostMapping("/listar")
    public List<Provincia> listarProvincias() {
        List<Provincia> val= new ArrayList<>();
        provinciaRepository.findAll().forEach(val::add);
        return val;
    }
    @PostMapping("/insertar")
    public void insertarProvincia(Provincia prov) {
        provinciaRepository.save(prov);
    }
}