package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.Cronograma;
import com.bonoperubackend.BonoPeruBackend.Repositorios.CronogramaRepository;
import com.bonoperubackend.BonoPeruBackend.Repositorios.IncidenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/incidente")
public class IncidenteController {

    @Autowired
    IncidenteRepository incidenteRepository;

    @Autowired
    CronogramaRepository cronogramaRepository;

    public static class Datos {
        private static LocalDate fechaini;
        private static LocalDate fechafin;
        private static Integer iddepartamento;
        private static Integer idprovincia;
        private static Integer iddistrito;
        private static List<Integer> cronogramas;
        public Datos() {
            super();
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

        public List<Integer> getCronogramas() {
            return cronogramas;
        }

        public void setCronogramas(List<Integer> cronogramas) {
            Datos.cronogramas = cronogramas;
        }
    }

    @PostMapping("/reporteinicial")
    public Hashtable<String,ArrayList<Object>> reporte1(@RequestBody Datos dato) {
        Hashtable<String,ArrayList<Object>> grafico =new Hashtable<>();
        ArrayList<ArrayList<Integer>> cantidades=incidenteRepository.reporteIncidentes(dato.getFechaini(),dato.getFechafin());

        ArrayList<Object> listanombres =new ArrayList<>();
        listanombres.add("lugares");
        listanombres.add("horarios");
        listanombres.add("dias");

        ArrayList<Object> listacantidades = new ArrayList<>();
        listacantidades.add(cantidades.get(0).get(0));
        listacantidades.add(cantidades.get(0).get(1));
        listacantidades.add(cantidades.get(0).get(2));

        grafico.put("listanombres",listanombres);
        grafico.put("listacantidades",listacantidades);
        return grafico;
    }

    @PostMapping("/reporte")
    public Hashtable<String,ArrayList<Object>> reporte2(@RequestBody Datos dato) {
        Hashtable<String,ArrayList<Object>> grafico =new Hashtable<>();
        ArrayList<Object> listacronogramas =new ArrayList<>();
        ArrayList<Object> listalugares = new ArrayList<>();
        ArrayList<Object> listahorarios = new ArrayList<>();
        ArrayList<Object> listadias = new ArrayList<>();
        ArrayList<Object> listaids = new ArrayList<>();

        ArrayList<ArrayList<Object>> respuesta=new ArrayList<ArrayList<Object>>();

        if(dato.getIddepartamento()!=null && dato.getIdprovincia()!=null && dato.getIddistrito()!=null){
            respuesta=incidenteRepository.reporteIncidentesCronograma(dato.getFechaini(),dato.getFechafin(),
                    dato.getIddepartamento(),dato.getIdprovincia(),dato.getIddistrito(),dato.getCronogramas());
        }else if(dato.getIddepartamento()!=null && dato.getIdprovincia()!=null && dato.getIddistrito()==null){
            respuesta=incidenteRepository.reporteIncidentesCronograma1(dato.getFechaini(),dato.getFechafin(),
                    dato.getIddepartamento(),dato.getIdprovincia(),dato.getCronogramas());
        }else if(dato.getIddepartamento()!=null && dato.getIdprovincia()==null && dato.getIddistrito()==null){
            respuesta=incidenteRepository.reporteIncidentesCronograma2(dato.getFechaini(),dato.getFechafin(),
                    dato.getIddepartamento(),dato.getCronogramas());
        }else if(dato.getIddepartamento()==null && dato.getIdprovincia()==null && dato.getIddistrito()==null){
            respuesta=incidenteRepository.reporteIncidentesCronograma3(dato.getFechaini(),dato.getFechafin(),dato.getCronogramas());
        }

        for(int i =0; i< respuesta.size(); i++){
            listaids.add(respuesta.get(i).get(0));
            listacronogramas.add(respuesta.get(i).get(1));
            listalugares.add(respuesta.get(i).get(2));
            listahorarios.add(respuesta.get(i).get(3));
            listadias.add(respuesta.get(i).get(4));
        }
        for(int i=0;i< dato.getCronogramas().size(); i++){
            if(!listaids.contains(dato.getCronogramas().get(i))){
                //buscar el nombre
                Integer id= (Integer) dato.getCronogramas().get(i);
                Optional<Cronograma> cg=cronogramaRepository.findById(id);
                if(cg.isPresent()){
                    //agregar 0's
                    listacronogramas.add(cg.get().getNombre());
                    listalugares.add(0);
                    listahorarios.add(0);
                    listadias.add(0);
                }
            }
        }
        grafico.put("listacronogramas",listacronogramas);
        grafico.put("listalugares",listalugares);
        grafico.put("listahorarios",listahorarios);
        grafico.put("listadias",listadias);
        return grafico;
    }

}
