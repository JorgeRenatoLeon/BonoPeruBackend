package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.Beneficiario;
import com.bonoperubackend.BonoPeruBackend.Modelos.RespuestaEncuesta;
import com.bonoperubackend.BonoPeruBackend.Modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RespuestaEncuestaRepository extends JpaRepository<RespuestaEncuesta,Integer> {
    Optional<RespuestaEncuesta> findAllByBeneficiario(Beneficiario beneficiario);
    Optional<RespuestaEncuesta> findByEstadoAndBeneficiario_IdbeneficiarioAndHorario_IdHorario(String estado, Integer fidbeneficiario, Integer fidhorario);
    Optional<RespuestaEncuesta> findByBeneficiarioAndEstado(Beneficiario beneficiario,String estado);

    @Query(value="SELECT I.puntaje, COUNT(I.puntaje) as cantPuntaje " +
            "FROM horario H, cronograma C, respuestaencuesta E, respuestaindividual I, pregunta P " +
            "WHERE C.idcronograma = ?1 AND C.idcronograma = H.fidcronograma AND H.idhorario = E.fidhorario AND E.estado = 'RES' " +
            "AND E.idrespuestaencuesta = I.fidrespuestaencuesta AND I.fidpregunta = P.idpregunta AND P.idpregunta = 1 " +
            "GROUP BY I.puntaje ORDER BY I.puntaje ASC;",nativeQuery=true)
    List<Object> findRespuestas1(int idcronograma);

    @Query(value="SELECT I.puntaje, COUNT(I.puntaje) as cantPuntaje " +
            "FROM horario H, cronograma C, respuestaencuesta E, respuestaindividual I, pregunta P " +
            "WHERE C.idcronograma = ?1 AND C.idcronograma = H.fidcronograma AND H.idhorario = E.fidhorario AND E.estado = 'RES' " +
            "AND E.idrespuestaencuesta = I.fidrespuestaencuesta AND I.fidpregunta = P.idpregunta AND P.idpregunta = 2 " +
            "GROUP BY I.puntaje ORDER BY I.puntaje ASC;",nativeQuery=true)
    List<Object> findRespuestas2(int idcronograma);

    @Query(value="SELECT I.respuesta, COUNT(I.respuesta) as cantRespuesta " +
            "FROM horario H, cronograma C, respuestaencuesta E, respuestaindividual I, pregunta P " +
            "WHERE C.idcronograma = ?1 AND C.idcronograma = H.fidcronograma AND H.idhorario = E.fidhorario AND E.estado = 'RES' " +
            "AND E.idrespuestaencuesta = I.fidrespuestaencuesta AND I.fidpregunta = P.idpregunta AND P.idpregunta = 3 " +
            "GROUP BY I.respuesta ORDER BY I.respuesta ASC;",nativeQuery=true)
    List<Object> findRespuestas3(int idcronograma);

    @Query(value="SELECT I.respuesta, COUNT(I.respuesta) as cantRespuesta " +
            "FROM horario H, cronograma C, respuestaencuesta E, respuestaindividual I, pregunta P " +
            "WHERE C.idcronograma = ?1 AND C.idcronograma = H.fidcronograma AND H.idhorario = E.fidhorario AND E.estado = 'RES' " +
            "AND E.idrespuestaencuesta = I.fidrespuestaencuesta AND I.fidpregunta = P.idpregunta AND P.idpregunta = 4 " +
            "GROUP BY I.respuesta ORDER BY I.respuesta ASC;",nativeQuery=true)
    List<Object> findRespuestas4(int idcronograma);

    @Query(value="SELECT I.respuesta, COUNT(I.respuesta) as cantRespuesta " +
            "FROM horario H, cronograma C, respuestaencuesta E, respuestaindividual I, pregunta P " +
            "WHERE C.idcronograma = ?1 AND C.idcronograma = H.fidcronograma AND H.idhorario = E.fidhorario AND E.estado = 'RES' " +
            "AND E.idrespuestaencuesta = I.fidrespuestaencuesta AND I.fidpregunta = P.idpregunta AND P.idpregunta = 5 " +
            "GROUP BY respuesta ORDER BY I.respuesta ASC;",nativeQuery=true)
    List<Object> findRespuestas5(int idcronograma);
}
