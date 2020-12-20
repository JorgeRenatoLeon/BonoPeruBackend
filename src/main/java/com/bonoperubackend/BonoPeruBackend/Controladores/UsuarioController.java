package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Usuario;
import com.bonoperubackend.BonoPeruBackend.Repositorios.UserRepository;
import com.bonoperubackend.BonoPeruBackend.Modelos.Rol;
import com.bonoperubackend.BonoPeruBackend.Repositorios.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/usuarios")

public class UsuarioController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository rolRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/listar")
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios;
        usuarios = userRepository.findAllACTUsuarios();
        return usuarios;
    }

    @PostMapping("/listarFiltrado")
    public List<Usuario> listarUsuariosFiltro(@RequestParam String busqueda) {
        List<Usuario> usuarios;
        usuarios = userRepository.findByNombresApellidosACT(busqueda);
        return usuarios;
    }

    @PostMapping("/insertar")
    public void insertarUsuario(@RequestBody Usuario usuario) {
        Optional <Rol> rol = rolRepository.findById(2);
        Set <Rol> setRol = new HashSet<>();
        setRol.add(rol.get());
        usuario.setRoles(setRol);
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        userRepository.save(usuario);
    };

    @PostMapping("/modificar")
    public void modificarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> user = userRepository.findById(usuario.getId());
        if (user.isPresent()) {
            if (usuario.getNombres() != null) {
                user.get().setNombres(usuario.getNombres());
            }
            if(usuario.getApellidos() != null){
                user.get().setApellidos(usuario.getApellidos());
            }
            if(usuario.getUsername() != null){
                user.get().setUsername(usuario.getUsername());
            }
            if(usuario.getUsuarioActualizacion() != 0){
                user.get().setUsuarioActualizacion(usuario.getUsuarioActualizacion());
            }
            userRepository.save(user.get());
        }
    }

    @PostMapping("/eliminar")
    public void eliminarUsuario(@RequestParam Integer id) {
        Optional<Usuario> usuario = userRepository.findById(id);
        if (usuario.isPresent()) {
            usuario.get().setEstado("ELI");
            userRepository.save(usuario.get());
        }
    }

    public static class Data {
        private String contrasena;
        public Data() {
            super();
        }
        public Data(String contrasena){this.contrasena = contrasena;}

        public String getContrasena() {
            return contrasena;
        }

        public void setContrasena(String contrasena) {
            this.contrasena = contrasena;
        }
    }

    @PostMapping("/contrasena/{id}")
    public void modificarUsuario(@PathVariable Integer id, @RequestBody Data contrasena) {
        Optional<Usuario> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setPassword(encoder.encode(contrasena.getContrasena()));
            userRepository.save(user.get());
        }
    }
}
