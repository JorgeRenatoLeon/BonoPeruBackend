package com.bonoperubackend.BonoPeruBackend.Controladores;

import com.bonoperubackend.BonoPeruBackend.Modelos.*;
import com.bonoperubackend.BonoPeruBackend.Repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
        LocalTime horai=entr.getHora();
        LocalDate dia=entr.getDia();
        //LocalTime horaf=LocalTime.now();
        LocalTime horaf=entr.getHora();
        Optional<Horario> horario=horarioRepository.findhorarios(idlugarentrega,codigofamilia,horai,dia);
        if(horario.isPresent()){
            //existe un horario, tiene el bono
            if (horario.get().getHoraInicio().isAfter(horai) || horario.get().getHoraFin().isBefore(horaf)) {
                mensaje = "horario";//No le corresponde en este horario
                Optional<Lugarentrega> lug= lugarEntregaRepository.findById(idlugarentrega);
                Optional<Cronograma> cro= cronogramaRepository.findByEstado("PUB");
                Incidente inc=new Incidente(dia,"Horario","ACT",horario.get().getBeneficiario(),lug.get(),cro.get());
                incidenteRepository.save(inc);
            }else{
                mensaje="bono";//le toca el bono
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
        }else {
            mensaje=mensajes(codigofamilia,horai,dia,idlugarentrega);
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
                }
                if (!hor.get(0).getFecha().isEqual(dia) & !hor.get(1).getFecha().isEqual(dia)) {
                    mensaje = mensaje + " dia";//No le corresponde este día
                    Incidente inc=new Incidente(dia,"Día","ACT",hor.get(0).getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
                }
                if ( (hor.get(0).getHoraInicio().isAfter(hora) || hor.get(0).getHoraFin().isBefore(hora)) &
                        (hor.get(1).getHoraInicio().isAfter(hora) || hor.get(1).getHoraFin().isBefore(hora))
                ) {
                    mensaje = mensaje + " horario";//No le corresponde en este horario
                    Incidente inc=new Incidente(dia,"Horario","ACT",hor.get(0).getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
                }
            }else {
                Integer idLugar=hor.get(0).getHorariolugarentrega().getLugarentrega().getIdLugarentrega();
                if(idLugar!=id){
                    mensaje = mensaje + "lugar";//No le corresponde este lugar de entrega
                    Incidente inc=new Incidente(dia,"Lugar","ACT",hor.get(0).getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
                }
                if (!hor.get(0).getFecha().isEqual(dia)) {
                    mensaje = mensaje + " dia";//No le corresponde este día
                    Incidente inc=new Incidente(dia,"Día","ACT",hor.get(0).getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
                }
                if (hor.get(0).getHoraInicio().isAfter(hora) || hor.get(0).getHoraFin().isBefore(hora)) {
                    mensaje = mensaje + " horario";//No le corresponde en este horario
                    Incidente inc=new Incidente(dia,"Horario","ACT",hor.get(0).getBeneficiario(),lug.get(),cro.get());
                    incidenteRepository.save(inc);
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
}
