package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.ListaCronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ListaCronogramaRepository extends JpaRepository<ListaCronograma,Integer> {

    /*@Query(value="",nativeQuery=true)
    List<ListaCronograma> findAllByAllFiltro(Integer idcronograma, Integer iddepartamento, Integer idprovincia, Integer iddistrito, LocalDate fechaini,LocalDate fechafin,String nombre);
    @Query(value="",nativeQuery=true)
    List<ListaCronograma> findAllByAllMenosDistrito(Integer idcronograma, Integer iddepartamento, Integer idprovincia, LocalDate fechaini,LocalDate fechafin,String nombre);
    @Query(value="",nativeQuery=true)
    List<ListaCronograma> findAllByDepartamento(Integer idcronograma, Integer iddepartamento, LocalDate fechaini,LocalDate fechafin,String nombre);*/
    @Query(value="select l.idlugarentrega, l.nombre, concat(d.nombre,' - ',p.nombre,' - ', dis.nombre) as locacion,\n" +
            "h.fecha, h.horainicio,h.horafin, l.aforo as aforo,\n" +
            "sum(b.femenino) as mujeres,\n" +
            "sum(b.esdiscapacitado) as discapacitados,\n" +
            "dis.zonariesgo as riesgo, count(b.idbeneficiario) as beneficiarios from horario as h \n" +
            "inner join horariolugarentrega as hor on h.fidhorariolugarentrega=hor.idhorariolugarentrega \n" +
            "inner join lugarentrega as l on hor.fidlugarentrega=l.idlugarentrega\n" +
            "inner join distrito as dis on l.fiddistrito=dis.iddistrito \n" +
            "inner join provincia as p on dis.fidprovincia=p.idprovincia \n" +
            "inner join departamento as d on p.fiddepartamento=d.iddepartamento \n" +
            "left join beneficiario as b on h.fidbeneficiario=b.idbeneficiario \n" +
            "where h.fidcronograma = ?1 and h.fecha>=?2 and\n" +
            "h.fecha<=?3 and l.nombre like  %?4%  and \n" +
            "b.estado='ACT' and dis.estado='ACT' and p.estado='ACT' and \n" +
            "d.estado='ACT' and hor.estado='ACT' and \n" +
            "l.estado='ACT' and (h.estado='NOE' or h.estado='ENT')  \n" +
            "group by h.idhorario;",nativeQuery=true)
    ArrayList<ListaCronograma> findAll(Integer idcronograma, LocalDate fechaini, LocalDate fechafin, String nombre);

}
