package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BeneficiarioAlgoritmoRepository extends  JpaRepository<BeneficiarioAlgoritmo,Integer>{
    @Query(value="select b.idbeneficiario, b.codigofamilia, b.penalidad,b.esdiscapacitado, b.femenino, b.masculino,  dis.ubigeo as ubigeodistrito, " +
            "pro.ubigeo as ubigeoprovincia, dep.ubigeo as ubigeodepartamento \n"+
            "FROM beneficiario as b inner join distrito as dis on b.fiddistrito=dis.iddistrito inner join provincia as pro on \n"+
            "dis.fidprovincia=pro.idprovincia inner join departamento as dep on pro.fiddepartamento=dep.iddepartamento \n"+
            "where b.estado='ACT' and dis.estado='ACT' and pro.estado='ACT' and dep.estado='ACT'",nativeQuery=true)
    ArrayList<BeneficiarioAlgoritmo> findAllByBeneficiarioAlgoritmo();
}
