package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.ListaLugarEntrega;
import com.bonoperubackend.BonoPeruBackend.Modelos.Lugarentrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface LugarEntregaRepository extends JpaRepository<Lugarentrega,Integer> {
    ArrayList<Lugarentrega> findAllByEstado(String estado);

    Optional<Lugarentrega> findByCodigo(String codigo);
}
