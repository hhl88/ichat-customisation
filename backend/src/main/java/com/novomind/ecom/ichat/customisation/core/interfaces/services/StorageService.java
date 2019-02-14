package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;

public interface StorageService {

    void init();

    String store(MultipartFile file) throws IOException;

    Resource loadFile(String filename) throws IOException, RuntimeException;

    void removeFile(String fileName) throws IOException;
}
