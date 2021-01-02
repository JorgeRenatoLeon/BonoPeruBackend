package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.*;
import com.bonoperubackend.BonoPeruBackend.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bonoperubackend.BonoPeruBackend.AlgoritmoGenetico.*;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import sun.security.krb5.internal.crypto.Des;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cronograma")
public class CronogramaController {
    @Autowired
    ListaCronogramaRepository listaCronogramaRepository;

    @Autowired
    LugarEntregaRepository lugarEntregaRepository;

    @Autowired
    DistritoRepository distritoRepository;

    @Autowired
    ProvinciaRepository provinciaRepository;

    @Autowired
    DepartamentoRepository departamentoRepository;

    @Autowired
    LugarEntregaAlgoritmoRepository lugarEntregaAlgoritmoRepository;

    @Autowired
    BeneficiarioAlgoritmoRepository beneficiarioAlgoritmoRepository;

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    DescargaCronogramaRepository descargaCronogramaRepository;

    @Autowired
    QuejasRepository quejasRepository;

    @Autowired
    CronogramaRepository cronogramaRepository;

    @Autowired
    IncidenteRepository incidenteRepository;

    @Autowired
    HistoricoRepository historicoRepository;

    @Autowired
    HorarioLugarEntregaAlgoritmoRepository horarioLugarEntregaAlgoritmoRepository;

    private Path fileStorageLocation;
    public static class Datos {
        private static Integer idcronograma;
        private static Integer iddepartamento;
        private static Integer idprovincia;
        private static Integer iddistrito;
        private static LocalDate fechaini;
        private static LocalDate fechafin;
        private static String nombre;
        private static List<Integer> numeros;
        private static Integer usuariocreacion;
        private static List<LocalDate> fechas;
        private static List<LocalTime> horainicio;
        private static List<LocalTime> horafin;
        private static List<Integer> listaDepartamentos;
        private static List<Integer> listaProvincias;
        private static List<Integer> listaDistritos;
        private static LocalDate fechaactual;


        public Datos() {
            super();
        }
        public Integer getIdcronograma() {
            return idcronograma;
        }
        public void setIdcronograma(Integer idcronograma) {
            Datos.idcronograma = idcronograma;
        }
        public Integer getIddepartamento() {
            return iddepartamento;
        }
        public void setIddepartamento(Integer iddepartamento) {
            Datos.iddepartamento = iddepartamento;
        }
        public Integer getIdprovincia() {
            return idprovincia;
        }
        public void setIdprovincia(Integer idprovincia) {
            Datos.idprovincia = idprovincia;
        }
        public Integer getIddistrito() {
            return iddistrito;
        }
        public void setIddistrito(Integer iddistrito) {
            Datos.iddistrito = iddistrito;
        }
        public LocalDate getFechaini() {
            return fechaini;
        }
        public void setFechaini(LocalDate fechaini) {
            Datos.fechaini = fechaini;
        }
        public LocalDate getFechafin() {
            return fechafin;
        }
        public void setFechafin(LocalDate fechafin) {
            Datos.fechafin = fechafin;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            Datos.nombre = nombre;
        }
        public List<Integer> getNumeros() {
            return numeros;
        }
        public void setNumeros(List<Integer> numeros) {
            Datos.numeros = numeros;
        }
        public Integer getUsuariocreacion() {
            return usuariocreacion;
        }
        public void setUsuariocreacion(Integer usuariocreacion) {
            Datos.usuariocreacion = usuariocreacion;
        }
        public List<LocalDate> getFechas() {
            return fechas;
        }
        public void setFechas(List<LocalDate> fechas) {
            Datos.fechas = fechas;
        }
        public List<LocalTime> getHorainicio() {
            return horainicio;
        }
        public void setHorainicio(List<LocalTime> horainicio) {
            Datos.horainicio = horainicio;
        }
        public List<LocalTime> getHorafin() {
            return horafin;
        }
        public void setHorafin(List<LocalTime> horafin) {
            Datos.horafin = horafin;
        }
        public List<Integer> getListaDepartamentos() {
            return listaDepartamentos;
        }
        public void setListaDepartamentos(List<Integer> listaDepartamentos) {
            Datos.listaDepartamentos = listaDepartamentos;
        }
        public List<Integer> getListaProvincias() {
            return listaProvincias;
        }
        public void setListaProvincias(List<Integer> listaProvincias) {
            Datos.listaProvincias = listaProvincias;
        }
        public List<Integer> getListaDistritos() {
            return listaDistritos;
        }
        public void setListaDistritos(List<Integer> listaDistritos) {
            Datos.listaDistritos = listaDistritos;
        }

        public LocalDate getFechaactual() {
            return fechaactual;
        }

        public void setFechaactual(LocalDate fechaactual) {
            Datos.fechaactual = fechaactual;
        }
    }

    @PostMapping("/listarbeneficiarioscronograma")
    public ArrayList<Object> listarbeneficiarioscronograma(@RequestBody Datos dato) {
        //listar los horarios de ese cronograma
        ArrayList<Object> reporte=new ArrayList<>();
        ArrayList<ArrayList<Object>> horarios=new ArrayList<>();

        if(dato.getIddepartamento()!=null && dato.getIdprovincia()!=null && dato.getIddistrito()!=null){
            horarios=horarioRepository.listarhorariosXDistrito(dato.getIdcronograma(),dato.getFechaini(),dato.getFechafin(),
                    dato.getNombre(),dato.getIddepartamento(),dato.getIdprovincia(),dato.getIddistrito());
        }else if(dato.getIddepartamento()!=null && dato.getIdprovincia()!=null && dato.getIddistrito()==null){
            horarios=horarioRepository.listarhorariosXProvincia(dato.getIdcronograma(),dato.getFechaini(),dato.getFechafin(),
                    dato.getNombre(),dato.getIddepartamento(),dato.getIdprovincia());
        }else if(dato.getIddepartamento()!=null && dato.getIdprovincia()==null && dato.getIddistrito()==null){
            horarios=horarioRepository.listarhorariosXDepartamento(dato.getIdcronograma(),dato.getFechaini(),dato.getFechafin(),
                    dato.getNombre(),dato.getIddepartamento());
        }else if(dato.getIddepartamento()==null && dato.getIdprovincia()==null && dato.getIddistrito()==null){
            horarios=horarioRepository.listarhorarios(dato.getIdcronograma(),dato.getFechaini(),dato.getFechafin(),dato.getNombre());
        }

        //horarios=horarioRepository.listarhorarios(dato.getIdcronograma(),dato.getFechaini(),dato.getFechafin(),dato.getNombre());

        ///realizar las diviciones
        LocalDate fechaactual=dato.getFechaactual();

        for(int i=0; i<horarios.size(); i++){
            ArrayList<Object> hor=horarios.get(i);
            Hashtable<String,Object> lug=new Hashtable<>();

            lug.put("idbeneficiario",hor.get(0));
            lug.put("nombre",hor.get(1));
            lug.put("locacion",hor.get(2));
            //lug.put("horarios",l.getNumhorarios());
            String fecha=hor.get(3).toString();
            lug.put("horario1",fecha.substring(8,10)+"/"+fecha.substring(5,7)+"/"+fecha.substring(0,4) + " - "+hor.get(4).toString().substring(0,5)+ "-" + hor.get(5).toString().substring(0,5));
            lug.put("lugar1",hor.get(6)+" - "+hor.get(7));


            if(hor.get(8).toString().contains("ENT")){
                lug.put("estado1","Entregado");
            }else if(hor.get(8).toString().contains("NOE") && ( fecha.compareTo(fechaactual.toString())<=0 )){
                lug.put("estado1","No entregado");
            }else{
                lug.put("estado1","Programado");
            }

            if(i<horarios.size()-1){
                ArrayList<Object> hor2=horarios.get(i+1);
                //Verificamos si es el segundo horario
                if(hor2.get(0).toString().equals(hor.get(0).toString())){
                    String fecha2=hor2.get(3).toString();
                    lug.put("horario2",fecha2.substring(8,10)+"/"+fecha2.substring(5,7)+"/"+fecha2.substring(0,4) + " - "+hor2.get(4).toString().substring(0,5)+ "-" + hor2.get(5).toString().substring(0,5));
                    lug.put("lugar2",hor2.get(6)+" - "+hor2.get(7));

                    if(hor2.get(8).toString().contains("ENT")){
                        lug.put("estado2","Entregado");
                    }else if(hor2.get(8).toString().contains("NOE") && ( hor2.get(3).toString().compareTo(fechaactual.toString())<=0 )){
                        lug.put("estado2","No entregado");
                    }else{
                        lug.put("estado2","Programado");
                    }

                    i++;
                }else{
                    lug.put("horario2","-");
                    lug.put("lugar2","-");
                    lug.put("estado2","-");
                }


            }

            reporte.add(lug);
        }
        return reporte;
    }

    /*@PostMapping("/listarhorariocronograma")
    public ArrayList<Object> listarhorariocronograma(@RequestBody Datos dato) {
        //listar los horarios de ese cronograma
        ArrayList<Horario> horarios=horarioRepository.findAllByCronograma_IdCronogramaAndFechaGreaterThanEqualAndFechaLessThanEqual(dato.getIdcronograma(),dato.getFechaini(),dato.getFechafin());
        int mujeres=0;
        int discapacitados=0;
        ArrayList<ListaCronograma> lugares= new ArrayList<>();
        ArrayList<Object> reporte=new ArrayList<>();
        for(Horario horario: horarios) {
            if (horario.getBeneficiario().getFemenino() == true) {
                mujeres = 1;
            } else {
                mujeres = 0;
            }
            if (horario.getBeneficiario().getEsdiscapacitado() == true) {
                discapacitados = 1;
            } else {
                discapacitados = 0;
            }
            Optional<Provincia> pro = provinciaRepository.findById(horario.getHorariolugarentrega().getLugarentrega().getDistrito().getFidprovincia());
            Optional<Departamento> dep = departamentoRepository.findById(pro.get().getFiddepartamento());
            if(dato.getIddepartamento()==null && dato.getIdprovincia()==null && dato.getIddistrito()==null) {
                ListaCronograma cronograma = new ListaCronograma(horario.getHorariolugarentrega().getLugarentrega().getIdLugarentrega(),
                        horario.getHorariolugarentrega().getLugarentrega().getNombre(),
                        dep.get().getNombre() + "-" + pro.get().getNombre() + "-" + horario.getHorariolugarentrega().getLugarentrega().getDistrito().getNombre(),
                        horario.getFecha(), horario.getHoraInicio(), horario.getHoraFin(),
                        horario.getHorariolugarentrega().getLugarentrega().getAforo(),
                        mujeres, discapacitados,
                        horario.getHorariolugarentrega().getLugarentrega().getDistrito().getZonariesgo(), 1);
                lugares.add(cronograma);
            }else if(dato.getIddepartamento()!=null && dato.getIdprovincia()!=null && dato.getIddistrito()==null &&
                    dep.get().getIddepartamento()==dato.getIddepartamento() && pro.get().getIdprovincia()==dato.getIdprovincia()){
                ListaCronograma cronograma = new ListaCronograma(horario.getHorariolugarentrega().getLugarentrega().getIdLugarentrega(),
                        horario.getHorariolugarentrega().getLugarentrega().getNombre(),
                        dep.get().getNombre() + "-" + pro.get().getNombre() + "-" + horario.getHorariolugarentrega().getLugarentrega().getDistrito().getNombre(),
                        horario.getFecha(), horario.getHoraInicio(), horario.getHoraFin(),
                        horario.getHorariolugarentrega().getLugarentrega().getAforo(),
                        mujeres, discapacitados,
                        horario.getHorariolugarentrega().getLugarentrega().getDistrito().getZonariesgo(), 1);
                lugares.add(cronograma);
            }else if(dato.getIddepartamento()!=null && dato.getIdprovincia()==null && dato.getIddistrito()==null &&
                    dep.get().getIddepartamento()==dato.getIddepartamento()){
                ListaCronograma cronograma = new ListaCronograma(horario.getHorariolugarentrega().getLugarentrega().getIdLugarentrega(),
                        horario.getHorariolugarentrega().getLugarentrega().getNombre(),
                        dep.get().getNombre() + "-" + pro.get().getNombre() + "-" + horario.getHorariolugarentrega().getLugarentrega().getDistrito().getNombre(),
                        horario.getFecha(), horario.getHoraInicio(), horario.getHoraFin(),
                        horario.getHorariolugarentrega().getLugarentrega().getAforo(),
                        mujeres, discapacitados,
                        horario.getHorariolugarentrega().getLugarentrega().getDistrito().getZonariesgo(), 1);
                lugares.add(cronograma);
            }else if(dato.getIddepartamento()!=null && dato.getIdprovincia()!=null && dato.getIddistrito()!=null &&
                    horario.getHorariolugarentrega().getLugarentrega().getDistrito().getIddistrito()==dato.getIddistrito()){
                ListaCronograma cronograma = new ListaCronograma(horario.getHorariolugarentrega().getLugarentrega().getIdLugarentrega(),
                        horario.getHorariolugarentrega().getLugarentrega().getNombre(),
                        dep.get().getNombre() + "-" + pro.get().getNombre() + "-" + horario.getHorariolugarentrega().getLugarentrega().getDistrito().getNombre(),
                        horario.getFecha(), horario.getHoraInicio(), horario.getHoraFin(),
                        horario.getHorariolugarentrega().getLugarentrega().getAforo(),
                        mujeres, discapacitados,
                        horario.getHorariolugarentrega().getLugarentrega().getDistrito().getZonariesgo(), 1);
                lugares.add(cronograma);
            }else{
                return reporte;
            }
        }
        ArrayList<ListaCronograma> fin=new ArrayList<>();
        //ya se tiene all ahora toca agrupar
        for(ListaCronograma lc: lugares){
            if(fin.isEmpty()==true){//si esta vacio se añade el primero
                fin.add(lc);
            }else{//si no esta vacio, se comprueba si ya existe
                int bandera=0;
                for(int i=0; i< fin.size(); i++){
                    if(fin.get(i).getFecha().equals(lc.getFecha()) && fin.get(i).getIdlugarentrega().equals(lc.getIdlugarentrega()) && fin.get(i).getHorainicio().equals(lc.getHorainicio()) && fin.get(i).getHorafin().equals(lc.getHorafin())){
                        //no se añade, solo se actualiza los valores
                        Integer ben=fin.get(i).getBeneficiarios() + lc.getBeneficiarios();
                        fin.get(i).setBeneficiarios(ben);
                        Integer discap=fin.get(i).getDiscapacitados() + lc.getDiscapacitados();
                        fin.get(i).setDiscapacitados(discap);
                        Integer muj=fin.get(i).getMujeres() + lc.getMujeres();
                        fin.get(i).setMujeres(muj);
                        ///se termina
                        bandera=0;
                        break;
                    }else{//se añade
                        bandera=1;
                    }
                }
                if(bandera==1){
                    fin.add(lc);
                }
            }
        }
        ///realizar las diviciones
        for(ListaCronograma l: fin){
            //filtrar por nombre
            if(l.getNombre().contains(dato.getNombre())){
                Hashtable<String,Object> lug=new Hashtable<>();
                lug.put("idlugarentrega",l.getIdlugarentrega());
                lug.put("nombre",l.getNombre());
                lug.put("locacion",l.getLocacion());
                lug.put("fecha",l.getFecha());
                lug.put("horainicio",l.getHorainicio());
                lug.put("horafin",l.getHorafin());
                lug.put("aforo",Math.round(l.getBeneficiarios().floatValue()/l.getAforo().floatValue() * 100 * 100)/100d  + "%");
                lug.put("mujeres",Math.round(l.getMujeres().floatValue()/l.getBeneficiarios().floatValue() * 100 * 100)/100d + "%");
                lug.put("discapacitados",Math.round(l.getDiscapacitados()/l.getBeneficiarios().floatValue() * 100 * 100)/100d + "%");
                lug.put("riesgo",l.getRiesgo());
                reporte.add(lug);
            }
        }

        return reporte;
    }*/
    //descargar cronograma dependiendo del ID de lugar de entrega que pasa,
    // imprimir todos sus atributos de los beneficiarios
    @PostMapping("/descargar")
    public ResponseEntity<Resource> descargar(@RequestBody Datos dato) throws IOException{
        String filename = "tutorials.xlsx";
        InputStreamResource file =generarExcel(dato);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    public ArrayList<ListaCronograma> funcion(Datos dato){
        //listar los horarios de ese cronograma
        ArrayList<Horario> horarios=new ArrayList<>();
        if(dato.getIddepartamento()!=null && dato.getIdprovincia()!=null && dato.getIddistrito()!=null){
            horarios=horarioRepository.listarhorariosbeneficiarios1(dato.getIdcronograma(),dato.getFechaini(),dato.getFechafin(),
                    dato.getIddepartamento(),dato.getIdprovincia(),dato.getIddistrito(),dato.getNombre());
        }else if(dato.getIddepartamento()!=null && dato.getIdprovincia()!=null && dato.getIddistrito()==null){
            horarios=horarioRepository.listarhorariosbeneficiarios2(dato.getIdcronograma(),dato.getFechaini(),dato.getFechafin(),
                    dato.getIddepartamento(),dato.getIdprovincia(),dato.getNombre());
        }else if(dato.getIddepartamento()!=null && dato.getIdprovincia()==null && dato.getIddistrito()==null){
            horarios=horarioRepository.listarhorariosbeneficiarios3(dato.getIdcronograma(),dato.getFechaini(),dato.getFechafin(),
                    dato.getIddepartamento(),dato.getNombre());
        }else if(dato.getIddepartamento()==null && dato.getIdprovincia()==null && dato.getIddistrito()==null){
            horarios=horarioRepository.listarhorariosbeneficiarios4(dato.getIdcronograma(),dato.getFechaini(),dato.getFechafin(),dato.getNombre());
        }
        String sexo="";
        String disca="";
        ArrayList<ListaCronograma> lugares= new ArrayList<>();
        for(Horario horario: horarios) {
            if(horario.getBeneficiario().getFemenino()){
                sexo="F";
            }else{
                sexo="M";
            }
            if(horario.getBeneficiario().getEsdiscapacitado()){
                disca="Si";
            }else{
                disca="No";
            }
            Optional<Distrito> dis = distritoRepository.findById(horario.getBeneficiario().getFiddistrito());
            Optional<Provincia> pro = provinciaRepository.findById(dis.get().getFidprovincia());
            Optional<Departamento> dep = departamentoRepository.findById(pro.get().getFiddepartamento());
            ListaCronograma cronograma = new ListaCronograma(horario.getBeneficiario().getIdbeneficiario(),horario.getBeneficiario().getCodigofamilia(),
                    dep.get().getNombre() , pro.get().getNombre(),dis.get().getNombre(),
                    sexo,disca,1,horario.getHorariolugarentrega().getLugarentrega().getNombre() + " - " +horario.getHorariolugarentrega().getLugarentrega().getDireccion(),
                    horario.getFecha(),horario.getHoraInicio(),
                    horario.getHoraFin(),horario.getEstado(),"-",null,null,null,"-",0);
            lugares.add(cronograma);
        }
        ArrayList<ListaCronograma> fin=new ArrayList<>();
        //ya se tiene all ahora toca agrupar
        for(ListaCronograma lc: lugares){
            if(fin.isEmpty()==true){//si esta vacio se añade el primero
                fin.add(lc);
            }else{//si no esta vacio, se comprueba si ya existe
                int bandera=0;
                for(int i=0; i< fin.size(); i++){
                    if(fin.get(i).getCodigofamilia().equals(lc.getCodigofamilia())){
                        //no se añade, solo se actualiza los valores
                        Integer a = fin.get(i).getNumhorarios() + lc.getNumhorarios();
                        fin.get(i).setNumhorarios(a);
                        fin.get(i).setCodigo2(lc.getCodigo1());
                        fin.get(i).setFecha2(lc.getFecha1());
                        fin.get(i).setHorainicio2(lc.getHorainicio1());
                        fin.get(i).setHorafin2(lc.getHorafin1());
                        fin.get(i).setEstado2(lc.getEstado1());
                        ///se termina
                        bandera=0;
                        break;
                    }else{//se añade
                        bandera=1;
                    }
                }
                if(bandera==1){
                    fin.add(lc);
                }
            }
        }
        return fin;
    }
    
    public InputStreamResource generarExcel (Datos dato) throws IOException{
        Logger LOGGER = Logger.getLogger("mx.com.hash.newexcel.ExcelOOXML");
        File archivo = new File("cronograma.xlsx");
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        //ServletOutputStream outputStream = response.getOutputStream();
        Sheet pagina = workbook.createSheet("Cronograma");
        // Creamos el estilo paga las celdas del encabezado
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        String[] titulos = {"Beneficiario","Departamento","Provincia","Distrito","Sexo",
                "Discapacitado","Números de Horarios", "Fecha 1","Horario 1","Lugar de entrega 1",
                "Estado 1","Fecha 2","Horario 2","Lugar de entrega 2","Estado 2"};
        //Llamar a función
        ArrayList<ListaCronograma> lista=funcion(dato);
        ////lista
        Row fila = pagina.createRow(0);
        // Creamos el encabezado
        for (int i = 0; i < titulos.length; i++) {
            Cell celda = fila.createCell(i);
            celda.setCellStyle(style);
            celda.setCellValue(titulos[i]);
        }
        for (int i = 0; i < lista.size(); i++) {
            fila = pagina.createRow(i+1);
            Cell celda = fila.createCell(0);
            celda.setCellValue(lista.get(i).getCodigofamilia());
            Cell celda1 = fila.createCell(1);
            celda1.setCellValue(lista.get(i).getDepartamento());
            Cell celda2 = fila.createCell(2);
            celda2.setCellValue(lista.get(i).getProvincia());
            Cell celda3 = fila.createCell(3);
            celda3.setCellValue(lista.get(i).getDistrito());
            Cell celda4 = fila.createCell(4);
            celda4.setCellValue(lista.get(i).getSexo());
            Cell celda5 = fila.createCell(5);
            celda5.setCellValue(lista.get(i).getDiscapacitado());
            Cell celda6 = fila.createCell(6);
            celda6.setCellValue(lista.get(i).getNumhorarios());
            Cell celda7 = fila.createCell(7);
            celda7.setCellValue(lista.get(i).getFecha1().toString());
            Cell celda8 = fila.createCell(8);
            celda8.setCellValue(lista.get(i).getHorainicio1()+" - "+lista.get(i).getHorafin1());
            Cell celda9 = fila.createCell(9);
            celda9.setCellValue(lista.get(i).getCodigo1());
            Cell celda10 = fila.createCell(10);
            if(lista.get(i).getEstado1().contains("ENT")){
                celda10.setCellValue("Entregado");
            }else if(lista.get(i).getEstado1().contains("NOE") && (Boolean.valueOf(lista.get(i).getFecha1().isBefore(dato.getFechaactual())) ||
                    Boolean.valueOf(lista.get(i).getFecha1().isEqual(dato.getFechaactual())))){
                celda10.setCellValue("No entregado");
            }else{
                celda10.setCellValue("Programado");
            }
            Cell celda11 = fila.createCell(11);
            Cell celda12 = fila.createCell(12);
            Cell celda13 = fila.createCell(13);
            Cell celda14 = fila.createCell(14);
            if(lista.get(i).getFecha2()==null){
                celda11.setCellValue("-");
                celda12.setCellValue("-");
                celda13.setCellValue("-");
                celda14.setCellValue("-");
            }else{
                celda11.setCellValue(lista.get(i).getFecha2().toString());
                celda12.setCellValue(lista.get(i).getHorainicio2()+" - "+lista.get(i).getHorafin2());
                celda13.setCellValue(lista.get(i).getCodigo2());
                if(lista.get(i).getEstado2().contains("ENT")){
                    celda14.setCellValue("Entregado");
                }else if(lista.get(i).getEstado2().contains("NOE") && (Boolean.valueOf(lista.get(i).getFecha2().isBefore(dato.getFechaactual())) ||
                        Boolean.valueOf(lista.get(i).getFecha2().isEqual(dato.getFechaactual())))){
                    celda14.setCellValue("No entregado");
                }else{
                    celda14.setCellValue("Programado");
                }
            }
        }
        // Ahora guardaremos el archivo
        //try {
        //FileOutputStream salida = new FileOutputStream(archivo);
        workbook.write(salida);
        ByteArrayInputStream in = new ByteArrayInputStream(salida.toByteArray());
        return new InputStreamResource(in);
            // Cerramos el libro para concluir operaciones
            //workbook.close();
        /*    LOGGER.log(Level.INFO, "Archivo creado existosamente en {0}", archivo.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Archivo no localizable en sistema de archivos");
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Error de entrada/salida");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @PostMapping("/generarcronograma")
    public Cronograma generarcronograma(@RequestBody Datos dato) {

        //Todos los lugares atienden de lunes a viernes
        ArrayList<LugarEntregaAlgoritmo> lugarentregas=lugarEntregaAlgoritmoRepository.findAllByLugarEntregaAlgoritmo();
        ArrayList<BeneficiarioAlgoritmo> beneficiarios=beneficiarioAlgoritmoRepository.findAllByBeneficiarioAlgoritmo();

        ArrayList<ArrayList<HorarioLugarEntregaAlgoritmo>> lugaresXdia = new ArrayList<>();
        //Consultar la lista de lugares por día
        String dias[] = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};

        for(int i=0; i<dias.length; i++){
            ArrayList<HorarioLugarEntregaAlgoritmo> ldia=horarioLugarEntregaAlgoritmoRepository.findLugarXDia(dias[i]);
            lugaresXdia.add(ldia);
            System.out.println("dia: "+dias[i]+ldia.size());
        }

        Genetic algoritmoGenetico=new Genetic();

        //Creamos el cronograma
        Cronograma cronograma = new Cronograma();
        cronograma.setNombre(dato.getNombre());
        cronograma.setFechaInicio(dato.getFechaini());
        cronograma.setFechaFin(dato.getFechaini()); //posteriormente actualizaremos la fecha fin
        cronograma.setUsuarioCreacion(dato.getUsuariocreacion());
        cronograma.setEstado("GEN");


        //Establecemos el día y fecha de inicio
        //Se recorre día por día
        //Nota: Enero-Diciembre (0-11)
        //Lunes es 1 - Jueves es 4
        //Nos ubicamos en el día anterior
        Calendar c = new GregorianCalendar(cronograma.getFechaInicio().getYear(),
                cronograma.getFechaInicio().getMonthValue()-1,
                cronograma.getFechaInicio().getDayOfMonth()-1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int diaSemana = c.get(Calendar.DAY_OF_WEEK);
        System.out.println(c.getTime().toString());
        System.out.println(diaSemana);
        Hashtable<String,Hashtable<String,ArrayList<Item>>> horarios = algoritmoGenetico.generarCronograma(lugarentregas,beneficiarios, lugaresXdia, c);
        System.out.println(c.getTime().toString());
        System.out.println(diaSemana);
        //new java.sql.Date(c.getTime().getTime()).toLocalDate()
        //Insertamos el cronograma

        Cronograma croGen = cronogramaRepository.save(cronograma);
        Set<String> clHorarios = horarios.keySet();



        //Recorremos cada día
        for (String llaveDia : clHorarios) {

            Hashtable<String,ArrayList<Item>> horGen =horarios.get(llaveDia);

            Calendar c2 = new GregorianCalendar(Integer.parseInt(llaveDia.substring(0,4)),
                    Integer.parseInt(llaveDia.substring(5,7))-1,Integer.parseInt(llaveDia.substring(8)));

            //Conseguir los lugares de entrega de la fecha seleccionada
            int diaLlaveDia = c2.get(Calendar.DAY_OF_WEEK);
            System.out.println(c2.getTime().toString());
            ArrayList<HorarioLugarEntregaAlgoritmo> lugaresLlaveDia = lugaresXdia.get(diaLlaveDia-1);

            //Recorremos todos los lugares de entrega de un día
            for(int j=0; j<lugaresLlaveDia.size(); j++){

                Horariolugarentrega horariolugarentrega=new Horariolugarentrega();
                Beneficiario beneficiario = new Beneficiario();

                //Turno mañana
                ArrayList<Item> beneficiariosLEM= horGen.get(lugaresLlaveDia.get(j).getCodigo()+"M");
                if(beneficiariosLEM != null) {
                    for (int k = 0; k < beneficiariosLEM.size(); k++) {
                        //System.out.println("k1: "+k+ "Beneficiroa: "+beneficiariosLEM.get(k).getIdbd() + "Lugar Entrega: "+ lugarentregas.get(j).getIdlugarentrega());
                        Horario horario1=new Horario();
                        horario1.setCronograma(croGen);

                        beneficiario.setIdbeneficiario(beneficiariosLEM.get(k).getIdbd());
                        horario1.setBeneficiario(beneficiario);

                        horariolugarentrega.setIdHorariolugarentrega(lugaresLlaveDia.get(j).getIdhorariolugarentrega());
                        horario1.setHorariolugarentrega(horariolugarentrega);

                        horario1.setEstado("NOE");
                        horario1.setFecha(new java.sql.Date(c2.getTime().getTime()).toLocalDate());

                        horario1.setHoraInicio(lugaresLlaveDia.get(j).getHoraaperturaturnoma());
                        horario1.setHoraFin(lugaresLlaveDia.get(j).getHoracierreturnoma());
                        System.out.println("horaIni, horafin: "+lugaresLlaveDia.get(j).getHoraaperturaturnoma()+" "+lugaresLlaveDia.get(j).getHoracierreturnoma());

                        horario1.setUsuarioCreacion(dato.getUsuariocreacion());
                        horario1=horarioRepository.save(horario1);
                        System.out.println(horario1.getIdHorario());

                    }
                }


                //Turno tarde
                ArrayList<Item> beneficiariosLET= horGen.get(lugaresLlaveDia.get(j).getCodigo()+"T");
                if(beneficiariosLET != null) {
                    for (int k = 0; k < beneficiariosLET.size(); k++) {
                        //System.out.println("k2: "+k+ "Beneficiroa: "+beneficiariosLET.get(k).getIdbd()+ "Lugar Entrega: "+ lugaresLlaveDia.get(j).getIdlugarentrega());
                        Horario horario2=new Horario();
                        horario2.setCronograma(croGen);

                        beneficiario.setIdbeneficiario(beneficiariosLET.get(k).getIdbd());
                        horario2.setBeneficiario(beneficiario);

                        horariolugarentrega.setIdHorariolugarentrega(lugaresLlaveDia.get(j).getIdhorariolugarentrega());
                        horario2.setHorariolugarentrega(horariolugarentrega);

                        horario2.setEstado("NOE");
                        horario2.setFecha(new java.sql.Date(c2.getTime().getTime()).toLocalDate());

                        horario2.setHoraInicio(lugaresLlaveDia.get(j).getHoraaperturaturnotar());
                        horario2.setHoraFin(lugaresLlaveDia.get(j).getHoracierreturnotar());

                        System.out.println("horaIni, horafin: "+lugaresLlaveDia.get(j).getHoraaperturaturnotar()+" "+lugaresLlaveDia.get(j).getHoracierreturnotar());

                        horario2.setUsuarioCreacion(dato.getUsuariocreacion());
                        horario2=horarioRepository.save(horario2);
                        System.out.println(horario2.getIdHorario());
                    }
                }

            }


            //horarioRepository.saveAll();
        }

        //Actualizamos la fecha fin del cronograma
        //Corregir fecha fin
        croGen.setFechaFin(new java.sql.Date(c.getTime().getTime()).toLocalDate());
        croGen=cronogramaRepository.save(croGen);

        return croGen;


    }

    @PostMapping("/reportebeneficiarios")
    public Hashtable<String, Integer> reportebeneficiarios() {
        Hashtable<String, Integer> reporte=new Hashtable<>();
        int cantMujeres=0, cantHombres=0, cantDisc=0, cantQuejas=0;
        cantMujeres=beneficiarioRepository.countBeneficiariosByFemeninoIsAndEstado(true, "ACT");
        cantHombres=beneficiarioRepository.countBeneficiariosByMasculinoIsAndEstado(true, "ACT");
        cantDisc=beneficiarioRepository.countBeneficiariosByEsdiscapacitadoAndEstado(true, "ACT");
        cantQuejas=quejasRepository.countQuejasByHorario_Cronograma_EstadoAndEstado("PUB","ACT");

        //cantHombres=horarioRepository.countHorarioByCronograma_EstadoAndBeneficiario_Femenino("ACT", true);
        //ArrayList<Object> enteros=beneficiarioRepository.indicadoresBeneficiario();

        reporte.put("cantmujeres",cantMujeres);
        reporte.put("canthombres",cantHombres);
        reporte.put("cantdisc",cantDisc);
        reporte.put("cantquejas",cantQuejas);

        return reporte;
    }

    @PostMapping("/monitoreoentregabono")
    public Hashtable<String,ArrayList<Object>> monitoreoentregabono(@RequestBody Datos dato) {
        ArrayList<ArrayList<Object>> listaBonosEntregados;
        Hashtable<String,ArrayList<Object>> grafico =new Hashtable<>();

        if(dato.getListaDepartamentos().size()>0 && dato.getListaProvincias().size()>0 && dato.getListaDistritos().size()>0)
            listaBonosEntregados=horarioRepository.monitoreoEntregaXDistrito(dato.getListaDistritos(),dato.getFechaini(), dato.getFechafin());
        else if (dato.getListaDepartamentos().size()>0 && dato.getListaProvincias().size()>0)
            listaBonosEntregados=horarioRepository.monitoreoEntregaXProvincia(dato.getListaProvincias(),dato.getFechaini(), dato.getFechafin());
        else if (dato.getListaDepartamentos().size()>0)
            listaBonosEntregados=horarioRepository.monitoreoEntregaXDepartamento(dato.getListaDepartamentos(),dato.getFechaini(), dato.getFechafin());
        else
            listaBonosEntregados=horarioRepository.monitoreoEntrega(dato.getFechaini(), dato.getFechafin());

        ArrayList<Object> listaFechas =new ArrayList<>();
        ArrayList<Object> listaCantidades = new ArrayList<>();

        for(int i =0; i< listaBonosEntregados.size(); i++){
            String fecha= listaBonosEntregados.get(i).get(0).toString();
            String fechaP= fecha.substring(8,10)+"-"+fecha.substring(5,7)+"-"+fecha.substring(0,4);
            listaFechas.add(fechaP);
            listaCantidades.add(listaBonosEntregados.get(i).get(1));
        }

        grafico.put("listaFechas", listaFechas);
        grafico.put("listaCantidades", listaCantidades);

        return grafico;
    }

    @PostMapping("/monitoreotoplugares")
    public Hashtable<String,ArrayList<Object>> monitoreotoplugares(@RequestBody Datos dato){
        Hashtable<String,ArrayList<Object>> grafico =new Hashtable<>();
        ArrayList<ArrayList<Object>> listaPeoresLugares;

        if(dato.getListaDepartamentos().size()>0)
            listaPeoresLugares=lugarEntregaRepository.topPeoresLugaresXDepartamento(dato.getListaDepartamentos(),dato.getFechaini(), dato.getFechafin().plusDays(1));
        else if (dato.getListaProvincias().size()>0)
            listaPeoresLugares=lugarEntregaRepository.topPeoresLugaresXProvincia(dato.getListaProvincias(),dato.getFechaini(), dato.getFechafin().plusDays(1));
        else if (dato.getListaDistritos().size()>0)
            listaPeoresLugares=lugarEntregaRepository.topPeoresLugaresXDistrito(dato.getListaDistritos(),dato.getFechaini(), dato.getFechafin().plusDays(1));
        else
            listaPeoresLugares=lugarEntregaRepository.topPeoresLugares(dato.getFechaini(), dato.getFechafin().plusDays(1));


        ArrayList<Object> listaLugares =new ArrayList<>();
        ArrayList<Object> listaCantidades = new ArrayList<>();

        for(int i =0; i< listaPeoresLugares.size(); i++){
            listaLugares.add(listaPeoresLugares.get(i).get(0));
            listaCantidades.add(listaPeoresLugares.get(i).get(1));
        }

        grafico.put("listaLugares", listaLugares);
        grafico.put("listaCantidades", listaCantidades);
        return grafico;
    }

    @PostMapping("/monitoreoavanceentrega")
    public Hashtable<String,ArrayList<Object>> monitoreoavanceentrega(@RequestBody Datos dato){
        Hashtable<String,ArrayList<Object>> grafico =new Hashtable<>();
        ArrayList<Object> listanombres =new ArrayList<>();
        ArrayList<Object> listacantidades = new ArrayList<>();
        Integer cantnoentre = 0;
        Integer cantentre= 0;
        Integer cantpend= 0;
        ArrayList<Horario> hor,horpend =new ArrayList<>();

        ArrayList<ArrayList<Integer>> cantidades = horarioRepository.avanceentregamonitoreo(dato.getFechaini(), dato.getFechafin(), dato.getFechaactual());



        listanombres.add("entregados");
        listanombres.add("noentregados");
        listanombres.add("pendientes");
        listacantidades.add(cantidades.get(0).get(0));
        listacantidades.add(cantidades.get(0).get(1));
        listacantidades.add(cantidades.get(0).get(2));
        grafico.put("listanombres",listanombres);
        grafico.put("listacantidades",listacantidades);
        return grafico;
    }

    @PostMapping("/resumencronograma")
    public Hashtable<String, Object> resumenCronograma() {
        Hashtable<String, Object> respuesta=new Hashtable<>();
        ArrayList<Beneficiario> bene=beneficiarioRepository.findBeneficiariosByEstado("ACT");
        List<Horario> hor=horarioRepository.findAllByCronograma_EstadoOrCronograma_Estado("PUB","GEN");
        //System.out.println(hor);
        ArrayList<Integer> lugares=new ArrayList<>();
        Integer id=0;
        for (Horario horario:hor) {
            id=horario.getHorariolugarentrega().getLugarentrega().getIdLugarentrega();
            if(!lugares.contains(id)){
                lugares.add(id);
            }
        }
        Optional<Cronograma> crog=cronogramaRepository.findCronogramaByEstadoOrEstado("PUB","GEN");
        if(crog.isPresent()){
            respuesta.put("idcronograma",crog.get().getIdCronograma());
            respuesta.put("nombre",crog.get().getNombre());
            respuesta.put("fechaini",crog.get().getFechaInicio());
            respuesta.put("fechafin",crog.get().getFechaFin());
            respuesta.put("fechaactualizacion", crog.get().getFechaActualizacion());
            respuesta.put("beneficiarios",bene.size());
            respuesta.put("lugares",lugares.size());
            respuesta.put("estado",crog.get().getEstado());
        }else{
            respuesta.put("idcronograma","");
            respuesta.put("nombre","");
            respuesta.put("fechaini","");
            respuesta.put("fechafin","");
            respuesta.put("fechaactualizacion", "");
            respuesta.put("beneficiarios",bene.size());
            List<Lugarentrega> lug=lugarEntregaRepository.findAllByEstado("ACT");
            respuesta.put("lugares",lug.size());
            respuesta.put("estado","");
        }
        return respuesta;
    }

    @PostMapping("/historico")
    public ArrayList<Historico> historico() {
        ArrayList<Historico> resp=historicoRepository.monitoreo();
        return resp;
    }

    @PostMapping("/publicar/{idcronograma}")
    public Cronograma listarDistrito(@PathVariable Integer idcronograma) {
        Cronograma  croD=new Cronograma();
        croD.setIdCronograma(0);
        Optional<Cronograma> cro=cronogramaRepository.findById(idcronograma);
        if(cro.isPresent()){
            cro.get().setEstado("PUB");
            cronogramaRepository.save(cro.get());
            return cro.get();
        }
        return croD;
    }

    @PostMapping("/cronograma_publicado")
    public Optional<Cronograma> cronogramaPublicado() {
        Optional<Cronograma> crog = cronogramaRepository.findCronogramaByEstadoOrEstado("PUB","GEN");
        return crog;
    }

    @PostMapping("/listar_cronogramas")
    public ArrayList<Cronograma> listarCronogramas() {
        ArrayList<Cronograma> cronogramas;
        cronogramas = cronogramaRepository.findCronogramasByEstadoIsNot("ELI");
        return cronogramas;
    }

}
