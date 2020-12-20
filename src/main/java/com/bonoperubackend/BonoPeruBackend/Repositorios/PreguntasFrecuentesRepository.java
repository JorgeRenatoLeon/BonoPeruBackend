package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.PreguntasFrecuentes;
import com.bonoperubackend.BonoPeruBackend.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PreguntasFrecuentesRepository extends JpaRepository<PreguntasFrecuentes,Integer> {

    @Query(value="select * from preguntasfrecuentes where estado = 'ACT'",nativeQuery=true)
    List<PreguntasFrecuentes> findAllACTPreguntasFrec();

}
