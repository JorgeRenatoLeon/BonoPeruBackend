package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Usuario;
import com.bonoperubackend.BonoPeruBackend.Modelos.Valores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValoresRepository extends JpaRepository<Valores,Long> {

}
