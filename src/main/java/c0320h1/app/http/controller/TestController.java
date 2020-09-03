package c0320h1.app.http.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/{fileName:[0-9A-z]+}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> get_File(@PathVariable String fileName, HttpServletResponse response) throws IOException {

        URL url = new URL("https://mp3-s1-zmp3.zadn.vn/a370fddc919b78c5218a/801670368018937177?authen=exp=1598925444~acl=/a370fddc919b78c5218a/*~hmac=498fbe0250f078295b51f826039fcd5c");
        URLConnection conn = url.openConnection();

        String filename = "Test.mp3";

        long len = conn.getContentLength();

//        MediaType mediaType = MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file.getFile()));

//        if (filename.toLowerCase().endsWith("mp4") || filename.toLowerCase().endsWith("mp3") ||
//                filename.toLowerCase().endsWith("3gp") || filename.toLowerCase().endsWith("mpeg") ||
//                filename.toLowerCase().endsWith("mpeg4"))
//        MediaType mediaType = MediaType.parseMediaType("application/octet-stream");

        MediaType mediaType = MediaType.parseMediaType(conn.getContentType());

        InputStreamResource resource = new InputStreamResource(conn.getInputStream());

        HttpHeaders headers = new HttpHeaders();

        Map<String, List<String>> map = conn.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey() != null){
                String result = String.join(", ", entry.getValue());

                Map values = new HashMap();
                values.put(entry.getKey(), result);
                headers.add(entry.getKey(), result);
                headers.setAll(values);
            }
        }
//        response.setHeader("Keep-Alive", "timeout=300");
//        headers.add("Keep-Alive", "timeout=300");

        return ResponseEntity.ok()
                .contentType(mediaType)
                .contentLength(len)
                .headers(headers)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }
}
