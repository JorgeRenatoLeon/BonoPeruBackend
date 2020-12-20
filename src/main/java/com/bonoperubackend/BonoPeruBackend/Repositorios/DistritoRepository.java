package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito,Integer> {
        ArrayList<Distrito> findAllByFidprovinciaAndEstado(Integer fidprovincia, String estado);
        Optional<Distrito> findByUbigeo(Integer ubigeo);
}
