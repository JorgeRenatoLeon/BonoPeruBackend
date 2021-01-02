package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Horario;
import com.bonoperubackend.BonoPeruBackend.Modelos.Horariolugarentrega;
import com.bonoperubackend.BonoPeruBackend.Modelos.Lugarentrega;
//import jdk.vm.ci.meta.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface HorarioLugarEntregaRepository extends JpaRepository<Horariolugarentrega,Integer> {

    Optional<Horariolugarentrega> findByDiaAndLugarentrega(String dia, Lugarentrega lugarentrega);
}
