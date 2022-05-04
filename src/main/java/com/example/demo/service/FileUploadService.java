package com.example.demo.service;

import com.example.demo.vo.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    Result uploadFile(MultipartFile file) throws IOException;
}
