package com.bonoperubackend.BonoPeruBackend.Modelos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.bonoperubackend.BonoPeruBackend.Repositorios.BeneficiarioRepository;
import com.bonoperubackend.BonoPeruBackend.Repositorios.DistritoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
    public static String TYPE = "text/csv";
    public static String TYPE2 = "application/vnd.ms-excel";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    @Autowired
    DistritoRepository distritoRepository;

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType()) && !TYPE2.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

}
