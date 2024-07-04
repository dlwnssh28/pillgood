package com.pillgood.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/admin/upload")
public class FileUploadController {

    // 실제 업로드 경로 설정 (예: 프로젝트 루트 디렉토리의 uploads 폴더)
    private final String uploadDir = System.getProperty("user.dir") + "/uploads";

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 업로드 폴더가 존재하지 않으면 생성
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일명 생성 및 저장 경로 설정
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);

            // 파일 URL 설정
            String fileUrl = "/uploads/" + filename;
            return new ResponseEntity<>(fileUrl, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Image upload failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
