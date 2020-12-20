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
            "where l.idlugarentrega=?1 and b.codigofamilia LIKE %?2% and h.horainicio<=?3 and h.fecha=?4 and c.estado='PUB' and h.estado='NOE' and b.estado='ACT' and hor.estado='ACT' and l.estado='ACT'",nativeQuery=true)
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
            "where cro.estado=\"PUB\"\n" +
            "group by hor.fecha\n" +
            "order by hor.fecha",nativeQuery=true)
    ArrayList<ArrayList<Object>> monitoreoEntrega();

    @Query(value="select * from horario\n" +
            "where fidcronograma=?1 and fecha in ?2 and\n" +
            "horainicio in ?3 and horafin in ?4\n" +
            "and (estado='NOE' or estado='ENT');",nativeQuery=true)
    ArrayList<Horario> descargarConTodosLosFiltros(Integer idcronograma, List<LocalDate> fechas, List<LocalTime> horainicio, List<LocalTime> horafin);

}
