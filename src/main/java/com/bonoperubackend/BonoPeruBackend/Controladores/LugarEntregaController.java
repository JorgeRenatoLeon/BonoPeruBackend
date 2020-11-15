package com.bonoperubackend.BonoPeruBackend.Controladores;
import com.bonoperubackend.BonoPeruBackend.Modelos.*;
import com.bonoperubackend.BonoPeruBackend.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lugarentrega")
public class LugarEntregaController {

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    ProvinciaRepository provinciaRepository;

    @Autowired
    DistritoRepository distritoRepository;

    @Autowired
    LugarEntregaRepository lugarEntregaRepository;

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    HorarioLugarEntregaRepository horarioLugarEntregaRepository;
    //clase para mostrar lista de lugares de entrega

    public class Lugares{
        private Integer idLugarentrega;
        @Column(nullable=false, length=10)
        private String codigo;
        @Column(nullable=false, length=200)
        private String nombre;
        @Column(nullable=false, length=100)
        private String nombreDepartamento;
        @Column(nullable=false, length=100)
        private String nombreProvincia;
        @Column(nullable=false, length=100)
        private String nombreDistrito;
        @Column(nullable=false, length=200)
        private String direccion;

        public Integer getIdLugarentrega() {
            return idLugarentrega;
        }

        public void setIdLugarentrega(Integer idLugarentrega) {
            this.idLugarentrega = idLugarentrega;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getNombreDepartamento() {
            return nombreDepartamento;
        }

        public void setNombreDepartamento(String nombreDepartamento) {
            this.nombreDepartamento = nombreDepartamento;
        }

        public String getNombreProvincia() {
            return nombreProvincia;
        }

        public void setNombreProvincia(String nombreProvincia) {
            this.nombreProvincia = nombreProvincia;
        }

        public String getNombreDistrito() {
            return nombreDistrito;
        }

        public void setNombreDistrito(String nombreDistrito) {
            this.nombreDistrito = nombreDistrito;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }
    }

    @PostMapping("/consultarCodigoFamilia")
    public String consultarCodigoFamilia(@RequestParam String codigo) {
        //codigo
        return "hola";
    }

    @PostMapping("/listarLugaresEntrega")
    public Provincia listarLugaresEntrega(@RequestParam Integer id) {
        //codigo
        Integer idD=id;
        Integer idP=1;
        Integer idDis=1;
        Optional<Provincia> provincias=provinciaRepository.findByFiddepartamento(idD);


        //lista todos los lugares de entrega sin ningún filtro
        /*List<Lugarentrega> lug= new ArrayList<>();
        lugarEntregaRepository.findAll().forEach(lug::add);
        List<Lugares> lugaresaListar= new ArrayList<>();
        for (Lugarentrega entr : lug) {
            Integer idProv=entr.getDistrito().getFid_provincia();
            Optional<Provincia> provincia= provinciaRepository.findById(idProv);
            Integer idDep=provincia.get().getFiddepartamento();
            Optional<Departamento> departamento=departamentoRepository.findById(idDep);
            Lugares lugar = crearlugares(entr,provincia.get(),departamento.get());
            lugaresaListar.add(lugar);
        }*/
        return provincias.get();
    }
    public Lugares crearlugares(Lugarentrega entr, Provincia provincia, Departamento departamento){
        Lugares l= new Lugares();
        l.setIdLugarentrega(entr.getIdLugarentrega());
        l.setCodigo(entr.getCodigo());
        l.setNombre(entr.getNombre());
        l.setDireccion(entr.getDireccion());
        l.setNombreDistrito(entr.getDistrito().getNombre());
        l.setNombreProvincia(provincia.getNombre());
        l.setNombreDepartamento(departamento.getNombre());
        return l;
    }
}
