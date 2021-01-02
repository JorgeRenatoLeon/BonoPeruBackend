package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico,Integer> {

    @Query(value="select c.idcronograma as id, c.nombre as nombre, c.fechainicio as fechaini,\n" +
            "c.fechafin as fechafin, count(distinct h.fidbeneficiario) as beneficiarios, \n" +
            "count(distinct l.idlugarentrega) as lugares\n" +
            "from cronograma as c\n" +
            "inner join horario as h on h.fidcronograma=c.idcronograma\n" +
            "inner join horariolugarentrega as p on p.idhorariolugarentrega=h.fidhorariolugarentrega\n" +
            "inner join lugarentrega as l on l.idlugarentrega=p.fidlugarentrega\n" +
            "inner join beneficiario as b on b.idbeneficiario=h.fidbeneficiario\n" +
            "group by c.idcronograma;",nativeQuery=true)
    ArrayList<Historico> monitoreo();
}
