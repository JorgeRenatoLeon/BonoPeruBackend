package com.bonoperubackend.BonoPeruBackend.Repositorios;


import com.bonoperubackend.BonoPeruBackend.Modelos.Horario;
import com.bonoperubackend.BonoPeruBackend.Modelos.Quejas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuejasRepository extends JpaRepository<Quejas,Integer>{
    @Query(value="select count(que.idquejas)\n" +
            "from quejas as que inner join horario as hor on que.fidhorario = hor.idhorario inner join cronograma as\n" +
            "cro on cro.idcronograma=hor.fidcronograma\n" +
            "where que.estado='ACT' and cro.estado='PUB' ;",nativeQuery=true)
    Integer contarQuejas();

    Integer countQuejasByHorario_Cronograma_EstadoAndEstado(String estadoCro, String estadoQueja);
    ArrayList<Quejas> findAllByHorario_IdHorarioAndBeneficiario_IdbeneficiarioAndLugarentrega_IdLugarentrega(Integer fidhorario,Integer fidbeneficiario, Integer fidlugarentrega);
    Optional<Quejas> findByBeneficiario_IdbeneficiarioAndHorario_IdHorarioAndLugarentrega_IdLugarentregaAndTipoQueja(Integer fidbeneficiario, Integer fidhorario, Integer fidlugarentrega, String tipoqueja);


    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(q.tipoqueja,\"lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(q.tipoqueja,\"horario\") = 0,1,0)) as horarios\n" +
            "from quejas as q\n" +
            "left join horario as h on h.idhorario=q.fidhorario\n" +
            "left join cronograma as c on c.idcronograma=h.fidcronograma\n" +
            "left join lugarentrega as l on l.idlugarentrega=q.fidlugarentrega\n" +
            "left join distrito as d on d.iddistrito=l.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where q.fechacreacion>=?1 \n" +
            "and q.fechacreacion<=?2 and dep.iddepartamento=?3 and p.idprovincia=?4 and d.iddistrito=?5 \n" +
            "and c.nombre in ?6\n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reportequejas(LocalDate fechaini, LocalDate fechafin, Integer iddepartamento, Integer idprovincia, Integer iddistrito, List<String> cronogramas);

    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(q.tipoqueja,\"lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(q.tipoqueja,\"horario\") = 0,1,0)) as horarios\n" +
            "from quejas as q\n" +
            "left join horario as h on h.idhorario=q.fidhorario\n" +
            "left join cronograma as c on c.idcronograma=h.fidcronograma\n" +
            "left join lugarentrega as l on l.idlugarentrega=q.fidlugarentrega\n" +
            "left join distrito as d on d.iddistrito=l.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where q.fechacreacion>=?1 \n" +
            "and q.fechacreacion<=?2 and dep.iddepartamento=?3 and p.idprovincia=?4 \n" +
            "and c.nombre in ?5\n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reportequejas1(LocalDate fechaini, LocalDate fechafin, Integer iddepartamento, Integer idprovincia, List<String> cronogramas);

    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(q.tipoqueja,\"lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(q.tipoqueja,\"horario\") = 0,1,0)) as horarios\n" +
            "from quejas as q\n" +
            "left join horario as h on h.idhorario=q.fidhorario\n" +
            "left join cronograma as c on c.idcronograma=h.fidcronograma\n" +
            "left join lugarentrega as l on l.idlugarentrega=q.fidlugarentrega\n" +
            "left join distrito as d on d.iddistrito=l.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where q.fechacreacion>=?1 \n" +
            "and q.fechacreacion<=?2 and dep.iddepartamento=?3 \n" +
            "and c.nombre in ?4\n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reportequejas2(LocalDate fechaini, LocalDate fechafin, Integer iddepartamento, List<String> cronogramas);

    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(q.tipoqueja,\"lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(q.tipoqueja,\"horario\") = 0,1,0)) as horarios\n" +
            "from quejas as q\n" +
            "left join horario as h on h.idhorario=q.fidhorario\n" +
            "left join cronograma as c on c.idcronograma=h.fidcronograma\n" +
            "left join lugarentrega as l on l.idlugarentrega=q.fidlugarentrega\n" +
            "left join distrito as d on d.iddistrito=l.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where q.fechacreacion>=?1 \n" +
            "and q.fechacreacion<=?2 \n" +
            "and c.nombre in ?3\n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reportequejas3(LocalDate fechaini, LocalDate fechafin,List<String> cronogramas);



    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(q.tipoqueja,\"lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(q.tipoqueja,\"horario\") = 0,1,0)) as horarios\n" +
            "from quejas as q\n" +
            "left join horario as h on h.idhorario=q.fidhorario\n" +
            "left join cronograma as c on c.idcronograma=h.fidcronograma\n" +
            "left join lugarentrega as l on l.idlugarentrega=q.fidlugarentrega\n" +
            "left join distrito as d on d.iddistrito=l.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where q.fechacreacion>=?1 \n" +
            "and q.fechacreacion<=?2 and dep.iddepartamento=?3 and p.idprovincia=?4 and d.iddistrito=?5 \n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reportequejas4(LocalDate fechaini, LocalDate fechafin, Integer iddepartamento, Integer idprovincia, Integer iddistrito);

    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(q.tipoqueja,\"lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(q.tipoqueja,\"horario\") = 0,1,0)) as horarios\n" +
            "from quejas as q\n" +
            "left join horario as h on h.idhorario=q.fidhorario\n" +
            "left join cronograma as c on c.idcronograma=h.fidcronograma\n" +
            "left join lugarentrega as l on l.idlugarentrega=q.fidlugarentrega\n" +
            "left join distrito as d on d.iddistrito=l.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where q.fechacreacion>=?1 \n" +
            "and q.fechacreacion<=?2 and dep.iddepartamento=?3 and p.idprovincia=?4 \n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reportequejas5(LocalDate fechaini, LocalDate fechafin, Integer iddepartamento, Integer idprovincia);

    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(q.tipoqueja,\"lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(q.tipoqueja,\"horario\") = 0,1,0)) as horarios\n" +
            "from quejas as q\n" +
            "left join horario as h on h.idhorario=q.fidhorario\n" +
            "left join cronograma as c on c.idcronograma=h.fidcronograma\n" +
            "left join lugarentrega as l on l.idlugarentrega=q.fidlugarentrega\n" +
            "left join distrito as d on d.iddistrito=l.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where q.fechacreacion>=?1 \n" +
            "and q.fechacreacion<=?2 and dep.iddepartamento=?3 \n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reportequejas6(LocalDate fechaini, LocalDate fechafin, Integer iddepartamento);

    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(q.tipoqueja,\"lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(q.tipoqueja,\"horario\") = 0,1,0)) as horarios\n" +
            "from quejas as q\n" +
            "left join horario as h on h.idhorario=q.fidhorario\n" +
            "left join cronograma as c on c.idcronograma=h.fidcronograma\n" +
            "left join lugarentrega as l on l.idlugarentrega=q.fidlugarentrega\n" +
            "left join distrito as d on d.iddistrito=l.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where q.fechacreacion>=?1 \n" +
            "and q.fechacreacion<=?2 \n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reportequejas7(LocalDate fechaini, LocalDate fechafin);


    @Query(value="select sum(if(STRCMP(q.tipoqueja,\"Lugar\") = 0,1,0))  as lugares,\n" +
            "  sum(if(STRCMP(q.tipoqueja,\"Horario\") = 0,1,0)) as horario\n" +
            "  from quejas as q;",nativeQuery = true)
    ArrayList<ArrayList<Integer>> reporteQuejas(LocalDate fechaini, LocalDate fechafin);


}
