package com.example.xyshw.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class ImageUploadController {

    @PostMapping("/imgUpload")
    public String ImageUpload(@RequestParam("a") MultipartFile file) throws IOException {
        File f = new File("./src/main/resources/a.png");
        FileOutputStream fileOutputStream = new FileOutputStream(f);
        fileOutputStream.write(file.getBytes());
        return "ok";
    }



}
