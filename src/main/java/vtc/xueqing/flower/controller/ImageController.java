package vtc.xueqing.flower.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 图片资源控制器
 * 用于提供产品图片等静态资源访问
 */
@Api(tags = "图片资源接口")
@RestController
@RequestMapping("/images")
public class ImageController {

    @ApiOperation("获取产品图片")
    @GetMapping("/products/{imageName:.+}")
    public ResponseEntity<Resource> getProductImage(@PathVariable String imageName) throws IOException {
        String imagePath = "static/images/products/" + imageName;
        ClassPathResource resource = new ClassPathResource(imagePath);
        
        if (resource.exists()) {
            String contentType = getContentType(imageName);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    private String getContentType(String imageName) {
        if (imageName.endsWith(".svg")) {
            return "image/svg+xml";
        } else if (imageName.endsWith(".png")) {
            return "image/png";
        } else if (imageName.endsWith(".jpg") || imageName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else {
            return "application/octet-stream";
        }
    }
}