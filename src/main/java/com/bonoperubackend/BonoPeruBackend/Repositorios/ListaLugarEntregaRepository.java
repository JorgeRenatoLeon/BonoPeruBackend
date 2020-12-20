package com.bonoperubackend.BonoPeruBackend.Repositorios;

import com.bonoperubackend.BonoPeruBackend.Modelos.ListaLugarEntrega;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListaLugarEntregaRepository extends JpaRepository<ListaLugarEntrega,Integer> {

    @Query(value="select l.idlugarentrega, l.codigo, l.nombre,concat(d.nombre,' - ',p.nombre,' - ',dis.nombre) as depprodis, v.nombre as tipo, l.direccion from lugarentrega as l inner join distrito as dis on l.fiddistrito=dis.iddistrito inner join provincia as p on dis.fidprovincia=p.idprovincia inner join departamento as d on p.fiddepartamento=d.iddepartamento inner join valores as v on v.tabla='lugarentrega' where d.iddepartamento=?1 and p.idprovincia=?2 and dis.iddistrito=?3 and l.nombre LIKE %?4% and dis.estado='ACT' and\n"+
            " p.estado='ACT' and d.estado='ACT' and l.estado='ACT' and v.abreviatura=l.tipo order by l.nombre ASC",
            countQuery = "select count(l.idlugarentrega) from lugarentrega as l \n" +
                    "inner join distrito as dis on l.fiddistrito=dis.iddistrito \n" +
                    "inner join provincia as p on dis.fidprovincia=p.idprovincia \n" +
                    "inner join departamento as d on p.fiddepartamento=d.iddepartamento \n" +
                    "inner join valores as v on v.tabla='lugarentrega' \n" +
                    "where d.iddepartamento=?1 and p.idprovincia=?2 and dis.iddistrito=?3 and l.nombre LIKE %?4% and dis.estado='ACT' \n" +
                    "and p.estado='ACT' and d.estado='ACT' and l.estado='ACT' and v.abreviatura=l.tipo", nativeQuery=true)
    List<ListaLugarEntrega> findAllByAllFiltro(Integer iddepartamento, Integer idprovincia, Integer iddistrito, String nombre);
    //, Pageable pageable

    @Query(value="select l.idlugarentrega, l.codigo, l.nombre,concat(d.nombre,' - ',p.nombre,' - ',dis.nombre) as depprodis, v.nombre as tipo, l.direccion from lugarentrega as l inner join distrito as dis on l.fiddistrito=dis.iddistrito inner join provincia as p on dis.fidprovincia=p.idprovincia inner join departamento as d on p.fiddepartamento=d.iddepartamento inner join valores as v on v.tabla='lugarentrega' where d.iddepartamento=?1 and p.idprovincia=?2 and l.nombre LIKE %?3% and dis.estado='ACT' and p.estado='ACT' and d.estado='ACT' and l.estado='ACT' and v.abreviatura=l.tipo order by l.nombre ASC",
            countQuery = "select count(l.idlugarentrega) from lugarentrega as l \n" +
                    "inner join distrito as dis on l.fiddistrito=dis.iddistrito \n" +
                    "inner join provincia as p on dis.fidprovincia=p.idprovincia \n" +
                    "inner join departamento as d on p.fiddepartamento=d.iddepartamento \n" +
                    "inner join valores as v on v.tabla='lugarentrega' \n" +
                    "where d.iddepartamento=?1 and p.idprovincia=?2 and l.nombre LIKE %?3% and dis.estado='ACT' \n" +
                    "and p.estado='ACT' and d.estado='ACT' and l.estado='ACT' and v.abreviatura=l.tipo",nativeQuery=true)
    List<ListaLugarEntrega> findAllByAllMenosDistrito(Integer iddepartamento, Integer idprovincia, String nombre);

    @Query(value="select l.idlugarentrega, l.codigo, l.nombre,concat(d.nombre,' - ',p.nombre,' - ',dis.nombre) as depprodis, v.nombre as tipo, l.direccion from lugarentrega as l inner join distrito as dis on l.fiddistrito=dis.iddistrito inner join provincia as p on dis.fidprovincia=p.idprovincia inner join departamento as d on p.fiddepartamento=d.iddepartamento inner join valores as v on v.tabla='lugarentrega' where d.iddepartamento=?1 and l.nombre LIKE %?2% and dis.estado='ACT' and p.estado='ACT' and d.estado='ACT' and l.estado='ACT' and v.abreviatura=l.tipo order by l.nombre ASC",
            countQuery = "select count(l.idlugarentrega) from lugarentrega as l \n" +
                    "inner join distrito as dis on l.fiddistrito=dis.iddistrito \n" +
                    "inner join provincia as p on dis.fidprovincia=p.idprovincia \n" +
                    "inner join departamento as d on p.fiddepartamento=d.iddepartamento \n" +
                    "inner join valores as v on v.tabla='lugarentrega' \n" +
                    "where d.iddepartamento=?1 and l.nombre LIKE %?2% and dis.estado='ACT' \n" +
                    "and p.estado='ACT' and d.estado='ACT' and l.estado='ACT' and v.abreviatura=l.tipo", nativeQuery=true)
    List<ListaLugarEntrega> findAllByDepartamento(Integer iddepartamento, String nombre);

    @Query(value="select l.idlugarentrega, l.codigo, l.nombre,concat(d.nombre,' - ',p.nombre,' - ',dis.nombre) as depprodis, v.nombre as tipo, l.direccion from lugarentrega as l inner join distrito as dis on l.fiddistrito=dis.iddistrito inner join provincia as p on dis.fidprovincia=p.idprovincia inner join departamento as d on p.fiddepartamento=d.iddepartamento inner join valores as v on v.tabla='lugarentrega' where l.nombre LIKE %?1% and dis.estado='ACT' and p.estado='ACT' and d.estado='ACT' and l.estado='ACT' and v.abreviatura=l.tipo order by l.nombre ASC",
            countQuery = "select count(l.idlugarentrega) from lugarentrega as l \n" +
                    "inner join distrito as dis on l.fiddistrito=dis.iddistrito \n" +
                    "inner join provincia as p on dis.fidprovincia=p.idprovincia \n" +
                    "inner join departamento as d on p.fiddepartamento=d.iddepartamento \n" +
                    "inner join valores as v on v.tabla='lugarentrega' \n" +
                    "where l.nombre LIKE %?1% and dis.estado='ACT' \n" +
                    "and p.estado='ACT' and d.estado='ACT' and l.estado='ACT' and v.abreviatura=l.tipo", nativeQuery=true)
    List<ListaLugarEntrega> findAll(String nombre);
}
