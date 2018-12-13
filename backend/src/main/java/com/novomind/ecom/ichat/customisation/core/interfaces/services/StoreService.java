package com.novomind.ecom.ichat.customisation.core.interfaces.services;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

public interface StoreService {

    void init();

    String store(MultipartFile file);

    Resource loadFile(String filename);

    void removeFile(String fileName);
}
