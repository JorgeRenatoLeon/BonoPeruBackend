package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Beneficiario;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaEncuesta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RespuestaEncuestaRepository extends JpaRepository<RespuestaEncuesta,Integer> {
    Optional<RespuestaEncuesta> findAllByBeneficiario(Beneficiario beneficiario);
    Optional<RespuestaEncuesta> findByEstadoAndBeneficiario_IdbeneficiarioAndHorario_IdHorario(String estado, Integer fidbeneficiario, Integer fidhorario);
    Optional<RespuestaEncuesta> findByBeneficiarioAndEstado(Beneficiario beneficiario,String estado);
}
