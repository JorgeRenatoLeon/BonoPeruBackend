package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.*;
import com.bonoperubackend.BonoPeruBackend.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/quejas")
public class QuejasController {
    @Autowired
    ListaCronogramaRepository listaCronogramaRepository;

    @Autowired
    LugarEntregaRepository lugarEntregaRepository;

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    CronogramaRepository cronogramaRepository;

    @Autowired
    QuejasRepository quejasRepository;

    public static class Entrada {
        private static LocalDate fechaini;
        private static LocalDate fechafi;
        private static Integer iddepartamento;
        private static Integer idprovincia;
        private static Integer iddistrito;
        private static List<Integer> cronogramas;
        public Entrada() {}

        public LocalDate getFechaini() {
            return fechaini;
        }

        public void setFechaini(LocalDate fechaini) {
            Entrada.fechaini = fechaini;
        }

        public LocalDate getFechafi() {
            return fechafi;
        }

        public void setFechafi(LocalDate fechafi) {
            Entrada.fechafi = fechafi;
        }

        public Integer getIddepartamento() {
            return iddepartamento;
        }

        public void setIddepartamento(Integer iddepartamento) {
            Entrada.iddepartamento = iddepartamento;
        }

        public Integer getIdprovincia() {
            return idprovincia;
        }

        public void setIdprovincia(Integer idprovincia) {
            Entrada.idprovincia = idprovincia;
        }

        public Integer getIddistrito() {
            return iddistrito;
        }

        public void setIddistrito(Integer iddistrito) {
            Entrada.iddistrito = iddistrito;
        }

        public List<Integer> getCronogramas() {
            return cronogramas;
        }

        public void setCronogramas(List<Integer> cronogramas) {
            Entrada.cronogramas = cronogramas;
        }
    }

    @PostMapping("/reportequejas")
    public Hashtable<String,ArrayList<Object>> quejas(@RequestBody Entrada data) {
        Hashtable<String,ArrayList<Object>> grafico =new Hashtable<>();
        ArrayList<Object> listanombres =new ArrayList<>();
        ArrayList<Object> listacantidades = new ArrayList<>();
        Integer cantLugar = 0;
        Integer cantHorario= 0;
        ArrayList<Cronograma> crog=cronogramaRepository.findAll();
        for (Cronograma c:crog) {
            ArrayList<Horario> hor=horarioRepository.findAllByCronograma_IdCronogramaAndFechaGreaterThanEqualAndFechaLessThanEqual(c.getIdCronograma(),data.getFechaini(),data.getFechafi());
            for(Horario h: hor){
                ArrayList<Quejas> quejas= quejasRepository.findAllByHorario_IdHorarioAndBeneficiario_IdbeneficiarioAndLugarentrega_IdLugarentrega(h.getIdHorario(),h.getBeneficiario().getIdbeneficiario(),h.getHorariolugarentrega().getLugarentrega().getIdLugarentrega());
                for(Quejas q:quejas){
                    if(q.getTipoQueja().equals("lugar")){
                        cantLugar=cantLugar+1;
                    }else if(q.getTipoQueja().equals("horario")){
                        cantHorario=cantHorario+1;
                    }
                }
            }
        }
        listanombres.add("Lugares");
        listanombres.add("Horarios");
        listacantidades.add(cantLugar);
        listacantidades.add(cantHorario);
        grafico.put("listanombres",listanombres);
        grafico.put("listacantidades",listacantidades);
        return grafico;
    }

    @PostMapping("/reporte")
    public Hashtable<String,ArrayList<Object>> reportequejas(@RequestBody Entrada data) {
        Hashtable<String,ArrayList<Object>> grafico =new Hashtable<>();
        ArrayList<Object> listacronogramas =new ArrayList<>();
        ArrayList<Object> listalugares = new ArrayList<>();
        ArrayList<Object> listahorarios = new ArrayList<>();
        ArrayList<Object> listaids = new ArrayList<>();
        ArrayList<ArrayList<Object>> respuesta= new ArrayList<ArrayList<Object>>();
        if(data.getIddepartamento()!=null && data.getIdprovincia()!=null && data.getIddistrito()!=null){
            respuesta=quejasRepository.reportequejas(data.getFechaini(),data.getFechafi(),data.getIddepartamento(),data.getIdprovincia(),data.getIddistrito(),data.getCronogramas());
        }else if(data.getIddepartamento()!=null && data.getIdprovincia()!=null && data.getIddistrito()==null){
            respuesta=quejasRepository.reportequejas1(data.getFechaini(),data.getFechafi(),data.getIddepartamento(),data.getIdprovincia(),data.getCronogramas());
        }else if(data.getIddepartamento()!=null && data.getIdprovincia()==null && data.getIddistrito()==null){
            respuesta=quejasRepository.reportequejas2(data.getFechaini(),data.getFechafi(),data.getIddepartamento(),data.getCronogramas());
        }else if(data.getIddepartamento()==null && data.getIdprovincia()==null && data.getIddistrito()==null){
            respuesta=quejasRepository.reportequejas3(data.getFechaini(),data.getFechafi(),data.getCronogramas());
        }
        for(int i =0; i< respuesta.size(); i++){
            listaids.add(respuesta.get(i).get(0));
            listacronogramas.add(respuesta.get(i).get(1));
            listalugares.add(respuesta.get(i).get(2));
            listahorarios.add(respuesta.get(i).get(3));
        }
        for(int i=0;i< data.getCronogramas().size(); i++){
            if(!listaids.contains(data.getCronogramas().get(i))){
                //buscar el nombre
                Integer id= (Integer) data.getCronogramas().get(i);
                Optional<Cronograma> cg=cronogramaRepository.findById(id);
                if(cg.isPresent()){
                    //agregar 0's
                    listacronogramas.add(cg.get().getNombre());
                    listalugares.add(0);
                    listahorarios.add(0);
                }
            }
        }
        grafico.put("listacronogramas",listacronogramas);
        grafico.put("listalugares",listalugares);
        grafico.put("listahorarios",listahorarios);
        return grafico;
    }

    public static class Data {
        private static Integer fidbeneficiario;
        private static Integer fidhorario;
        private static Integer fidlugarentrega;
        private static String tipoqueja1;
        private static ArrayList<String> descripcion1;
        private static String tipoqueja2;
        private static ArrayList<String>  descripcion2;
        public Data() {}
        public Integer getFidbeneficiario() {
            return fidbeneficiario;
        }
        public void setFidbeneficiario(Integer fidbeneficiario) {
            Data.fidbeneficiario = fidbeneficiario;
        }
        public Integer getFidhorario() {
            return fidhorario;
        }
        public void setFidhorario(Integer fidhorario) {
            Data.fidhorario = fidhorario;
        }
        public Integer getFidlugarentrega() {
            return fidlugarentrega;
        }
        public void setFidlugarentrega(Integer fidlugarentrega) {
            Data.fidlugarentrega = fidlugarentrega;
        }
        public String getTipoqueja1() {
            return tipoqueja1;
        }
        public void setTipoqueja1(String tipoqueja1) {
            Data.tipoqueja1 = tipoqueja1;
        }
        public ArrayList<String> getDescripcion1() {
            return descripcion1;
        }
        public void setDescripcion1(ArrayList<String> descripcion1) {
            Data.descripcion1 = descripcion1;
        }
        public String getTipoqueja2() {
            return tipoqueja2;
        }
        public void setTipoqueja2(String tipoqueja2) {
            Data.tipoqueja2 = tipoqueja2;
        }
        public ArrayList<String> getDescripcion2() {
            return descripcion2;
        }
        public void setDescripcion2(ArrayList<String> descripcion2) {
            Data.descripcion2 = descripcion2;
        }
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Data dato){
        //cuando selecciona LUGAR
        if(dato.getTipoqueja1().equals("lugar")){
            //verificar
            Optional<Quejas> ver=quejasRepository.findByBeneficiario_IdbeneficiarioAndHorario_IdHorarioAndLugarentrega_IdLugarentregaAndTipoQueja(dato.getFidbeneficiario(),dato.getFidhorario(),dato.getFidlugarentrega(),dato.getTipoqueja1());
            if(!ver.isPresent()){
                Quejas q=new Quejas();
                Optional<Horario> h=horarioRepository.findById(dato.getFidhorario());
                q.setHorario(h.get());
                Optional<Beneficiario> b=beneficiarioRepository.findById(dato.getFidbeneficiario());
                q.setBeneficiario(b.get());
                Optional<Lugarentrega> l=lugarEntregaRepository.findById(dato.getFidlugarentrega());
                q.setLugarentrega(l.get());
                q.setTipoQueja(dato.getTipoqueja1());
                String des="";
                for(int i=0;i<dato.getDescripcion1().size();i++){
                    if(i==0){
                        des= des + dato.getDescripcion1().get(i);
                    }else{
                        des= des + ", " +dato.getDescripcion1().get(i);
                    }
                }
                q.setDescripcion(des);
                quejasRepository.save(q);
            }
        }
        //cuando selecciona HORARIO
        if(dato.getTipoqueja2().equals("horario")){
            Optional<Quejas> ver=quejasRepository.findByBeneficiario_IdbeneficiarioAndHorario_IdHorarioAndLugarentrega_IdLugarentregaAndTipoQueja(dato.getFidbeneficiario(),dato.getFidhorario(),dato.getFidlugarentrega(),dato.getTipoqueja2());
            if(!ver.isPresent()){
                Quejas q=new Quejas();
                Optional<Horario> h=horarioRepository.findById(dato.getFidhorario());
                q.setHorario(h.get());
                Optional<Beneficiario> b=beneficiarioRepository.findById(dato.getFidbeneficiario());
                q.setBeneficiario(b.get());
                Optional<Lugarentrega> l=lugarEntregaRepository.findById(dato.getFidlugarentrega());
                q.setLugarentrega(l.get());
                q.setTipoQueja(dato.getTipoqueja2());
                String des="";
                for(int i=0;i<dato.getDescripcion2().size();i++){
                    if(i==0){
                        des= des + dato.getDescripcion2().get(i);
                    }else{
                        des= des + ", " +dato.getDescripcion2().get(i);
                    }
                }
                q.setDescripcion(des);
                quejasRepository.save(q);
            }
        }
        if(dato.getTipoqueja1().isEmpty() && dato.getTipoqueja2().isEmpty()){
            //si marco en all NO
            return;
        }
    }
}
