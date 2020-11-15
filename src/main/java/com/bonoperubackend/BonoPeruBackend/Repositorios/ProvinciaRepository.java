package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia,Integer> {
    Optional<Provincia> findByFiddepartamento(Integer fiddepartamento);
}
