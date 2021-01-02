package com.bonoperubackend.BonoPeruBackend.Controladores;
import com.bonoperubackend.BonoPeruBackend.Modelos.*;
import com.bonoperubackend.BonoPeruBackend.Repositorios.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lugarentrega")
public class LugarEntregaController {

    @Autowired
    LugarEntregaRepository lugarEntregaRepository;

    @Autowired
    ListaLugarEntregaRepository listaLugarEntregaRepository;

    @Autowired
    DistritoRepository distritoRepository;

    @Autowired
    ProvinciaRepository provinciaRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    HorarioLugarEntregaRepository horarioLugarEntregaRepository;

    //clase para recibir los datos
    public static class Datos{
        private static Integer iddepartamento;
        private static Integer idprovincia;
        private static Integer iddistrito;
        private static String nombre;
        public Datos() {
            super();
        }
        public Integer getIddepartamento() {
            return iddepartamento;
        }
        public void setIddepartamento(Integer iddepartamento) {
            this.iddepartamento = iddepartamento;
        }
        public Integer getIdprovincia() {
            return idprovincia;
        }
        public void setIdprovincia(Integer idprovincia) {
            this.idprovincia = idprovincia;
        }
        public Integer getIddistrito() {
            return iddistrito;
        }
        public void setIddistrito(Integer iddistrito) {
            this.iddistrito = iddistrito;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }

    @PostMapping("/listarLugaresEntrega")
    public List<ListaLugarEntrega> listarLugaresEntrega(@RequestBody Datos dato) {
        Integer idD=dato.getIddepartamento();
        Integer idP=dato.getIdprovincia();
        Integer idDis=dato.getIddistrito();
        String nombre=dato.getNombre();
        List<ListaLugarEntrega> lugares = null;
        /*if(idD!=null & idP!=null & idDis!=null){
            lugares=listaLugarEntregaRepository.findAllByAllFiltro(idD,idP,idDis,nombre,PageRequest.of(0, 2));
        }else if(idD!=null & idP!=null & idDis==null){
            lugares=listaLugarEntregaRepository.findAllByAllMenosDistrito(idD,idP,nombre,PageRequest.of(0, 2));
        }else if(idD!=null & idP==null & idDis==null){
            lugares=listaLugarEntregaRepository.findAllByDepartamento(idD,nombre,PageRequest.of(0, 2));
        }else if(idD==null & idP==null & idDis==null){
            lugares=listaLugarEntregaRepository.findAll(nombre,PageRequest.of(0, 2));
        }*/
        if(idD!=null & idP!=null & idDis!=null){
            lugares=listaLugarEntregaRepository.findAllByAllFiltro(idD,idP,idDis,nombre);
        }else if(idD!=null & idP!=null & idDis==null){
            lugares=listaLugarEntregaRepository.findAllByAllMenosDistrito(idD,idP,nombre);
        }else if(idD!=null & idP==null & idDis==null){
            lugares=listaLugarEntregaRepository.findAllByDepartamento(idD,nombre);
        }else if(idD==null & idP==null & idDis==null){
            lugares=listaLugarEntregaRepository.findAll(nombre);
        }
        return lugares;
    }

    public class ResponseMessage {
        private String message;
        private List<Lugarentrega> lugares;
        List<ArrayList<String>> errores;

        public ResponseMessage(String message,List<Lugarentrega> lugarentregas,List<ArrayList<String>> errores) {
            this.message = message;
            this.lugares = lugarentregas;
            this.errores = errores;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Lugarentrega> getLugares() {
            return lugares;
        }

        public void setLugares(List<Lugarentrega> lugares) {
            this.lugares = lugares;
        }

        public List<ArrayList<String>> getErrores() {
            return errores;
        }

        public void setErrores(List<ArrayList<String>> errores) {
            this.errores = errores;
        }
    }


    public List<Object> save(MultipartFile file, Integer idCreacion) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Lugarentrega> lugaresEntrega = new ArrayList<>();
            List<ArrayList<String>> errores = new ArrayList<>();
            List<Horariolugarentrega> horarioslugarentrega = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            int row=0;
            for (CSVRecord csvRecord : csvRecords) {
                ArrayList<String> array = new ArrayList<>();

                int ubigeo = -1;
                try {
                    ubigeo = Integer.parseInt(csvRecord.get("UBIGEO"));
                }
                catch (Exception e){
                    array.add(Integer.toString(row));
                    array.add("Error en el UBIGEO");
                }

                String nombre = csvRecord.get("NOMBRE");
                if(nombre==null || nombre.equals("")){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el Nombre");
                }

                String codigo = csvRecord.get("CODIGO");
                if(codigo==null || codigo.equals("")){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el Codigo");
                }

                String direccion = csvRecord.get("DIRECCION");
                if(direccion==null || direccion.equals("")){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en la Direccion");
                }

                String tipo = csvRecord.get("TIPO");
                if(tipo==null || tipo.equals("")){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el Tipo");
                }

                float ratio=0;
                try {
                    ratio = Float.parseFloat(csvRecord.get("RATIO_ATENCION"));
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el Ratio de atencion");
                }

                int aforo=0;
                try {
                    int num = Integer.parseInt(csvRecord.get("NUMERO_VENTANILLAS"));
                    aforo = num*((int) ratio);
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el Aforo");
                }

                LocalTime horaInicioLunesMa = null;
                LocalTime horaFinLunesMa = null;
                LocalTime horaInicioLunesTar = null;
                LocalTime horaFinLunesTar = null;
                try {
                    String horasAux = csvRecord.get("LUNES");
                    if(horasAux.length()!=0){
                        horaInicioLunesMa = LocalTime.parse(horasAux.substring(0,5)).minusHours(3);
                        horaFinLunesMa = LocalTime.parse("12:00").minusHours(3);
                        horaInicioLunesTar = LocalTime.parse("12:00").minusHours(3);
                        horaFinLunesTar = LocalTime.parse(horasAux.substring(6,11)).minusHours(3);
                    }
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el horario del Lunes: ".concat(exception.getMessage()));
                }

                LocalTime horaInicioMartesMa = null;
                LocalTime horaFinMartesMa = null;
                LocalTime horaInicioMartesTar = null;
                LocalTime horaFinMartesTar = null;
                try {
                    String horasAux = csvRecord.get("MARTES");
                    if(horasAux.length()!=0) {
                        horaInicioMartesMa = LocalTime.parse(horasAux.substring(0, 5)).minusHours(3);
                        horaFinMartesMa = LocalTime.parse("12:00").minusHours(3);
                        horaInicioMartesTar = LocalTime.parse("12:00").minusHours(3);
                        horaFinMartesTar = LocalTime.parse(horasAux.substring(6,11)).minusHours(3);
                    }
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el horario del Martes: ".concat(exception.getMessage()));
                }

                LocalTime horaInicioMiercolesMa = null;
                LocalTime horaFinMiercolesMa = null;
                LocalTime horaInicioMiercolesTar = null;
                LocalTime horaFinMiercolesTar = null;
                try {
                    String horasAux = csvRecord.get("MIERCOLES");
                    if(horasAux.length()!=0) {
                        horaInicioMiercolesMa = LocalTime.parse(horasAux.substring(0, 5)).minusHours(3);
                        horaFinMiercolesMa = LocalTime.parse("12:00").minusHours(3);
                        horaInicioMiercolesTar = LocalTime.parse("12:00").minusHours(3);
                        horaFinMiercolesTar = LocalTime.parse(horasAux.substring(6,11)).minusHours(3);
                    }
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el horario del Miercoles: ".concat(exception.getMessage()));
                }

                LocalTime horaInicioJuevesMa = null;
                LocalTime horaFinJuevesMa = null;
                LocalTime horaInicioJuevesTar = null;
                LocalTime horaFinJuevesTar = null;
                try {
                    String horasAux = csvRecord.get("JUEVES");
                    if(horasAux.length()!=0) {
                        horaInicioJuevesMa = LocalTime.parse(horasAux.substring(0, 5)).minusHours(3);
                        horaFinJuevesMa = LocalTime.parse("12:00").minusHours(3);
                        horaInicioJuevesTar = LocalTime.parse("12:00").minusHours(3);
                        horaFinJuevesTar = LocalTime.parse(horasAux.substring(6,11)).minusHours(3);
                    }
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el horario del Jueves: ".concat(exception.getMessage()));
                }

                LocalTime horaInicioViernesMa = null;
                LocalTime horaFinViernesMa = null;
                LocalTime horaInicioViernesTar = null;
                LocalTime horaFinViernesTar = null;
                try {
                    String horasAux = csvRecord.get("VIERNES");
                    if(horasAux.length()!=0) {
                        horaInicioViernesMa = LocalTime.parse(horasAux.substring(0, 5)).minusHours(3);
                        horaFinViernesMa = LocalTime.parse("12:00").minusHours(3);
                        horaInicioViernesTar = LocalTime.parse("12:00").minusHours(3);
                        horaFinViernesTar = LocalTime.parse(horasAux.substring(6,11)).minusHours(3);
                    }
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el horario del Viernes: ".concat(exception.getMessage()));
                }

                LocalTime horaInicioSabadoMa = null;
                LocalTime horaFinSabadoMa = null;
                LocalTime horaInicioSabadoTar = null;
                LocalTime horaFinSabadoTar = null;
                try {
                    String horasAux = csvRecord.get("SABADO");
                    if(horasAux.length()!=0) {
                        horaInicioSabadoMa = LocalTime.parse(horasAux.substring(0, 5)).minusHours(3);
                        horaFinSabadoMa = LocalTime.parse("12:00").minusHours(3);
                        horaInicioSabadoTar = LocalTime.parse("12:00").minusHours(3);
                        horaFinSabadoTar = LocalTime.parse(horasAux.substring(6,11)).minusHours(3);
                        if(horaFinSabadoMa.compareTo(horaInicioSabadoMa)<0 || horaInicioSabadoTar.compareTo(horaFinSabadoMa)<0 || horaFinSabadoTar.compareTo(horaInicioSabadoTar)<0){
                            if(array.size()==0) array.add(Integer.toString(row));
                            array.add("Error en el horario del Sabado: Horario Inválido");
                        }
                    }
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el horario del Sabado: ".concat(exception.getMessage()));
                }

                LocalTime horaInicioDomingoMa = null;
                LocalTime horaFinDomingoMa = null;
                LocalTime horaInicioDomingoTar = null;
                LocalTime horaFinDomingoTar = null;
                try {
                    String horasAux = csvRecord.get("DOMINGO");
                    if(horasAux.length()!=0) {
                        horaInicioDomingoMa = LocalTime.parse(horasAux.substring(0, 5)).minusHours(3);
                        horaFinDomingoMa = LocalTime.parse("12:00").minusHours(3);
                        horaInicioDomingoTar = LocalTime.parse("12:00").minusHours(3);
                        horaFinDomingoTar = LocalTime.parse(horasAux.substring(6,11)).minusHours(3);
                        if(horaFinDomingoMa.compareTo(horaInicioDomingoMa)<0 || horaInicioDomingoTar.compareTo(horaFinDomingoMa)<0 || horaFinDomingoTar.compareTo(horaInicioDomingoTar)<0){
                            if(array.size()==0) array.add(Integer.toString(row));
                            array.add("Error en el horario del Domingo: Horario Inválido");
                        }
                    }
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el horario del Domingo: ".concat(exception.getMessage()));
                }

                Optional<Departamento> departamentoAux = departamentoRepository.findByUbigeo(ubigeo/10000);
                if(departamentoAux.isPresent()){
                    Optional<Provincia> provinciaAux = provinciaRepository.findByUbigeoAndFiddepartamento((ubigeo/100)%100,departamentoAux.get().getIddepartamento());
                    if(provinciaAux.isPresent()){
                        Optional<Distrito> distritoAux = distritoRepository.findByUbigeoAndFidprovincia(ubigeo%100,provinciaAux.get().getIdprovincia());
                        if (distritoAux.isPresent()) {
                            Distrito distrito = distritoAux.get();

                            if(array.size()==0){
                                Optional<Lugarentrega> lugarExistente = lugarEntregaRepository.findByCodigo(codigo);
                                if(lugarExistente.isPresent()){
                                    Horariolugarentrega horariolugarentrega;
                                    Optional<Horariolugarentrega> hor;
                                    if(horaInicioLunesMa!=null) {
                                        hor = horarioLugarEntregaRepository.findByDiaAndLugarentrega("Lunes", lugarExistente.get());
                                        if (hor.isPresent()) {
                                            hor.get().setHoraaperturaturnoma(horaInicioLunesMa);
                                            hor.get().setHoracierreturnoma(horaFinLunesMa);
                                            hor.get().setHoraaperturaturnotar(horaInicioLunesTar);
                                            hor.get().setHoracierreturnotar(horaFinLunesTar);
                                            hor.get().setUsuarioActualizacion(idCreacion);
                                            horarioLugarEntregaRepository.save(hor.get());

                                        } else {
                                            horariolugarentrega = new Horariolugarentrega();
                                            horariolugarentrega.setDia("Lunes");
                                            horariolugarentrega.setEstado("ACT");
                                            horariolugarentrega.setHoraaperturaturnoma(horaInicioLunesMa);
                                            horariolugarentrega.setHoracierreturnoma(horaFinLunesMa);
                                            horariolugarentrega.setHoraaperturaturnotar(horaInicioLunesTar);
                                            horariolugarentrega.setHoracierreturnotar(horaFinLunesTar);
                                            horariolugarentrega.setLugarentrega(lugarExistente.get());
                                            horariolugarentrega.setUsuarioCreacion(idCreacion);
                                            horariolugarentrega.setDiabinario(1000000);
                                            horarioLugarEntregaRepository.save(horariolugarentrega);
                                        }
                                    }
                                    if(horaInicioMartesMa!=null) {
                                        hor = horarioLugarEntregaRepository.findByDiaAndLugarentrega("Martes",lugarExistente.get());
                                        if(hor.isPresent()){
                                            hor.get().setHoraaperturaturnoma(horaInicioMartesMa);
                                            hor.get().setHoracierreturnoma(horaFinMartesMa);
                                            hor.get().setHoraaperturaturnotar(horaInicioMartesTar);
                                            hor.get().setHoracierreturnotar(horaFinMartesTar);
                                            hor.get().setUsuarioActualizacion(idCreacion);
                                            horarioLugarEntregaRepository.save(hor.get());
                                        }
                                        else{
                                            horariolugarentrega = new Horariolugarentrega();
                                            horariolugarentrega.setDia("Martes");
                                            horariolugarentrega.setEstado("ACT");
                                            horariolugarentrega.setHoraaperturaturnoma(horaInicioMartesMa);
                                            horariolugarentrega.setHoracierreturnoma(horaFinMartesMa);
                                            horariolugarentrega.setHoraaperturaturnotar(horaInicioMartesTar);
                                            horariolugarentrega.setHoracierreturnotar(horaFinMartesTar);
                                            horariolugarentrega.setLugarentrega(lugarExistente.get());
                                            horariolugarentrega.setUsuarioCreacion(idCreacion);
                                            horariolugarentrega.setDiabinario(100000);
                                            horarioLugarEntregaRepository.save(horariolugarentrega);
                                        }
                                    }
                                    if(horaInicioMiercolesMa!=null) {
                                        hor = horarioLugarEntregaRepository.findByDiaAndLugarentrega("Miercoles",lugarExistente.get());
                                        if(hor.isPresent()){
                                            hor.get().setHoraaperturaturnoma(horaInicioMiercolesMa);
                                            hor.get().setHoracierreturnoma(horaFinMiercolesMa);
                                            hor.get().setHoraaperturaturnotar(horaInicioMiercolesTar);
                                            hor.get().setHoracierreturnotar(horaFinMiercolesTar);
                                            hor.get().setUsuarioActualizacion(idCreacion);
                                            horarioLugarEntregaRepository.save(hor.get());
                                        }
                                        else{
                                            horariolugarentrega = new Horariolugarentrega();
                                            horariolugarentrega.setDia("Miercoles");
                                            horariolugarentrega.setEstado("ACT");
                                            horariolugarentrega.setHoraaperturaturnoma(horaInicioMiercolesMa);
                                            horariolugarentrega.setHoracierreturnoma(horaFinMiercolesMa);
                                            horariolugarentrega.setHoraaperturaturnotar(horaInicioMiercolesTar);
                                            horariolugarentrega.setHoracierreturnotar(horaFinMiercolesTar);
                                            horariolugarentrega.setLugarentrega(lugarExistente.get());
                                            horariolugarentrega.setUsuarioCreacion(idCreacion);
                                            horariolugarentrega.setDiabinario(10000);
                                            horarioLugarEntregaRepository.save(horariolugarentrega);
                                        }
                                    }
                                    if(horaInicioJuevesMa!=null) {
                                        hor = horarioLugarEntregaRepository.findByDiaAndLugarentrega("Jueves",lugarExistente.get());
                                        if(hor.isPresent()){
                                            hor.get().setHoraaperturaturnoma(horaInicioJuevesMa);
                                            hor.get().setHoracierreturnoma(horaFinJuevesMa);
                                            hor.get().setHoraaperturaturnotar(horaInicioJuevesTar);
                                            hor.get().setHoracierreturnotar(horaFinJuevesTar);
                                            hor.get().setUsuarioActualizacion(idCreacion);
                                            horarioLugarEntregaRepository.save(hor.get());
                                        }
                                        else{
                                            horariolugarentrega = new Horariolugarentrega();
                                            horariolugarentrega.setDia("Jueves");
                                            horariolugarentrega.setEstado("ACT");
                                            horariolugarentrega.setHoraaperturaturnoma(horaInicioJuevesMa);
                                            horariolugarentrega.setHoracierreturnoma(horaFinJuevesMa);
                                            horariolugarentrega.setHoraaperturaturnotar(horaInicioJuevesTar);
                                            horariolugarentrega.setHoracierreturnotar(horaFinJuevesTar);
                                            horariolugarentrega.setUsuarioCreacion(idCreacion);
                                            horariolugarentrega.setDiabinario(1000);
                                            horarioLugarEntregaRepository.save(horariolugarentrega);
                                        }
                                    }
                                    if(horaInicioViernesMa!=null) {
                                        hor = horarioLugarEntregaRepository.findByDiaAndLugarentrega("Viernes",lugarExistente.get());
                                        if(hor.isPresent()){
                                            hor.get().setHoraaperturaturnoma(horaInicioViernesMa);
                                            hor.get().setHoracierreturnoma(horaFinViernesMa);
                                            hor.get().setHoraaperturaturnotar(horaInicioViernesTar);
                                            hor.get().setHoracierreturnotar(horaFinViernesTar);
                                            hor.get().setUsuarioActualizacion(idCreacion);
                                            horarioLugarEntregaRepository.save(hor.get());
                                        }
                                        else{
                                            horariolugarentrega = new Horariolugarentrega();
                                            horariolugarentrega.setDia("Viernes");
                                            horariolugarentrega.setEstado("ACT");
                                            horariolugarentrega.setHoraaperturaturnoma(horaInicioViernesMa);
                                            horariolugarentrega.setHoracierreturnoma(horaFinViernesMa);
                                            horariolugarentrega.setHoraaperturaturnotar(horaInicioViernesTar);
                                            horariolugarentrega.setHoracierreturnotar(horaFinViernesTar);
                                            horariolugarentrega.setUsuarioCreacion(idCreacion);
                                            horariolugarentrega.setDiabinario(100);
                                            horarioLugarEntregaRepository.save(horariolugarentrega);
                                        }
                                    }
                                    if(horaInicioSabadoMa!=null) {
                                        hor = horarioLugarEntregaRepository.findByDiaAndLugarentrega("Sabado",lugarExistente.get());
                                        if(hor.isPresent()){
                                            hor.get().setHoraaperturaturnoma(horaInicioSabadoMa);
                                            hor.get().setHoracierreturnoma(horaFinSabadoMa);
                                            hor.get().setHoraaperturaturnotar(horaInicioSabadoTar);
                                            hor.get().setHoracierreturnotar(horaFinSabadoTar);
                                            hor.get().setUsuarioActualizacion(idCreacion);
                                            horarioLugarEntregaRepository.save(hor.get());
                                        }
                                        else{
                                            horariolugarentrega = new Horariolugarentrega();
                                            horariolugarentrega.setDia("Sabado");
                                            horariolugarentrega.setEstado("ACT");
                                            horariolugarentrega.setHoraaperturaturnoma(horaInicioSabadoMa);
                                            horariolugarentrega.setHoracierreturnoma(horaFinSabadoMa);
                                            horariolugarentrega.setHoraaperturaturnotar(horaInicioSabadoTar);
                                            horariolugarentrega.setHoracierreturnotar(horaFinSabadoTar);
                                            horariolugarentrega.setUsuarioCreacion(idCreacion);
                                            horariolugarentrega.setDiabinario(10);
                                            horarioLugarEntregaRepository.save(horariolugarentrega);
                                        }
                                    }
                                    if(horaInicioDomingoMa!=null) {
                                        hor = horarioLugarEntregaRepository.findByDiaAndLugarentrega("Domingo",lugarExistente.get());
                                        if(hor.isPresent()){
                                            hor.get().setHoraaperturaturnoma(horaInicioDomingoMa);
                                            hor.get().setHoracierreturnoma(horaFinDomingoMa);
                                            hor.get().setHoraaperturaturnotar(horaInicioDomingoTar);
                                            hor.get().setHoracierreturnotar(horaFinDomingoTar);
                                            hor.get().setUsuarioActualizacion(idCreacion);
                                            horarioLugarEntregaRepository.save(hor.get());
                                        }
                                        else{
                                            horariolugarentrega = new Horariolugarentrega();
                                            horariolugarentrega.setDia("Domingo");
                                            horariolugarentrega.setEstado("ACT");
                                            horariolugarentrega.setHoraaperturaturnoma(horaInicioDomingoMa);
                                            horariolugarentrega.setHoracierreturnoma(horaFinDomingoMa);
                                            horariolugarentrega.setHoraaperturaturnotar(horaInicioDomingoTar);
                                            horariolugarentrega.setHoracierreturnotar(horaFinDomingoTar);
                                            horariolugarentrega.setUsuarioCreacion(idCreacion);
                                            horariolugarentrega.setDiabinario(1);
                                            horarioLugarEntregaRepository.save(horariolugarentrega);
                                        }
                                    }
                                    lugaresEntrega.add(lugarExistente.get());
                                }
                                else{
                                    Lugarentrega lugar = new Lugarentrega(
                                            nombre,
                                            codigo,
                                            direccion,
                                            ratio,
                                            aforo,
                                            tipo,
                                            idCreacion,
                                            "ACT",
                                            distrito
                                    );
                                    distritoRepository.save(distrito);
                                    lugarEntregaRepository.save(lugar);
                                    lugaresEntrega.add(lugar);
                                    lugar = lugarEntregaRepository.findByCodigo(lugar.getCodigo()).get();
                                    Horariolugarentrega horariolugarentrega;

                                    if(horaInicioLunesMa!=null){
                                        horariolugarentrega = new Horariolugarentrega();
                                        horariolugarentrega.setDia("Lunes");
                                        horariolugarentrega.setEstado("ACT");
                                        horariolugarentrega.setHoraaperturaturnoma(horaInicioLunesMa);
                                        horariolugarentrega.setHoracierreturnoma(horaFinLunesMa);
                                        horariolugarentrega.setHoraaperturaturnotar(horaInicioLunesTar);
                                        horariolugarentrega.setHoracierreturnotar(horaFinLunesTar);
                                        horariolugarentrega.setLugarentrega(lugar);
                                        horariolugarentrega.setDiabinario(1000000);
                                        horariolugarentrega.setUsuarioCreacion(idCreacion);
                                        horarioLugarEntregaRepository.save(horariolugarentrega);
                                    }

                                    if(horaInicioMartesMa!=null) {
                                        horariolugarentrega = new Horariolugarentrega();
                                        horariolugarentrega.setDia("Martes");
                                        horariolugarentrega.setEstado("ACT");
                                        horariolugarentrega.setHoraaperturaturnoma(horaInicioMartesMa);
                                        horariolugarentrega.setHoracierreturnoma(horaFinMartesMa);
                                        horariolugarentrega.setHoraaperturaturnotar(horaInicioMartesTar);
                                        horariolugarentrega.setHoracierreturnotar(horaFinMartesTar);
                                        horariolugarentrega.setLugarentrega(lugar);
                                        horariolugarentrega.setDiabinario(100000);
                                        horariolugarentrega.setUsuarioCreacion(idCreacion);
                                        horarioLugarEntregaRepository.save(horariolugarentrega);
                                    }

                                    if(horaInicioMiercolesMa!=null) {
                                        horariolugarentrega = new Horariolugarentrega();
                                        horariolugarentrega.setDia("Miercoles");
                                        horariolugarentrega.setEstado("ACT");
                                        horariolugarentrega.setHoraaperturaturnoma(horaInicioMiercolesMa);
                                        horariolugarentrega.setHoracierreturnoma(horaFinMiercolesMa);
                                        horariolugarentrega.setHoraaperturaturnotar(horaInicioMiercolesTar);
                                        horariolugarentrega.setHoracierreturnotar(horaFinMiercolesTar);
                                        horariolugarentrega.setLugarentrega(lugar);
                                        horariolugarentrega.setDiabinario(10000);
                                        horariolugarentrega.setUsuarioCreacion(idCreacion);
                                        horarioLugarEntregaRepository.save(horariolugarentrega);
                                    }

                                    if(horaInicioJuevesMa!=null) {
                                        horariolugarentrega = new Horariolugarentrega();
                                        horariolugarentrega.setDia("Jueves");
                                        horariolugarentrega.setEstado("ACT");
                                        horariolugarentrega.setHoraaperturaturnoma(horaInicioJuevesMa);
                                        horariolugarentrega.setHoracierreturnoma(horaFinJuevesMa);
                                        horariolugarentrega.setHoraaperturaturnotar(horaInicioJuevesTar);
                                        horariolugarentrega.setHoracierreturnotar(horaFinJuevesTar);
                                        horariolugarentrega.setLugarentrega(lugar);
                                        horariolugarentrega.setDiabinario(1000);
                                        horariolugarentrega.setUsuarioCreacion(idCreacion);
                                        horarioLugarEntregaRepository.save(horariolugarentrega);
                                    }

                                    if(horaInicioViernesMa!=null) {
                                        horariolugarentrega = new Horariolugarentrega();
                                        horariolugarentrega.setDia("Viernes");
                                        horariolugarentrega.setEstado("ACT");
                                        horariolugarentrega.setHoraaperturaturnoma(horaInicioViernesMa);
                                        horariolugarentrega.setHoracierreturnoma(horaFinViernesMa);
                                        horariolugarentrega.setHoraaperturaturnotar(horaInicioViernesTar);
                                        horariolugarentrega.setHoracierreturnotar(horaFinViernesTar);
                                        horariolugarentrega.setLugarentrega(lugar);
                                        horariolugarentrega.setDiabinario(100);
                                        horariolugarentrega.setUsuarioCreacion(idCreacion);
                                        horarioLugarEntregaRepository.save(horariolugarentrega);
                                    }

                                    if(horaInicioSabadoMa!=null) {
                                        horariolugarentrega = new Horariolugarentrega();
                                        horariolugarentrega.setDia("Sabado");
                                        horariolugarentrega.setEstado("ACT");
                                        horariolugarentrega.setHoraaperturaturnoma(horaInicioSabadoMa);
                                        horariolugarentrega.setHoracierreturnoma(horaFinSabadoMa);
                                        horariolugarentrega.setHoraaperturaturnotar(horaInicioSabadoTar);
                                        horariolugarentrega.setHoracierreturnotar(horaFinSabadoTar);
                                        horariolugarentrega.setLugarentrega(lugar);
                                        horariolugarentrega.setDiabinario(10);
                                        horariolugarentrega.setUsuarioCreacion(idCreacion);
                                        horarioLugarEntregaRepository.save(horariolugarentrega);
                                    }

                                    if(horaInicioDomingoMa!=null) {
                                        horariolugarentrega = new Horariolugarentrega();
                                        horariolugarentrega.setDia("Domingo");
                                        horariolugarentrega.setEstado("ACT");
                                        horariolugarentrega.setHoraaperturaturnoma(horaInicioDomingoMa);
                                        horariolugarentrega.setHoracierreturnoma(horaFinDomingoMa);
                                        horariolugarentrega.setHoraaperturaturnotar(horaInicioDomingoTar);
                                        horariolugarentrega.setHoracierreturnotar(horaFinDomingoTar);
                                        horariolugarentrega.setLugarentrega(lugar);
                                        horariolugarentrega.setDiabinario(1);
                                        horariolugarentrega.setUsuarioCreacion(idCreacion);
                                        horarioLugarEntregaRepository.save(horariolugarentrega);
                                    }
                                }
                            }
                            else{
                                errores.add(array);
                            }
                        }
                        else{
                            if(array.size()==0) array.add(Integer.toString(row));
                            array.add("Codigo de UBIGEO erróneo");
                            errores.add(array);
                        }
                    }
                    else{
                        if(array.size()==0) array.add(Integer.toString(row));
                        array.add("Codigo de UBIGEO erróneo");
                        errores.add(array);
                    }
                }
                else{
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Codigo de UBIGEO erróneo");
                    errores.add(array);
                }
                row = row+1;
                
            }
            List<Object> respuesta = new ArrayList<>();
            respuesta.add(lugaresEntrega);
            respuesta.add(errores);
            return respuesta;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    @RequestMapping(value="/carga/{idCreacion}", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable Integer idCreacion, @RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                List<Object> respuesta = save(file,idCreacion);

                message = "Lugares de Entrega Cargados exitosamente: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,(List<Lugarentrega>) respuesta.get(0),(List<ArrayList<String>>) respuesta.get(1)));
            } catch (Exception e) {
                message = "No se pudieron cargar los Lugares de Entrega: " + file.getOriginalFilename() + " / " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,null,null));
            }
        }

        message = "No es un archivo csv";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,null,null));
    }


}
