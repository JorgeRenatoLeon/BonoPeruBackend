package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
public interface BeneficiarioRepository extends  JpaRepository<Beneficiario,Integer>{

    Optional<Beneficiario> findByCodigofamilia(String codigofamilia);
    ArrayList<Beneficiario> findBeneficiariosByEstado(String estado);
    Integer countBeneficiariosByFemeninoIsAndEstado(Boolean valor, String estado);
    Integer countBeneficiariosByMasculinoIsAndEstado(Boolean valor, String estado);
    Integer countBeneficiariosByEsdiscapacitadoAndEstado(Boolean valor, String estado);

    @Query(value="select  sum(b.femenino) as cantfemenino, sum(b.masculino) as cantmasculino, sum(b.esdiscapacitado) as cantdiscapacitado\n" +
            "from beneficiario as b \n" +
            "where b.estado='ACT';",nativeQuery=true)
    ArrayList<Object> indicadoresBeneficiario();

}
