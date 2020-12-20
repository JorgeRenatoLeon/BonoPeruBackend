package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Provincia;
import com.bonoperubackend.BonoPeruBackend.Repositorios.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/provincia")
public class ProvinciaController {

    @Autowired
    ProvinciaRepository provinciaRepository;

    @PostMapping("/listar/{fiddepartamento}")
    public List<Provincia> listarProvincias(@PathVariable Integer fiddepartamento) {
        List<Provincia> val;
        val = provinciaRepository.findAllByFiddepartamentoAndAndEstado(fiddepartamento, "ACT");
        return val;
    }

    @PostMapping("/insertar")
    public void insertarProvincia(@RequestBody Provincia prov) {
        provinciaRepository.save(prov);
    }
}
