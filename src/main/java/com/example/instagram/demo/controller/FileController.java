package com.example.instagram.demo.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/uploads")
public class FileController {

    private static final String UPLOAD_DIR = "uploads";
    private final Path uploadPath = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        // Load file as Resource
        Path filePath = uploadPath.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        // Check if file exists
        if (!resource.exists()) {
            throw new RuntimeException("File not found: " + fileName);
        }

        // Set content disposition header for file download
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .headers(headers)
                .body(resource.getContentAsByteArray());
    }
}