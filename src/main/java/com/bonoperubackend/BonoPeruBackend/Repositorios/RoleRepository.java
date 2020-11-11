package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.ERole;
import com.bonoperubackend.BonoPeruBackend.Modelos.Rol;
//import com.bonoperubackend.BonoPeruBackend.Modelos.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNOMBRE(ERole name);
}
