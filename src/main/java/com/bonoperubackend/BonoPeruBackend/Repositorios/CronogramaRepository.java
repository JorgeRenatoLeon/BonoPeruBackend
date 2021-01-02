package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma,Integer> {

    Optional<Cronograma> findCronogramaByEstadoOrEstado(String estado,String estado2);

    ArrayList<Cronograma> findAll();

    Optional<Cronograma> findByEstado(String estado);


    @Query(value="select c.nombre from cronograma as c",nativeQuery=true)
    ArrayList<String> cronogramas();

    Optional<Cronograma> findByNombre(String nombre);

    ArrayList<Cronograma> findCronogramasByEstadoIsNot(String estado);

}
