package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Persona;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonaControlador {

    @PostMapping("/objeto")
    public String hello(@RequestBody Persona obj) {
        return String.format("Hola %s %s!", obj.getNombre(), obj.getApellido());
    }

    @GetMapping("/hola")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hola %s!", name);
    }
}
