package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.*;
import com.bonoperubackend.BonoPeruBackend.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/horario")
public class HorarioController {

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    IncidenteRepository incidenteRepository;

    @Autowired
    LugarEntregaRepository lugarEntregaRepository;

    @Autowired
    RespuestaEncuestaRepository respuestaEncuestaRepository;

    @Autowired
    RespuestaIndividualRepository respuestaIndividualRepository;

    @Autowired
    PreguntaRepository preguntaRepository;

    @Autowired
    CronogramaRepository cronogramaRepository;

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    public static class Entrada {
        private static Integer idlugarentrega;
        private static String codigofamilia;
        private static LocalTime hora;
        private static LocalDate dia;
        public Entrada() {
            super();
        }
        public Integer getIdlugarentrega() {
            return idlugarentrega;
        }

        public void setIdlugarentrega(Integer idlugarentrega) {
            Entrada.idlugarentrega = idlugarentrega;
        }

        public String getCodigofamilia() {
            return codigofamilia;
        }

        public void setCodigofamilia(String codigofamilia) {
            Entrada.codigofamilia = codigofamilia;
        }

        public LocalTime getHora() {
            return hora;
        }

        public void setHora(LocalTime hora) {
            Entrada.hora = hora;
        }

        public LocalDate getDia() {
            return dia;
        }

        public void setDia(LocalDate dia) {
            Entrada.dia = dia;
        }
    }

    @PostMapping("/consultarCodigoFamilia")
    public String consultarCodigoFamilia(@RequestBody Entrada entr) {
        String mensaje="";
        Integer idlugarentrega=entr.getIdlugarentrega() ;
        String codigofamilia=entr.getCodigofamilia();
        //LocalTime horai=LocalTime.now();
        LocalTime horai=entr.getHora().minusHours(5);
        LocalDate dia=entr.getDia();
        //LocalTime horaf=LocalTime.now();
        LocalTime horaf=entr.getHora();
        Optional<Beneficiario> l=beneficiarioRepository.findByCodigofamilia(codigofamilia);
        if(!l.isPresent()){
            mensaje="no bono";
        }else{
            Optional<Horario> horario=horarioRepository.findhorarios(idlugarentrega,codigofamilia,horai,dia);
            if(horario.isPresent()){
                //existe un horario, tiene el bono
                if (horario.get().getHoraInicio().isAfter(horai) || horario.get().getHoraFin().isBefore(horaf)) {
                    mensaje = "horario";//No le corresponde en este horario
                    Optional<Lugarentrega> lug= lugarEntregaRepository.findById(idlugarentrega);
                    Optional<Cronograma> cro= cronogramaRepository.findByEstado("PUB");
                    Incidente inc=new Incidente(dia,"Horario","ACT",horario.get().getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
                    //insertar penalidad
                    Optional<Beneficiario> be=beneficiarioRepository.findByCodigofamilia(codigofamilia);
                    float pen=be.get().getPenalidad()+1;
                    be.get().setPenalidad(pen);
                    beneficiarioRepository.save(be.get());
                }else{
                    if(horario.get().getEstado().contains("ENT")){
                        mensaje="entregado";
                    }else{
                        mensaje="bono";//le toca el bono
                        horario.get().setEstado("ENT");
                        horarioRepository.save(horario.get());
                        //obtener todas las preguntas
                        ArrayList<Pregunta> preguntas=preguntaRepository.findAllByEstado("ACT");
                        RespuestaEncuesta respEnc= new RespuestaEncuesta("PEN",horario.get().getBeneficiario(),horario.get());
                        respuestaEncuestaRepository.save(respEnc);
                        for(Pregunta pre: preguntas){
                            RespuestaIndividual respind= new RespuestaIndividual(respEnc,pre);
                            if(pre.getIdPregunta()==1 || pre.getIdPregunta()==2){
                                respind.setPuntaje(-1);
                            }else{
                                respind.setRespuesta("Sin respuesta");
                            }
                            respuestaIndividualRepository.save(respind);
                        }
                    }
                }
            }else {
                mensaje=mensajes(codigofamilia,horai,dia,idlugarentrega);
            }
        }
        return mensaje;
    }
    public String mensajes(String codigofamilia, LocalTime hora, LocalDate dia, Integer id){
        String mensaje="";
        List<Horario> hor=horarioRepository.findAllByBeneficiario(codigofamilia);
        Optional<Lugarentrega> lug= lugarEntregaRepository.findById(id);
        if(!hor.isEmpty()){
            Optional<Cronograma> cro= cronogramaRepository.findByEstado("PUB");
            //verificar si tiene dos horarios o no
            if(hor.size()==2){
                Integer idLugar=hor.get(0).getHorariolugarentrega().getLugarentrega().getIdLugarentrega();
                Integer idLugar2=hor.get(1).getHorariolugarentrega().getLugarentrega().getIdLugarentrega();
                if(idLugar!=id & idLugar2!=id){
                    mensaje = mensaje + "lugar";//No le corresponde este lugar de entrega
                    Beneficiario ben=hor.get(1).getBeneficiario();
                    Incidente inc=new Incidente(dia,"Lugar","ACT",ben,lug.get(),cro.get());
                    incidenteRepository.save(inc);
                    //insertar penalidad
                    Optional<Beneficiario> be=beneficiarioRepository.findByCodigofamilia(codigofamilia);
                    float pen=be.get().getPenalidad()+1;
                    be.get().setPenalidad(pen);
                    beneficiarioRepository.save(be.get());
                }
                if (!hor.get(0).getFecha().isEqual(dia) & !hor.get(1).getFecha().isEqual(dia)) {
                    mensaje = mensaje + " dia";//No le corresponde este día
                    Incidente inc=new Incidente(dia,"Día","ACT",hor.get(0).getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
                    //insertar penalidad
                    Optional<Beneficiario> be=beneficiarioRepository.findByCodigofamilia(codigofamilia);
                    float pen=be.get().getPenalidad()+1;
                    be.get().setPenalidad(pen);
                    beneficiarioRepository.save(be.get());
                }
                if ( (hor.get(0).getHoraInicio().isAfter(hora) || hor.get(0).getHoraFin().isBefore(hora)) &
                        (hor.get(1).getHoraInicio().isAfter(hora) || hor.get(1).getHoraFin().isBefore(hora))
                ) {
                    mensaje = mensaje + " horario";//No le corresponde en este horario
                    Incidente inc=new Incidente(dia,"Horario","ACT",hor.get(0).getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
                    //insertar penalidad
                    Optional<Beneficiario> be=beneficiarioRepository.findByCodigofamilia(codigofamilia);
                    float pen=be.get().getPenalidad()+1;
                    be.get().setPenalidad(pen);
                    beneficiarioRepository.save(be.get());
                }
            }else {
                Integer idLugar=hor.get(0).getHorariolugarentrega().getLugarentrega().getIdLugarentrega();
                if(idLugar!=id){
                    mensaje = mensaje + "lugar";//No le corresponde este lugar de entrega
                    Incidente inc=new Incidente(dia,"Lugar","ACT",hor.get(0).getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
                    //insertar penalidad
                    Optional<Beneficiario> be=beneficiarioRepository.findByCodigofamilia(codigofamilia);
                    float pen=be.get().getPenalidad()+1;
                    be.get().setPenalidad(pen);
                    beneficiarioRepository.save(be.get());
                }
                if (!hor.get(0).getFecha().isEqual(dia)) {
                    mensaje = mensaje + " dia";//No le corresponde este día
                    Incidente inc=new Incidente(dia,"Día","ACT",hor.get(0).getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
                    //insertar penalidad
                    Optional<Beneficiario> be=beneficiarioRepository.findByCodigofamilia(codigofamilia);
                    float pen=be.get().getPenalidad()+1;
                    be.get().setPenalidad(pen);
                    beneficiarioRepository.save(be.get());
                }
                if (hor.get(0).getHoraInicio().isAfter(hora) || hor.get(0).getHoraFin().isBefore(hora)) {
                    mensaje = mensaje + " horario";//No le corresponde en este horario
                    Incidente inc=new Incidente(dia,"Horario","ACT",hor.get(0).getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
                    //insertar penalidad
                    Optional<Beneficiario> be=beneficiarioRepository.findByCodigofamilia(codigofamilia);
                    float pen=be.get().getPenalidad()+1;
                    be.get().setPenalidad(pen);
                    beneficiarioRepository.save(be.get());
                }
            }
        }else{
            mensaje="no bono";//No le toca el bono
        }
        return mensaje;
    }

    @PostMapping("/hora")
    public LocalDate consultarCodigoFamilia() {
        LocalDate locaDate = LocalDate.now();
        return LocalDate.now();
    }
    public static class Datos {
        private static LocalDate fechaini;
        private static LocalDate fechafin;
        private static LocalDate fechaactual;
        private static Integer iddepartamento;
        private static Integer idprovincia;
        private static Integer iddistrito;
        private static List<Integer> cronogramas;
        public Datos() {}

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

        public LocalDate getFechaactual() {
            return fechaactual;
        }

        public void setFechaactual(LocalDate fechaactual) {
            Datos.fechaactual = fechaactual;
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

    @PostMapping("/reportebonos")
    public Hashtable<String,ArrayList<Object>> reporteavanceentrega(@RequestBody Datos data) {
        Hashtable<String,ArrayList<Object>> grafico =new Hashtable<>();
        ArrayList<Object> listanombres =new ArrayList<>();
        ArrayList<Object> listacantidades = new ArrayList<>();
        Integer cantnoentre = 0;
        Integer cantentre= 0;
        Integer cantpend= 0;
        ArrayList<Horario> hor,horpend =new ArrayList<>();

        if(data.getFechaactual().isBefore(data.getFechafin())) {
            hor = horarioRepository.findAllByFechaGreaterThanEqualAndFechaLessThanEqual(data.getFechaini(), data.getFechaactual());
            horpend = horarioRepository.findAllByFechaGreaterThanAndFechaLessThanEqual(data.getFechaactual(), data.getFechafin());
        }else {
            hor = horarioRepository.findAllByFechaGreaterThanEqualAndFechaLessThanEqual(data.getFechaini(), data.getFechafin());
        }
        for (Horario h:hor) {
            if(h.getEstado().equals("ENT")){
                cantentre=cantentre+1;
            }else if(h.getEstado().equals("NOE")){
                cantnoentre=cantnoentre+1;
            }
        }
        cantpend=horpend.size();

        listanombres.add("entregados");
        listanombres.add("noentregados");
        listanombres.add("pendientes");
        listacantidades.add(cantentre);
        listacantidades.add(cantnoentre);
        listacantidades.add(cantpend);
        grafico.put("listanombres",listanombres);
        grafico.put("listacantidades",listacantidades);
        return grafico;
    }
}
