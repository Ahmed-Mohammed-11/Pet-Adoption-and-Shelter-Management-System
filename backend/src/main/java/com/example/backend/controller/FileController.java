package com.example.backend.controller;

import com.example.backend.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/test/file")
public class FileController {

    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload
            (@RequestParam("file") MultipartFile[] files) throws IOException {

        fileService.upload(files);
        return ResponseEntity.ok("Files uploaded successfully");
    }

    @GetMapping("/files")
    public ResponseEntity<List<String>> getAllBlobs() {
        List<String> items = fileService.listBlobs();
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Boolean> delete
            (@PathVariable("name") String name) {

        fileService.deleteBlob(name);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/download/{name}")
    public ResponseEntity<Resource> getFile (@PathVariable("name") String name) {

        ByteArrayResource resource =
                new ByteArrayResource(fileService
                        .getFile(name));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" +
                        name + "\"");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .headers(headers).body(resource);
    }
}
