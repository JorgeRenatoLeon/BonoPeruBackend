package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LugarEntregaAlgoritmoRepository extends  JpaRepository<LugarEntregaAlgoritmo,Integer> {
    @Query(value="SELECT  l.idlugarentrega, l.codigo, l.aforo as capacidad , \n" +
            "sum(if(STRCMP(hor.estado,\"ENT\") = 0 and STRCMP(cro.estado,\"ANT\") = 0,1,0)) as cantbonosentregados,\n" +
            "sum(if(STRCMP(hor.estado,\"NOE\") = 0 and STRCMP(cro.estado,\"ANT\") = 0,1,0)) as cantbonosnoentregados, \n" +
            "count(que.idquejas) as cantquejas, dis.zonariesgo as nivelcontagio, dis.ubigeo as ubigeodistrito, \n" +
            "pro.ubigeo as ubigeoprovincia, dep.ubigeo as ubigeodepartamento, hle.idhorariolugarentrega\n" +
            "FROM lugarentrega as l \n" +
            "left join distrito as dis on l.fiddistrito=dis.iddistrito \n" +
            "left join provincia as pro on dis.fidprovincia=pro.idprovincia \n" +
            "left join departamento as dep on pro.fiddepartamento=dep.iddepartamento  \n" +
            "left join horariolugarentrega as hle on l.idlugarentrega=hle.fidlugarentrega \n" +
            "left join horario as hor on hle.idhorariolugarentrega=hor.fidhorariolugarentrega \n" +
            "left join cronograma as cro on cro.idcronograma=hor.fidcronograma\n" +
            "left join quejas as que on que.fidlugarentrega=l.idlugarentrega and que.tipoqueja='lugar'\n" +
            "where l.estado='ACT'  and dis.estado='ACT' and pro.estado='ACT' and dep.estado='ACT' and hle.estado='ACT' group by l.codigo;",nativeQuery=true)
    ArrayList<LugarEntregaAlgoritmo> findAllByLugarEntregaAlgoritmo();


}

