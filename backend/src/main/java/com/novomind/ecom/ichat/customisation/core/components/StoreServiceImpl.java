package com.novomind.ecom.ichat.customisation.core.components;

import com.novomind.ecom.ichat.customisation.core.interfaces.services.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
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


    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't initialize storage folder");
        }
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
