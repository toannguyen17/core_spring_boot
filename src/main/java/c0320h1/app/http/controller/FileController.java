package c0320h1.app.http.controller;

import c0320h1.app.middleware.Guestable;
import c0320h1.system.filesystem.Storage;
import c0320h1.system.http.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.interceptor.Interceptors;

@Controller
@Interceptors(Guestable.class)
public class FileController {

    @Autowired
    private Storage storageService;

    @GetMapping("/")
    public String listAllFiles() {
        return "listFiles";
    }

    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        return storageService.download(filename);
    }

    @PostMapping("/upload-file")
    @ResponseBody
    public String uploadFile(@RequestParam("file") UploadedFile file) {

        file.store("images/users/avatar");

        String name = file.getFile().getOriginalFilename();

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();

        System.out.println(uri);

        return "ok single upload";
    }
}
