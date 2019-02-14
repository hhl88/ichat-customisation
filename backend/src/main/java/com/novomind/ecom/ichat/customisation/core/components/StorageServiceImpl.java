package com.novomind.ecom.ichat.customisation.core.components;

import com.novomind.ecom.ichat.customisation.core.interfaces.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;

@Service
public class StorageServiceImpl implements StorageService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String ROOT_PATH = "/tmp/storage";
    private File dir = new File(ROOT_PATH);


    private final Path rootLocation = Paths.get(dir.getAbsolutePath());



    @PostConstruct
    public void init() {
//        try {
            log.info("Try init storage");
            if(!Files.exists(rootLocation)) {
                log.info("Target file \"" + ROOT_PATH + "\" will be created.");;
                try {
                    Files.createDirectories(Paths.get(dir.getAbsolutePath()));
                    log.info("exists 1: " + Files.exists(rootLocation));
                    log.info("exists 2: " + dir.exists() + " --- " + dir.getAbsolutePath());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            /*if (!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }*/
       /* } catch (IOException e) {
            throw new RuntimeException("Couldn't initialize storage folder");
        }*/
    }

    @Override
    public String store(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        int dotSplit = originalFileName.lastIndexOf(".");
        String ext = originalFileName.substring(dotSplit + 1);
        String fileName = originalFileName.substring(0, dotSplit);
        Calendar calendar = Calendar.getInstance();
        String filePath = fileName + "_" + calendar.getTimeInMillis() + "." + ext;
        Files.copy(file.getInputStream(), rootLocation.resolve(filePath).normalize(), StandardCopyOption.REPLACE_EXISTING);
        log.debug("Saved an img at " + rootLocation.resolve(filePath).toString());
        return filePath;

    }

    @Override
    public Resource loadFile(String filename) throws IOException, RuntimeException {

        Path file = rootLocation.resolve(filename).normalize();
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Load file failed " + filename);
        }

    }


    public void removeFile(String fileName) throws IOException {
        Path file = rootLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(file.toUri());
        if (resource.exists() || resource.isReadable()) {
            Files.delete(file);
        }

    }
}
