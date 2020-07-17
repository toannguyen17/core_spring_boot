package c0320h1.system.filesystem;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface Storage {
    Storage disk(String disk);

    void putFile(String path, MultipartFile file);

    void putFile(String path, MultipartFile file, String name);

    Resource get(String filename);

    void delete(String file);

    boolean exists(String file);

    void copy(String src, String dest);

    void move(String old_file, String new_file);

    void makeDirectory(String dir);

    void deleteDirectory(String dir);

    ResponseEntity<Resource> download(String file);
    ResponseEntity<Resource> download(String file, String name, HttpHeaders headers);
}
