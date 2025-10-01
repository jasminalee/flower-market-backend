package vtc.xueqing.flower.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 图片资源控制器
 * 用于提供产品图片和上传图片等静态资源访问
 * 配合FileUploadController使用，提供上传图片的访问支持
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
    
    @ApiOperation("获取上传的图片")
    @GetMapping("/uploads/**")
    public ResponseEntity<Resource> getUploadedImage(javax.servlet.http.HttpServletRequest request) throws IOException {
        try {
            // 获取完整的请求路径
            String requestURI = request.getRequestURI();
            // 提取文件路径部分 (去除/images前缀)
            String imagePath = requestURI.substring("/images".length());
            
            // 构建文件的绝对路径
            String projectPath = System.getProperty("user.dir");
            Path fileLocation = Paths.get(projectPath, "src/main/resources", imagePath).normalize();
            Resource resource = new UrlResource(fileLocation.toUri());
            
            if (resource.exists()) {
                String contentType = getContentType(resource.getFilename());
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    private String getContentType(String imageName) {
        if (imageName == null) {
            return "application/octet-stream";
        }
        
        if (imageName.endsWith(".svg")) {
            return "image/svg+xml";
        } else if (imageName.endsWith(".png")) {
            return "image/png";
        } else if (imageName.endsWith(".jpg") || imageName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (imageName.endsWith(".gif")) {
            return "image/gif";
        } else {
            return "application/octet-stream";
        }
    }
}