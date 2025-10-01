package vtc.xueqing.flower.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vtc.xueqing.flower.common.ResponseResult;
import vtc.xueqing.flower.config.BaseController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传控制器
 * 用于处理富文本编辑器中的图片上传
 * 上传后的图片可通过 /images/uploads/{datePath}/{filename} 访问
 */
@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/api/upload")
public class FileUploadController extends BaseController {
    
    // 图片保存目录（相对于resources目录）
    private static final String UPLOAD_DIR = "static/images/uploads/";
    
    @ApiOperation("上传图片")
    @PostMapping("/image")
    public ResponseResult<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 检查文件是否为空
            if (file.isEmpty()) {
                return fail("上传文件不能为空");
            }
            
            // 检查文件类型
            String contentType = file.getContentType();
            if (contentType == null || (!contentType.startsWith("image/"))) {
                return fail("只支持图片格式文件");
            }
            
            // 检查文件大小（限制为5MB）
            if (file.getSize() > 5 * 1024 * 1024) {
                return fail("图片大小不能超过5MB");
            }
            
            // 获取项目根路径
            String projectPath = System.getProperty("user.dir");
            String uploadPath = projectPath + "/src/main/resources/" + UPLOAD_DIR;
            
            // 按日期创建子目录
            String datePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            String finalUploadPath = uploadPath + datePath + "/";
            
            // 创建目录
            File uploadDir = new File(finalUploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newFilename = UUID.randomUUID().toString().replace("-", "") + fileExtension;
            
            // 保存文件
            File dest = new File(finalUploadPath + newFilename);
            file.transferTo(dest);
            
            // 返回图片访问URL (相对路径)
            // 该URL可直接通过ImageController的getUploadedImage接口访问
            String imageUrl = "/images/uploads/" + datePath + "/" + newFilename;
            return success("上传成功", imageUrl);
            
        } catch (Exception e) {
            return fail("上传失败：" + e.getMessage());
        }
    }
}