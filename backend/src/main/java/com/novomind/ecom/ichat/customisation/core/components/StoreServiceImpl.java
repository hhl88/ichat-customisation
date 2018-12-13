package com.novomind.ecom.ichat.customisation.core.components;

import com.novomind.ecom.ichat.customisation.core.interfaces.services.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

@Service("storeService")
public class StoreServiceImpl implements StoreService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final Path rootLocation = Paths.get("/tmp/storage");

    public String store(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        int dotSplit = originalFileName.lastIndexOf(".");
        String ext = originalFileName.substring(dotSplit, originalFileName.length() - dotSplit);
        String fileName = originalFileName.substring(0, dotSplit);
        Calendar calendar = Calendar.getInstance();
        String filePath = fileName + "_" + calendar.getTimeInMillis() + "." + ext;
        try {
            Files.copy(file.getInputStream(), rootLocation.resolve(filePath), StandardCopyOption.REPLACE_EXISTING);
            log.debug("Saved an img at " + rootLocation.resolve(filePath).toString());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Upload failed!");
        }

        return filePath;
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Load file failed " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Load file failed!");
        }
    }

    public void init() {
        log.info("INIT STORAGE SERVICE");
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    public void removeFile(String fileName) {
        try {
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                Files.delete(file);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Load file failed!");
        } catch (IOException e) {
            throw new RuntimeException("Not found");
        }
    }
}
