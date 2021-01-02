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
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/beneficiario")
public class BeneficiarioController {

    @Autowired
    BeneficiarioRepository beneficiarioRepository;
    @Autowired
    BeneficiarioAlgoritmoRepository beneficiarioAlgoritmoRepository;
    @Autowired
    LugarEntregaAlgoritmoRepository lugarEntregaAlgoritmoRepository;
    @Autowired
    HorarioRepository horarioRepository;
    @Autowired
    LugarEntregaRepository lugarEntregaRepository;
    @Autowired
    DistritoRepository distritoRepository;
    @Autowired
    ProvinciaRepository provinciaRepository;
    @Autowired
    DepartamentoRepository departamentoRepository;

    @PostMapping("/listar")
    public List<Beneficiario> listarBeneficiarios() {
        //Solo lista beneficiarios activos
        List<Beneficiario> ben;
        ben = beneficiarioRepository.findBeneficiariosByEstado("ACT");
        return ben;
    }
    //EL beneficiario consulta su cod de familia para saber su horario
    @PostMapping("/consultarHorario/{cod}")
    public List<Horario> buscarXCodFam(@PathVariable String cod) {

        List <Horario> horario=horarioRepository.findHorariosByBeneficiario_CodigofamiliaAndCronograma_Estado(cod, "PUB");
        //Devuelve lista vacía si el beneficiario no tiene un bono actual para recoger
        return horario;

    }
    //información del beneficiario
    @PostMapping("/infobeneficiario/{id}")
    public Hashtable<String, Object> infoBeneficiario(@PathVariable Integer id) {
        Hashtable<String, Object> respuesta=new Hashtable<>();
        String discapacitado="";
        String sexo="";
        Optional<Beneficiario> bene=beneficiarioRepository.findById(id);
        Optional<Distrito> dis=distritoRepository.findById(bene.get().getFiddistrito());
        Optional<Provincia> prov=provinciaRepository.findById(dis.get().getFidprovincia());
        Optional<Departamento> dep=departamentoRepository.findById(prov.get().getFiddepartamento());
        if(bene.get().getFemenino()){//es mujer
            sexo="F";
        }else{
            sexo="M";
        }
        if(bene.get().getEsdiscapacitado()){//es discapacitado
            discapacitado="Si";
        }else{
            discapacitado="No";
        }
        respuesta.put("departamento",dep.get().getNombre());
        respuesta.put("provincia",prov.get().getNombre());
        respuesta.put("distrito",dis.get().getNombre());
        respuesta.put("sexo",sexo);
        respuesta.put("discapacitado",discapacitado);
        return respuesta;
    }


    @PostMapping("/prueba")
    public List<LugarEntregaAlgoritmo> prueba (){
        List<LugarEntregaAlgoritmo> ejemploLista;
        ejemploLista=lugarEntregaAlgoritmoRepository.findAllByLugarEntregaAlgoritmo();
        return ejemploLista;
    }

    @PostMapping("/prueba2")
    public List<BeneficiarioAlgoritmo> prueba2 (){
        List<BeneficiarioAlgoritmo> ejemploLista;
        ejemploLista=beneficiarioAlgoritmoRepository.findAllByBeneficiarioAlgoritmo();
        return ejemploLista;
    }

    public class ResponseMessage {
        private String message;
        private List<Beneficiario> beneficiarios;
        List<ArrayList<String>> errores;

        public ResponseMessage(String message, List<Beneficiario> beneficiarios, List<ArrayList<String>> errores) {
            this.message = message;
            this.beneficiarios = beneficiarios;
            this.errores = errores;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Beneficiario> getBeneficiarios() {
            return beneficiarios;
        }

        public void setBeneficiarios(List<Beneficiario> beneficiarios) {
            this.beneficiarios = beneficiarios;
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

            List<Beneficiario> beneficiarios = new ArrayList<>();
            List<ArrayList<String>> errores = new ArrayList<>();

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
                    array.add("Error en el Ubigeo");
                }

                String codigo = csvRecord.get("CO_HOGAR");
                if(codigo==null || codigo.equals("")){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el Codigo de Hogar");
                }

                boolean masculino=false;
                try {
                    int mas = Integer.parseInt(csvRecord.get("DE_GENERO"));
                    masculino = mas==1;
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el Sexo");
                }

                boolean femenino=false;
                try {
                    int fem = Integer.parseInt(csvRecord.get("DE_GENERO"));
                    femenino = fem==2;
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    if (array.get(array.size()-1)!="Error en el Sexo") array.add("Error en el Sexo");
                }

                boolean discapacidad=false;
                try {
                    int dis = Integer.parseInt(csvRecord.get("FLAG_DISCAP_SEVERA"));
                    discapacidad = dis==1;
                }
                catch (Exception exception){
                    if(array.size()==0) array.add(Integer.toString(row));
                    array.add("Error en el campo de Discapacidad");
                }

                Optional<Departamento> departamentoAux = departamentoRepository.findByUbigeo(ubigeo/10000);
                if(departamentoAux.isPresent()){
                    Optional<Provincia> provinciaAux = provinciaRepository.findByUbigeoAndFiddepartamento((ubigeo/100)%100,departamentoAux.get().getIddepartamento());
                    if(provinciaAux.isPresent()){
                        Optional<Distrito> distritoAux = distritoRepository.findByUbigeoAndFidprovincia(ubigeo%100,provinciaAux.get().getIdprovincia());
                        if (distritoAux.isPresent()) {
                            Integer codigoDistrito = distritoAux.get().getIddistrito();

                            if(array.size()==0) {
                                Optional<Beneficiario> beneficiarioExistente = beneficiarioRepository.findByCodigofamilia(codigo);
                                if(beneficiarioExistente.isPresent()){
                                    beneficiarioExistente.get().setFiddistrito(codigoDistrito);
                                    beneficiarioExistente.get().setEsdiscapacitado(discapacidad);
                                    beneficiarioExistente.get().setMasculino(masculino);
                                    beneficiarioExistente.get().setFemenino(femenino);
                                    beneficiarioExistente.get().setUsuarioactualizacion(idCreacion);
                                    beneficiarioExistente.get().setEstado("ACT");
                                    beneficiarios.add(beneficiarioExistente.get());
                                }
                                else{
                                    Beneficiario beneficiario = new Beneficiario(
                                            codigoDistrito,
                                            csvRecord.get("CO_HOGAR"),
                                            femenino,
                                            masculino,
                                            discapacidad,
                                            0,
                                            0,
                                            "ACT",
                                            idCreacion
                                    );
                                    beneficiarios.add(beneficiario);
                                }
                            }
                            else{
                                errores.add(array);
                            }
                        }
                        else{
                            if(array.size()==0) array.add(Integer.toString(row));
                            if (array.size()==1 || !array.get(1).equals("Error en el Ubigeo")) array.add("Error en el Ubigeo: Codigo Incorrecto");
                            errores.add(array);
                        }
                    }
                    else{
                        if(array.size()==0) array.add(Integer.toString(row));
                        if (array.size()==1 || !array.get(1).equals("Error en el Ubigeo")) array.add("Error en el Ubigeo: Codigo Incorrecto");
                        errores.add(array);
                    }
                }
                else{
                    if(array.size()==0) array.add(Integer.toString(row));
                    if (array.size()==1 || !array.get(1).equals("Error en el Ubigeo")) array.add("Error en el Ubigeo: Codigo Incorrecto");
                    errores.add(array);
                }
                row = row+1;
            }
            beneficiarioRepository.saveAll(beneficiarios);
            List<Object> respuesta = new ArrayList<>();
            respuesta.add(beneficiarios);
            respuesta.add(errores);
            return respuesta;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    @PostMapping("/carga/{idCreacion}")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable Integer idCreacion, @RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                List<Object> respuesta = save(file,idCreacion);

                message = "Beneficiarios Cargados exitosamente: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,(List<Beneficiario>) respuesta.get(0),(List<ArrayList<String>>) respuesta.get(1)));
            } catch (Exception e) {
                message = "No se pudieron cargar los Beneficiarios: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,null,null));
            }
        }

        message = "No es un archivo csv";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,null,null));
    }



}
