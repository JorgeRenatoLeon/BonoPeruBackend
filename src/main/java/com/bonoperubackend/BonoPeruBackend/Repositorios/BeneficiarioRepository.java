package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Beneficiario;
import com.bonoperubackend.BonoPeruBackend.Modelos.Horario;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaEncuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BeneficiarioRepository extends  JpaRepository<Beneficiario,Integer>{
    //Optional<Horario> findAllByBeneficiario(Integer id);

  //Optional<Beneficiario> findByCodigo_familia(String codigo_familia);

}
