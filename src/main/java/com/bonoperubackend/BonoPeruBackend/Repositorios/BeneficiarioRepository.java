package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiarioRepository extends  JpaRepository<Beneficiario,Integer>{
}
