package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Lugarentrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LugarEntregaRepository extends JpaRepository<Lugarentrega,Integer> {
}
