package vtc.xueqing.flower.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vtc.xueqing.flower.config.BaseController;

import java.io.File;

// @Api(tags = "图片查询接口")
@Slf4j
@RestController
@RequestMapping("/images")
public class ImageController extends BaseController {

    @ApiOperation("获取上传的图片")
    @GetMapping("/uploads/{filename}")
    public ResponseEntity<Resource> getUploadedImage(
            @ApiParam("文件名") @PathVariable String filename) {

        // 构建文件路径
        String uploadDir = "static/images/uploads/";
        String filePath = uploadDir + "/" + filename;

        log.info("获取上传的图片：{}", filePath);
        // 获取文件资源
        Resource resource = new FileSystemResource(new File(System.getProperty("user.dir") + "/src/main/resources/" + filePath));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}

