package com.bonoperubackend.BonoPeruBackend.Repositorios;

import java.util.Optional;

//import com.bonoperubackend.BonoPeruBackend.Modelos.User;
import com.bonoperubackend.BonoPeruBackend.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String username);

    Boolean existsByUsuario(String username);
}
