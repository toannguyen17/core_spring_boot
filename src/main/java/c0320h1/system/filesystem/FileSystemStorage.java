package c0320h1.system.filesystem;

import c0320h1.system.properties.StorageProperties;
import c0320h1.system.filesystem.exception.FileNotFoundException;
import c0320h1.system.filesystem.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class FileSystemStorage implements Storage {
    private Path location;

    private StorageProperties properties;

    @Autowired
    public FileSystemStorage(StorageProperties properties) {
        this.properties = properties;
        this.location = Paths.get(this.properties.getLocation() + "/" + properties.getDisk());
    }

    /*
     * Lấy dường dẫn tệp trong thư mục gốc
     */
    public Path getPath(String filename) {
        return Paths.get(location.toString() + "/" + filename);
    }

    public Path getPath(String filename, String pathName, boolean create) {
        Path path = this.location;
        if (pathName != null) {
            path = Paths.get(path.toString() + "/" + pathName);
            if (create) {
                makeDirectory(path);
            }
        }
        path = Paths.get(path.toString() + "/" + filename);
        return path;
    }

    /*
     * Chọn thư mục gốc
     */
    @Override
    public Storage disk(String disk) {
        checkPath(disk);
        this.location = Paths.get(properties.getLocation() + "/" + disk);
        return this;
    }

    @Override
    public void putFile(String path, MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            checkPath(filename, path);
            try (InputStream inputStream = file.getInputStream()) {
                Path path_save = getPath(filename, path, true);
                Files.copy(inputStream, path_save, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public void putFile(String path, MultipartFile file, String name) {
        String filename = StringUtils.cleanPath(name);
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            checkPath(filename, path);
            try (InputStream inputStream = file.getInputStream()) {
                Path path_save = getPath(filename, path, true);
                Files.copy(inputStream, path_save, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Resource get(String filename) {
        checkPath(filename);
        try {
            Path file = getPath(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isFile() && resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void delete(String file) {
        checkPath(file);
        Path path = getPath(file);
        FileSystemUtils.deleteRecursively(path.toFile());
    }

    @Override
    public boolean exists(String filename) {
        checkPath(filename);
        Path file = getPath(filename);
        Resource resource = null;
        try {
            resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void copyAndDelete(String old_file, String new_file, boolean delete) {
        checkPath(old_file, new_file);

        Path src  = getPath(old_file);
        Path dect = getPath(new_file);
        try {
            FileSystemUtils.copyRecursively(src.toFile(), dect.toFile());
            if (delete){
                delete(old_file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void copy(String old_file, String new_file) {
        copyAndDelete(old_file, new_file, false);
    }

    @Override
    public void move(String old_file, String new_file) {
        copyAndDelete(old_file, new_file, true);
    }

    @Override
    public void makeDirectory(String dir) {
        checkPath(dir);
        Path path = getPath(dir);
        makeDirectory(path);
    }

    public void makeDirectory(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public void deleteDirectory(String dir) {
        checkPath(dir);
        delete(dir);
    }

    @Override
    public ResponseEntity<Resource> download(String file) {
        checkPath(file);
        Resource resource = get(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Override
    public ResponseEntity<Resource> download(String file, String name, HttpHeaders headers) {
        checkPath(file, name);
        Resource resource = get(file);
        return ResponseEntity.ok()
                .headers(headers)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + name + "\"")
                .body(resource);
    }

    public void checkPath(String... path){
        for (String p: path){
            if (p.contains("..")) {
                throw new StorageException("The path is not valid: " + p);
            }
        }
    }
}
