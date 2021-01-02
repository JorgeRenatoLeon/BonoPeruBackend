package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.DescargaCronograma;
import com.bonoperubackend.BonoPeruBackend.Modelos.Horario;
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
public interface HorarioRepository extends JpaRepository<Horario,Integer> {

    @Query(value="select h.* from cronograma as c inner join horario as h on c.idcronograma=h.fidcronograma inner join beneficiario as b on h.fidbeneficiario=b.idbeneficiario inner join horariolugarentrega as hor on h.fidhorariolugarentrega=hor.idhorariolugarentrega inner join lugarentrega as l on hor.fidlugarentrega=l.idlugarentrega\n" +
            "where l.idlugarentrega=?1 and b.codigofamilia LIKE %?2% and h.horainicio<=?3 and h.fecha=?4 and c.estado='PUB' and b.estado='ACT' and hor.estado='ACT' and l.estado='ACT'",nativeQuery=true)
    Optional<Horario> findhorarios(Integer idlugarentrega, String codigofamilia,LocalTime horaini, LocalDate dia);

    @Query(value="select h.* from horario as h inner join beneficiario as b on h.fidbeneficiario=b.idbeneficiario \n" +
            "inner join cronograma as c on c.idcronograma=h.fidcronograma where b.codigofamilia LIKE %?1% and h.estado='NOE' and b.estado='ACT' and c.estado='PUB'",nativeQuery=true)
    List<Horario> findAllByBeneficiario(String codigofamilia);

    ArrayList <Horario> findHorariosByBeneficiario_CodigofamiliaAndCronograma_Estado(String codigofamilia, String estado);


    List<Horario> findAllByCronograma_EstadoOrCronograma_Estado(String estado, String estado2);

    ArrayList<Horario> findAllByCronograma_IdCronograma(Integer idcronograma);

    ArrayList<Horario> findAllByCronograma_IdCronogramaAndFechaGreaterThanEqualAndFechaLessThanEqual(Integer idcronograma,LocalDate fechainicial,LocalDate fechafinal);

    ArrayList<Horario> findAllByCronograma_IdCronogramaAndFechaGreaterThanEqualAndFechaLessThanEqualAndHorariolugarentrega_Lugarentrega_NombreLike(Integer idcronograma,LocalDate fechainicial,LocalDate fechafinal, String nombre);

    Optional<Integer> countHorarioByCronograma_EstadoAndBeneficiario_Femenino(String estado, Boolean valor);

    @Query(value="SELECT hor.fecha as fecha, sum(if(STRCMP(hor.estado,\"ENT\") = 0,1,0)) as cantidad\n" +
            "FROM horario as hor \n" +
            "left join cronograma as cro on cro.idcronograma=hor.fidcronograma \n" +
            "where cro.estado=\"PUB\" and hor.fecha>=?1 and hor.fecha<=?2  \n" +
            "group by hor.fecha\n" +
            "order by hor.fecha",nativeQuery=true)
    ArrayList<ArrayList<Object>> monitoreoEntrega(LocalDate fechaini, LocalDate fechafin);

    @Query(value="SELECT hor.fecha as fecha, sum(if(STRCMP(hor.estado,\"ENT\") = 0,1,0)) as cantidad\n" +
            "FROM horario as hor \n" +
            "left join cronograma as cro on cro.idcronograma=hor.fidcronograma \n" +
            "left join horariolugarentrega as horle on hor.fidhorariolugarentrega=horle.idhorariolugarentrega\n" +
            "left join lugarentrega as le on le.idlugarentrega=horle.fidlugarentrega\n" +
            "left join distrito as d on d.iddistrito=le.fiddistrito\n" +
            "left join provincia as p on d.fidprovincia=p.idprovincia\n" +
            "where cro.estado=\"PUB\" and hor.fecha>=?2 and hor.fecha<=?3  and p.fiddepartamento in ?1 \n" +
            "group by hor.fecha\n" +
            "order by hor.fecha",nativeQuery=true)
    ArrayList<ArrayList<Object>> monitoreoEntregaXDepartamento(List<Integer> listaDepartamentos,LocalDate fechaini, LocalDate fechafin);

    @Query(value="SELECT hor.fecha as fecha, sum(if(STRCMP(hor.estado,\"ENT\") = 0,1,0)) as cantidad\n" +
            "FROM horario as hor \n" +
            "left join cronograma as cro on cro.idcronograma=hor.fidcronograma \n" +
            "left join horariolugarentrega as horle on hor.fidhorariolugarentrega=horle.idhorariolugarentrega\n" +
            "left join lugarentrega as le on le.idlugarentrega=horle.fidlugarentrega\n" +
            "left join distrito as d on d.iddistrito=le.fiddistrito\n" +
            "where cro.estado=\"PUB\" and hor.fecha>=?2 and hor.fecha<=?3  and d.fidprovincia in ?1 \n" +
            "group by hor.fecha\n" +
            "order by hor.fecha",nativeQuery=true)
    ArrayList<ArrayList<Object>> monitoreoEntregaXProvincia(List<Integer> listaProvincias,LocalDate fechaini, LocalDate fechafin);

    @Query(value="SELECT hor.fecha as fecha, sum(if(STRCMP(hor.estado,\"ENT\") = 0,1,0)) as cantidad\n" +
            "FROM horario as hor \n" +
            "left join cronograma as cro on cro.idcronograma=hor.fidcronograma \n" +
            "left join horariolugarentrega as horle on hor.fidhorariolugarentrega=horle.idhorariolugarentrega\n" +
            "left join lugarentrega as le on le.idlugarentrega=horle.fidlugarentrega\n" +
            "where cro.estado=\"PUB\" and hor.fecha>=?2 and hor.fecha<=?3  and le.fiddistrito in ?1 \n" +
            "group by hor.fecha\n" +
            "order by hor.fecha",nativeQuery=true)
    ArrayList<ArrayList<Object>> monitoreoEntregaXDistrito(List<Integer> listaDistritos,LocalDate fechaini, LocalDate fechafin);

    @Query(value="select * from horario\n" +
            "where fidcronograma=?1 and fecha in ?2 and\n" +
            "horainicio in ?3 and horafin in ?4\n" +
            "and (estado='NOE' or estado='ENT');",nativeQuery=true)
    ArrayList<Horario> descargarConTodosLosFiltros(Integer idcronograma, List<LocalDate> fechas, List<LocalTime> horainicio, List<LocalTime> horafin);

    ArrayList<Horario> findAllByFechaGreaterThanEqualAndFechaLessThanEqual(LocalDate fechainicial,LocalDate fechafinal);

    ArrayList<Horario> findAllByFechaGreaterThanEqualAndFechaLessThanEqualAndCronograma_Estado(LocalDate fechainicial,LocalDate fechafinal, String estado);

    ArrayList<Horario> findAllByFechaGreaterThan(LocalDate fechainicial);

    ArrayList<Horario> findAllByFechaGreaterThanAndFechaLessThanEqual(LocalDate fechainicial,LocalDate fechafinal);

    ArrayList<Horario> findAllByFechaGreaterThanAndFechaLessThanEqualAndCronograma_Estado(LocalDate fechainicial,LocalDate fechafinal, String estado);

    @Query(value="select h.* from horario as h\n" +
            "inner join beneficiario as b on b.idbeneficiario=h.fidbeneficiario\n" +
            "inner join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "inner join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "inner join departamento as j on j.iddepartamento=p.fiddepartamento\n" +
            "where h.fidcronograma=?1 and h.fecha>=?2 and h.fecha<=?3\n" +
            "and j.iddepartamento=?4 and p.idprovincia=?5 and d.iddistrito=?6 and b.codigofamilia like %?7%",nativeQuery=true)
    ArrayList<Horario> listarhorariosbeneficiarios1(Integer idcronograma,LocalDate fechainicial,LocalDate fechafinal,Integer iddepartamento,Integer idprovincia,Integer iddistrito, String codigofamilia);

    @Query(value="select h.* from horario as h\n" +
            "inner join beneficiario as b on b.idbeneficiario=h.fidbeneficiario\n" +
            "inner join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "inner join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "inner join departamento as j on j.iddepartamento=p.fiddepartamento\n" +
            "where h.fidcronograma=?1 and h.fecha>=?2 and h.fecha<=?3\n" +
            "and j.iddepartamento=?4 and p.idprovincia=?5 and b.codigofamilia like %?6%",nativeQuery=true)
    ArrayList<Horario> listarhorariosbeneficiarios2(Integer idcronograma,LocalDate fechainicial,LocalDate fechafinal,Integer iddepartamento,Integer idprovincia,String codigofamilia);

    @Query(value="select h.* from horario as h\n" +
            "inner join beneficiario as b on b.idbeneficiario=h.fidbeneficiario\n" +
            "inner join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "inner join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "inner join departamento as j on j.iddepartamento=p.fiddepartamento\n" +
            "where h.fidcronograma=?1 and h.fecha>=?2 and h.fecha<=?3\n" +
            "and j.iddepartamento=?4 and b.codigofamilia like %?5%",nativeQuery=true)
    ArrayList<Horario> listarhorariosbeneficiarios3(Integer idcronograma,LocalDate fechainicial,LocalDate fechafinal,Integer iddepartamento,String codigofamilia);

    @Query(value="select h.* from horario as h\n" +
            "inner join beneficiario as b on b.idbeneficiario=h.fidbeneficiario\n" +
            "inner join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "inner join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "inner join departamento as j on j.iddepartamento=p.fiddepartamento\n" +
            "where h.fidcronograma=?1 and h.fecha>=?2 and h.fecha<=?3\n" +
            "and b.codigofamilia like %?4%",nativeQuery=true)
    ArrayList<Horario> listarhorariosbeneficiarios4(Integer idcronograma,LocalDate fechainicial,LocalDate fechafinal,String codigofamilia);


    @Query(value="select sum(if(h.fecha<=?3 and strcmp(h.estado,\"ENT\")=0,1,0)) as entregados,\n" +
            "sum(if(h.fecha<=?3 and strcmp(h.estado,\"NOE\")=0,1,0)) as noentregados,\n" +
            "sum(if(h.fecha>?3,1,0)) as pendientes\n" +
            " from horario as h\n" +
            "inner join cronograma as c\n" +
            "on c.idcronograma=h.fidcronograma\n" +
            "where c.estado=\"PUB\" and h.fecha>=?1 and h.fecha<=?2",nativeQuery = true)
    ArrayList<ArrayList<Integer>> avanceentregamonitoreo(LocalDate fechaini, LocalDate fechafin, LocalDate fechaactual);

    @Query(value="select h.fidbeneficiario, b.codigofamilia, concat(dep.nombre,\"-\", p.nombre, \"-\",d.nombre) as ubicacion,\n" +
            "h.fecha, h.horainicio, h.horafin,  le.nombre, le.direccion, h.estado  from horario h\n" +
            "inner join beneficiario as b on b.idbeneficiario=h.fidbeneficiario\n" +
            "inner join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "inner join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "inner join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "inner join horariolugarentrega as hle on hle.idhorariolugarentrega=h.fidhorariolugarentrega\n" +
            "inner join lugarentrega as le on hle.fidlugarentrega=le.idlugarentrega\n" +
            "where h.fidcronograma=?1 and h.fecha>=?2 and h.fecha<=?3 and b.codigofamilia like %?4%\n" +
            "order by b.codigofamilia",nativeQuery=true)
    ArrayList<ArrayList<Object>> listarhorarios(Integer idcronograma,LocalDate fechainicial,LocalDate fechafinal,String codigofamilia);

    @Query(value="select h.fidbeneficiario, b.codigofamilia, concat(dep.nombre,\"-\", p.nombre, \"-\",d.nombre) as ubicacion,\n" +
            "h.fecha, h.horainicio, h.horafin,  le.nombre, le.direccion, h.estado  from horario h\n" +
            "inner join beneficiario as b on b.idbeneficiario=h.fidbeneficiario\n" +
            "inner join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "inner join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "inner join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "inner join horariolugarentrega as hle on hle.idhorariolugarentrega=h.fidhorariolugarentrega\n" +
            "inner join lugarentrega as le on hle.fidlugarentrega=le.idlugarentrega\n" +
            "where h.fidcronograma=?1 and h.fecha>=?2 and h.fecha<=?3 and b.codigofamilia like %?4% \n" +
            "and dep.iddepartamento=?5 \n" +
            "order by b.codigofamilia",nativeQuery=true)
    ArrayList<ArrayList<Object>> listarhorariosXDepartamento(Integer idcronograma,LocalDate fechainicial,LocalDate fechafinal,String codigofamilia, Integer iddepartamento);

    @Query(value="select h.fidbeneficiario, b.codigofamilia, concat(dep.nombre,\"-\", p.nombre, \"-\",d.nombre) as ubicacion,\n" +
            "h.fecha, h.horainicio, h.horafin,  le.nombre, le.direccion, h.estado  from horario h\n" +
            "inner join beneficiario as b on b.idbeneficiario=h.fidbeneficiario\n" +
            "inner join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "inner join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "inner join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "inner join horariolugarentrega as hle on hle.idhorariolugarentrega=h.fidhorariolugarentrega\n" +
            "inner join lugarentrega as le on hle.fidlugarentrega=le.idlugarentrega\n" +
            "where h.fidcronograma=?1 and h.fecha>=?2 and h.fecha<=?3 and b.codigofamilia like %?4% \n" +
            "and dep.iddepartamento=?5 and p.idprovincia=?6 \n" +
            "order by b.codigofamilia",nativeQuery=true)
    ArrayList<ArrayList<Object>> listarhorariosXProvincia(Integer idcronograma,LocalDate fechainicial,LocalDate fechafinal,String codigofamilia, Integer iddepartamento, Integer idprovincia);

    @Query(value="select h.fidbeneficiario, b.codigofamilia, concat(dep.nombre,\"-\", p.nombre, \"-\",d.nombre) as ubicacion,\n" +
            "h.fecha, h.horainicio, h.horafin,  le.nombre, le.direccion, h.estado  from horario h\n" +
            "inner join beneficiario as b on b.idbeneficiario=h.fidbeneficiario\n" +
            "inner join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "inner join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "inner join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "inner join horariolugarentrega as hle on hle.idhorariolugarentrega=h.fidhorariolugarentrega\n" +
            "inner join lugarentrega as le on hle.fidlugarentrega=le.idlugarentrega\n" +
            "where h.fidcronograma=?1 and h.fecha>=?2 and h.fecha<=?3 and b.codigofamilia like %?4% \n" +
            "and dep.iddepartamento=?5 and p.idprovincia=?6 and d.iddistrito=?7 \n" +
            "order by b.codigofamilia",nativeQuery=true)
    ArrayList<ArrayList<Object>> listarhorariosXDistrito(Integer idcronograma,LocalDate fechainicial,LocalDate fechafinal,String codigofamilia, Integer iddepartamento, Integer idprovincia,Integer iddistrito);

}
