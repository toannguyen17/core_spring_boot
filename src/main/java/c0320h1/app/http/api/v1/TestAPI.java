package c0320h1.app.http.api.v1;

import c0320h1.model.Users;
import c0320h1.system.http.UploadedFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestAPI {
    @GetMapping("/api/a")
    public ResponseEntity<String> test(){
        String str = "abc";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @PostMapping("/api/a")
    public ResponseEntity<String> test2(MultipartFile file){
        System.out.println(file);
        return new ResponseEntity<>("file.getOriginalFilename()", HttpStatus.CREATED);
    }
}
