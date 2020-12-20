package com.bonoperubackend.BonoPeruBackend.Controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HomeController {
    @GetMapping({"/hello"})
    public String helloWorld(@RequestParam(required = false, defaultValue = "amigo") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-world";
    }
}