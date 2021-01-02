package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.ListaLugarEntrega;
import com.bonoperubackend.BonoPeruBackend.Modelos.Lugarentrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface LugarEntregaRepository extends JpaRepository<Lugarentrega,Integer> {
    ArrayList<Lugarentrega> findAllByEstado(String estado);

    Optional<Lugarentrega> findByCodigo(String codigo);

    @Query(value="Select le.nombre, count(q.idquejas) as cantQuejas \n" +
            "FROM horario as hor \n" +
            "left join cronograma as cro on cro.idcronograma=hor.fidcronograma \n" +
            "left join horariolugarentrega as horle on hor.fidhorariolugarentrega=horle.idhorariolugarentrega\n" +
            "left join lugarentrega as le on le.idlugarentrega=horle.fidlugarentrega\n" +
            "inner join quejas as q on hor.idhorario=q.fidhorario\n" +
            "where cro.estado=\"PUB\" and q.fechacreacion>=?1 and q.fechacreacion<=?2 and q.estado=\"ACT\" \n" +
            "group by le.idlugarentrega\n" +
            "order by cantQuejas desc\n" +
            "limit 5",nativeQuery=true)
    ArrayList<ArrayList<Object>> topPeoresLugares(LocalDate fechaini, LocalDate fechafin);

    @Query(value="Select le.nombre, count(q.idquejas) as cantQuejas \n" +
            "FROM horario as hor \n" +
            "left join cronograma as cro on cro.idcronograma=hor.fidcronograma \n" +
            "left join horariolugarentrega as horle on hor.fidhorariolugarentrega=horle.idhorariolugarentrega\n" +
            "left join lugarentrega as le on le.idlugarentrega=horle.fidlugarentrega\n" +
            "inner join quejas as q on hor.idhorario=q.fidhorario\n" +
            "where cro.estado=\"PUB\" and q.fechacreacion>=?2 and q.fechacreacion<=?3 and q.estado=\"ACT\" and le.fiddistrito in ?1\n" +
            "group by le.idlugarentrega\n" +
            "order by cantQuejas desc\n" +
            "limit 5",nativeQuery=true)
    ArrayList<ArrayList<Object>> topPeoresLugaresXDistrito(List<Integer> listaDistritos,LocalDate fechaini, LocalDate fechafin);

    @Query(value="Select le.nombre, count(q.idquejas) as cantQuejas \n" +
            "FROM horario as hor \n" +
            "left join cronograma as cro on cro.idcronograma=hor.fidcronograma \n" +
            "left join horariolugarentrega as horle on hor.fidhorariolugarentrega=horle.idhorariolugarentrega\n" +
            "left join lugarentrega as le on le.idlugarentrega=horle.fidlugarentrega\n" +
            "inner join quejas as q on hor.idhorario=q.fidhorario\n" +
            "inner join distrito as d on d.iddistrito=le.fiddistrito\n" +
            "where cro.estado=\"PUB\" and q.fechacreacion>=?2 and q.fechacreacion<=?3 and q.estado=\"ACT\" and d.fidprovincia in ?1\n" +
            "group by le.idlugarentrega\n" +
            "order by cantQuejas desc\n" +
            "limit 5",nativeQuery=true)
    ArrayList<ArrayList<Object>> topPeoresLugaresXProvincia(List<Integer> listaProvincias,LocalDate fechaini, LocalDate fechafin);

    @Query(value="Select le.nombre, count(q.idquejas) as cantQuejas \n" +
            "FROM horario as hor \n" +
            "left join cronograma as cro on cro.idcronograma=hor.fidcronograma \n" +
            "left join horariolugarentrega as horle on hor.fidhorariolugarentrega=horle.idhorariolugarentrega\n" +
            "left join lugarentrega as le on le.idlugarentrega=horle.fidlugarentrega\n" +
            "inner join quejas as q on hor.idhorario=q.fidhorario\n" +
            "inner join distrito as d on d.iddistrito=le.fiddistrito\n" +
            "inner join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "where cro.estado=\"PUB\" and q.fechacreacion>=?2 and q.fechacreacion<=?3 and q.estado=\"ACT\" and p.fiddepartamento in ?1\n" +
            "group by le.idlugarentrega\n" +
            "order by cantQuejas desc\n" +
            "limit 5",nativeQuery=true)
    ArrayList<ArrayList<Object>> topPeoresLugaresXDepartamento(List<Integer> listaDepartamentos,LocalDate fechaini, LocalDate fechafin);


}
