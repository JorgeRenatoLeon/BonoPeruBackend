package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Departamento;
import com.bonoperubackend.BonoPeruBackend.Modelos.Distrito;
import com.bonoperubackend.BonoPeruBackend.Repositorios.DistritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/distrito")
public class DistritoController {

    @Autowired
    DistritoRepository distritoRepository;

    @PostMapping("/listar/{fidprovincia}")
    public List<Distrito> listarDistrito(@PathVariable Integer fidprovincia) {
        List<Distrito> val;
        val=distritoRepository.findAllByFidprovinciaAndEstado(fidprovincia, "ACT");
        return val;
    }

    @PostMapping("/insertar")
    public void insertarDistrito(@RequestBody Distrito dis) {
        distritoRepository.save(dis);
    }
}
