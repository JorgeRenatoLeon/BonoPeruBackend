package com.bonoperubackend.BonoPeruBackend.Controladores;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class HomeController {
    @GetMapping({"/hello"})
    public String helloWorld(@RequestParam(required = false, defaultValue = "amigo") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-world";
    }

    @RestController
    public class FileUploadController {
        @RequestMapping(value = "/upload", method = RequestMethod.POST,
                consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

        public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
            File convertFile = new File(file.getOriginalFilename());
            convertFile.createNewFile();
            FileOutputStream fout = new FileOutputStream(convertFile);
            fout.write(file.getBytes());
            fout.close();
            return "File is upload successfully";
        }
    }

    @GetMapping(value = "/.well-known/pki-validation/55C94ABE6EEBAC4BFAF57F64BF05E3D7.txt")
    public ResponseEntity<Object> contact() throws IOException {

        String filename = "55C94ABE6EEBAC4BFAF57F64BF05E3D7.txt";
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object>
                responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
                MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }
}