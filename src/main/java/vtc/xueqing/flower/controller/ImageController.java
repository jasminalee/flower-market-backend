package vtc.xueqing.flower.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    // private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    //
    // @ApiOperation("获取产品图片")
    // @GetMapping("/products/{imageName:.+}")
    // public ResponseEntity<Resource> getProductImage(@PathVariable String imageName) throws IOException {
    //     String imagePath = "static/images/products/" + imageName;
    //     ClassPathResource resource = new ClassPathResource(imagePath);
    //
    //     if (resource.exists()) {
    //         String contentType = getContentType(imageName);
    //         logger.info("产品图片访问成功，图片名称: {}, 路径: {}", imageName, imagePath);
    //         return ResponseEntity.ok()
    //                 .contentType(MediaType.parseMediaType(contentType))
    //                 .body(resource);
    //     } else {
    //         logger.warn("产品图片不存在，图片名称: {}, 路径: {}", imageName, imagePath);
    //         return ResponseEntity.notFound().build();
    //     }
    // }
    //
    // @ApiOperation("获取上传的图片")
    // @GetMapping("/uploads/**")
    // public ResponseEntity<Resource> getUploadedImage(javax.servlet.http.HttpServletRequest request) throws IOException {
    //     try {
    //         // 获取完整的请求路径
    //         String requestURI = request.getRequestURI();
    //         // 提取文件路径部分 (去除/images前缀)
    //         String imagePath = requestURI.substring("/images".length());
    //
    //         // 构建文件的绝对路径，保持与上传逻辑一致
    //         String projectPath = System.getProperty("user.dir");
    //         // 修复路径，添加缺失的 static/images 部分
    //         Path fileLocation = Paths.get(projectPath, "src/main/resources/static", imagePath).normalize();
    //         Resource resource = new UrlResource(fileLocation.toUri());
    //
    //         logger.info("尝试访问上传图片，请求URI: {}, 文件路径: {}", requestURI, fileLocation.toString());
    //
    //         if (resource.exists()) {
    //             String contentType = getContentType(resource.getFilename());
    //             logger.info("上传图片访问成功，请求URI: {}, 文件路径: {}", requestURI, fileLocation.toString());
    //             return ResponseEntity.ok()
    //                     .contentType(MediaType.parseMediaType(contentType))
    //                     .body(resource);
    //         } else {
    //             logger.warn("上传图片不存在，请求URI: {}, 文件路径: {}", requestURI, fileLocation.toString());
    //             return ResponseEntity.notFound().build();
    //         }
    //     } catch (Exception e) {
    //         logger.error("访问上传图片时发生异常，请求URI: {}", request.getRequestURI(), e);
    //         return ResponseEntity.notFound().build();
    //     }
    // }
    //
    // private String getContentType(String imageName) {
    //     if (imageName == null) {
    //         return "application/octet-stream";
    //     }
    //
    //     if (imageName.endsWith(".svg")) {
    //         return "image/svg+xml";
    //     } else if (imageName.endsWith(".png")) {
    //         return "image/png";
    //     } else if (imageName.endsWith(".jpg") || imageName.endsWith(".jpeg")) {
    //         return "image/jpeg";
    //     } else if (imageName.endsWith(".gif")) {
    //         return "image/gif";
    //     } else {
    //         return "application/octet-stream";
    //     }
    // }
}