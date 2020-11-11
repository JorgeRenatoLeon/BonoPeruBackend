package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping(value="/api/Persona/")
public class PersonaControlador {

    //@Autowired
    //private PersonaServiceAPI personaServiceAPI;

    @PostMapping("/per")
    public String hello(@RequestBody Persona obj) {
        return String.format("Hola %s %s!", obj.getNombre(), obj.getApellido());
    }

    @PostMapping("/hola")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hola %s!", name);
    }
    @PostMapping("/lista")
    public static List<Persona> getPersonas(){
        List<Persona> lista = new ArrayList<>();
        Persona persona= new Persona("Johana","Diaz");
        Persona persona2= new Persona("Caro","Mejía");
        lista.add(persona);
        lista.add(persona2);
        return lista;
    }
}
