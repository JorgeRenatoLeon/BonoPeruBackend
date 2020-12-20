package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.HorarioLugarEntregaAlgoritmo;
import com.bonoperubackend.BonoPeruBackend.Modelos.LugarEntregaAlgoritmo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface HorarioLugarEntregaAlgoritmoRepository extends JpaRepository<HorarioLugarEntregaAlgoritmo,String> {

    @Query(value="SELECT l.codigo, hle.idhorariolugarentrega, hle.horaaperturaturnoma,  hle.horacierreturnoma, hle.horaaperturaturnotar, hle.horacierreturnotar\n" +
            "FROM lugarentrega as l \n" +
            "inner join horariolugarentrega as hle on l.idlugarentrega=hle.fidlugarentrega \n" +
            "where l.estado='ACT' and hle.estado='ACT' and hle.dia like %?1%",nativeQuery=true)
    ArrayList<HorarioLugarEntregaAlgoritmo> findLugarXDia(String dia);

}
