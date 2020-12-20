package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.DescargaCronograma;
import com.bonoperubackend.BonoPeruBackend.Modelos.ListaCronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface DescargaCronogramaRepository extends JpaRepository<DescargaCronograma,Integer> {
    @Query(value="select l.idlugarentrega,d.nombre as departamento, p.nombre as provincia,\n" +
            "dis.nombre as distrito, l.nombre as lugar, h.fecha as fecha, h.horainicio as horainicio,\n" +
            "h.horafin as horafin, b.codigofamilia\n" +
            "from cronograma as c\n" +
            "inner join horario as h on c.idcronograma=h.fidcronograma\n" +
            "inner join beneficiario as b on h.fidbeneficiario=b.idbeneficiario\n" +
            "inner join distrito as dis on b.fiddistrito=dis.iddistrito\n" +
            "inner join provincia as p on dis.fidprovincia=p.idprovincia\n" +
            "inner join departamento as d on p.fiddepartamento=d.iddepartamento\n" +
            "inner join horariolugarentrega as hor on h.fidhorariolugarentrega=hor.idhorariolugarentrega\n" +
            "inner join lugarentrega as l on hor.fidlugarentrega=l.idlugarentrega\n" +
            "where c.idcronograma=?1 and d.iddepartamento=?2 and p.idprovincia=?3 and dis.iddistrito=?4 and\n" +
            "h.fecha>=?5 and h.fecha<=?6 and l.nombre like %?7% \n" +
            "and l.idlugarentrega in ?8 and c.estado='PUB'\n" +
            "and h.estado='NOE' or h.estado='ENT' and b.estado='ACT' and dis.estado='ACT'\n" +
            "and p.estado='ACT' and d.estado='ACT' and hor.estado='ACT' and l.estado='ACT'\n" +
            "group by h.idhorario order by l.nombre ASC;",nativeQuery=true)
    ArrayList<DescargaCronograma> descargarConTodosLosFiltros(Integer idcronograma, Integer iddepartamento, Integer idprovincia,
                                                              Integer iddistrito, LocalDate fechaini, LocalDate fechafin,
                                                              String nombre, List<Integer> numeros);
    @Query(value="select l.idlugarentrega,d.nombre as departamento, p.nombre as provincia,\n" +
            "dis.nombre as distrito, l.nombre as lugar, h.fecha as fecha, h.horainicio as horainicio,\n" +
            "h.horafin as horafin, b.codigofamilia\n" +
            "from cronograma as c\n" +
            "inner join horario as h on c.idcronograma=h.fidcronograma\n" +
            "inner join beneficiario as b on h.fidbeneficiario=b.idbeneficiario\n" +
            "inner join distrito as dis on b.fiddistrito=dis.iddistrito\n" +
            "inner join provincia as p on dis.fidprovincia=p.idprovincia\n" +
            "inner join departamento as d on p.fiddepartamento=d.iddepartamento\n" +
            "inner join horariolugarentrega as hor on h.fidhorariolugarentrega=hor.idhorariolugarentrega\n" +
            "inner join lugarentrega as l on hor.fidlugarentrega=l.idlugarentrega\n" +
            "where c.idcronograma=?1 and d.iddepartamento=?2 and p.idprovincia=?3 and\n" +
            "h.fecha>=?4 and h.fecha<=?5 and l.nombre like %?6% \n" +
            "and l.idlugarentrega in ?7 and c.estado='PUB'\n" +
            "and h.estado='NOE' or h.estado='ENT' and b.estado='ACT' and dis.estado='ACT'\n" +
            "and p.estado='ACT' and d.estado='ACT' and hor.estado='ACT' and l.estado='ACT'\n" +
            "group by h.idhorario order by l.nombre ASC;",nativeQuery=true)
    ArrayList<DescargaCronograma> descargarSinDistrito(Integer idcronograma, Integer iddepartamento, Integer idprovincia,
                                                        LocalDate fechaini, LocalDate fechafin,String nombre, List<Integer> numeros);
    @Query(value="select l.idlugarentrega,d.nombre as departamento, p.nombre as provincia,\n" +
            "dis.nombre as distrito, l.nombre as lugar, h.fecha as fecha, h.horainicio as horainicio,\n" +
            "h.horafin as horafin, b.codigofamilia\n" +
            "from cronograma as c\n" +
            "inner join horario as h on c.idcronograma=h.fidcronograma\n" +
            "inner join beneficiario as b on h.fidbeneficiario=b.idbeneficiario\n" +
            "inner join distrito as dis on b.fiddistrito=dis.iddistrito\n" +
            "inner join provincia as p on dis.fidprovincia=p.idprovincia\n" +
            "inner join departamento as d on p.fiddepartamento=d.iddepartamento\n" +
            "inner join horariolugarentrega as hor on h.fidhorariolugarentrega=hor.idhorariolugarentrega\n" +
            "inner join lugarentrega as l on hor.fidlugarentrega=l.idlugarentrega\n" +
            "where c.idcronograma=?1 and d.iddepartamento=?2 and\n" +
            "h.fecha>=?3 and h.fecha<=?4 and l.nombre like %?5% \n" +
            "and l.idlugarentrega in ?6 and c.estado='PUB'\n" +
            "and h.estado='NOE' or h.estado='ENT' and b.estado='ACT' and dis.estado='ACT'\n" +
            "and p.estado='ACT' and d.estado='ACT' and hor.estado='ACT' and l.estado='ACT'\n" +
            "group by h.idhorario order by l.nombre ASC;",nativeQuery=true)
    ArrayList<DescargaCronograma> descargarSinProvincia(Integer idcronograma, Integer iddepartamento,LocalDate fechaini, LocalDate fechafin,
                                                       String nombre, List<Integer> numeros);
    @Query(value="select l.idlugarentrega,d.nombre as departamento, p.nombre as provincia,\n" +
            "dis.nombre as distrito, l.nombre as lugar, h.fecha as fecha, h.horainicio as horainicio,\n" +
            "h.horafin as horafin, b.codigofamilia\n" +
            "from cronograma as c\n" +
            "inner join horario as h on c.idcronograma=h.fidcronograma\n" +
            "inner join beneficiario as b on h.fidbeneficiario=b.idbeneficiario\n" +
            "inner join distrito as dis on b.fiddistrito=dis.iddistrito\n" +
            "inner join provincia as p on dis.fidprovincia=p.idprovincia\n" +
            "inner join departamento as d on p.fiddepartamento=d.iddepartamento\n" +
            "inner join horariolugarentrega as hor on h.fidhorariolugarentrega=hor.idhorariolugarentrega\n" +
            "inner join lugarentrega as l on hor.fidlugarentrega=l.idlugarentrega\n" +
            "where c.idcronograma=?1 and\n" +
            "h.fecha>=?2 and h.fecha<=?3 and l.nombre like %?4% \n" +
            "and l.idlugarentrega in ?5 and c.estado='PUB'\n" +
            "and h.estado='NOE' or h.estado='ENT' and b.estado='ACT' and dis.estado='ACT'\n" +
            "and p.estado='ACT' and d.estado='ACT' and hor.estado='ACT' and l.estado='ACT'\n" +
            "group by h.idhorario order by l.nombre ASC;",nativeQuery=true)
    ArrayList<DescargaCronograma> descargarSinDepartamento(Integer idcronograma, LocalDate fechaini, LocalDate fechafin,
                                                        String nombre, List<Integer> numeros);
}
