package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Departamento;
import com.bonoperubackend.BonoPeruBackend.Modelos.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Integer> {

    Optional<Departamento> findByUbigeo(Integer ubigeo);
}
