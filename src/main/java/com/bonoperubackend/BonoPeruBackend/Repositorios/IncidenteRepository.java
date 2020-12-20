package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface IncidenteRepository extends JpaRepository<Incidente,Integer> {

    @Query(value="select sum(if(STRCMP(i.tipoincidente,\"Lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(i.tipoincidente,\"Horario\") = 0,1,0)) as horario,\n" +
            "sum(if(STRCMP(i.tipoincidente,\"Día\") = 0,1,0)) as  dias \n" +
            "from incidente as i\n" +
            "where i.fecha>=?1 \n" +
            "and i.fecha<=?2",nativeQuery = true)
    ArrayList<ArrayList<Integer>> reporteIncidentes(LocalDate fechaini, LocalDate fechafin);

    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(i.tipoincidente,\"Lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(i.tipoincidente,\"Horario\") = 0,1,0)) as horario,\n" +
            "sum(if(STRCMP(i.tipoincidente,\"Día\") = 0,1,0)) as  dias\n" +
            "from incidente as i\n" +
            "left join cronograma as c on c.idcronograma=i.fidcronograma\n" +
            "left join beneficiario as b on b.idbeneficiario=i.fidbeneficiario\n" +
            "left join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where i.fecha>=?1 \n" +
            "and i.fecha<=?2 and dep.iddepartamento=?3 and p.idprovincia=?4 and d.iddistrito=?5 \n" +
            "and c.idcronograma in ?6\n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reporteIncidentesCronograma(LocalDate fechaini, LocalDate fechafin, Integer iddepartamento, Integer idprovincia, Integer iddistrito, List<Integer> cronogramas);

    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(i.tipoincidente,\"Lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(i.tipoincidente,\"Horario\") = 0,1,0)) as horario,\n" +
            "sum(if(STRCMP(i.tipoincidente,\"Día\") = 0,1,0)) as  dias\n" +
            "from incidente as i\n" +
            "left join cronograma as c on c.idcronograma=i.fidcronograma\n" +
            "left join beneficiario as b on b.idbeneficiario=i.fidbeneficiario\n" +
            "left join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where i.fecha>=?1 \n" +
            "and i.fecha<=?2 and dep.iddepartamento=?3 and p.idprovincia=?4 \n" +
            "and c.idcronograma in ?5\n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reporteIncidentesCronograma1(LocalDate fechaini, LocalDate fechafin, Integer iddepartamento, Integer idprovincia, List<Integer> cronogramas);

    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(i.tipoincidente,\"Lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(i.tipoincidente,\"Horario\") = 0,1,0)) as horario,\n" +
            "sum(if(STRCMP(i.tipoincidente,\"Día\") = 0,1,0)) as  dias\n" +
            "from incidente as i\n" +
            "left join cronograma as c on c.idcronograma=i.fidcronograma\n" +
            "left join beneficiario as b on b.idbeneficiario=i.fidbeneficiario\n" +
            "left join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where i.fecha>=?1 \n" +
            "and i.fecha<=?2 and dep.iddepartamento=?3 \n" +
            "and c.idcronograma in ?4\n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reporteIncidentesCronograma2(LocalDate fechaini, LocalDate fechafin, Integer iddepartamento, List<Integer> cronogramas);

    @Query(value="select c.idcronograma, c.nombre, sum(if(STRCMP(i.tipoincidente,\"Lugar\") = 0,1,0)) as lugares, \n" +
            "sum(if(STRCMP(i.tipoincidente,\"Horario\") = 0,1,0)) as horario,\n" +
            "sum(if(STRCMP(i.tipoincidente,\"Día\") = 0,1,0)) as  dias\n" +
            "from incidente as i\n" +
            "left join cronograma as c on c.idcronograma=i.fidcronograma\n" +
            "left join beneficiario as b on b.idbeneficiario=i.fidbeneficiario\n" +
            "left join distrito as d on d.iddistrito=b.fiddistrito\n" +
            "left join provincia as p on p.idprovincia=d.fidprovincia\n" +
            "left join departamento as dep on dep.iddepartamento=p.fiddepartamento\n" +
            "where i.fecha>=?1 \n" +
            "and i.fecha<=?2\n" +
            "and c.idcronograma in ?3\n" +
            "group by c.idcronograma",nativeQuery=true)
    ArrayList<ArrayList<Object>> reporteIncidentesCronograma3(LocalDate fechaini, LocalDate fechafin, List<Integer> cronogramas);


}
