# flower-market-backend
flower-market-backend 花卉市场后端


`create database flower_market;`

~~~ sql
-- 用户表：存储用户基本信息
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名(登陆)',
  `password` varchar(100) NOT NULL COMMENT '密码(加密存储)',
  `nickname` varchar(50) NOT NULL comment '昵称',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `addr` varchar(255) NOT NULL COMMENT '地址',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态（0-禁用，1-正常）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';


-- 角色表：存储角色信息
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(50) NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';

-- 权限表：存储权限信息
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` bigint(20) DEFAULT 0 COMMENT '父权限ID（用于构建权限树）',
  `permission_name` varchar(100) NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) NOT NULL COMMENT '权限编码',
  `permission_type` tinyint(4) NOT NULL COMMENT '权限类型（1-菜单，2-按钮，3-接口）',
  `url` varchar(255) DEFAULT NULL COMMENT '资源路径（如URL）',
  `method` varchar(10) DEFAULT NULL COMMENT '请求方法（GET/POST等，适用于接口权限）',
  `sort` int(11) DEFAULT 0 COMMENT '排序号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_permission_code` (`permission_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统权限表';

-- 用户角色关联表：用户与角色的多对多关系
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_role` (`user_id`,`role_id`),
  KEY `idx_role_id` (`role_id`),
  CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 角色权限关联表：角色与权限的多对多关系
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NOT NULL COMMENT '权限ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_permission` (`role_id`,`permission_id`),
  KEY `idx_permission_id` (`permission_id`),
  CONSTRAINT `fk_role_permission_role_id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_role_permission_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

~~~
## 测试数据
~~~
-- 插入系统用户测试数据
INSERT INTO `sys_user` (
  `username`, `password`, `nickname`, `email`, `phone`, `addr`, `status`
) VALUES
-- 管理员用户 (密码通常为加密存储，这里用明文示例)
('admin', 'admin123', '系统管理员', 'admin@example.com', '13800138000', '北京市海淀区', 1),
-- 普通用户1
('user01', 'user01pwd', '张三', 'zhangsan@example.com', '13900139001', '上海市浦东新区', 1),
-- 普通用户2
('user02', 'user02pwd', '李四', 'lisi@example.com', '13700137002', '广州市天河区', 1),
-- 禁用用户
('user03', 'user03pwd', '王五', 'wangwu@example.com', '13600136003', '深圳市南山区', 0),
-- 测试用户
('test01', 'test01pwd', '赵六', 'zhaoliu@example.com', '13500135004', '杭州市西湖区', 1);
    
~~~

### dot.js模板文件
entity
~~~
{{ 
    var today = new Date();
    var fullYear = today.getFullYear();
    var month = today.getMonth() + 1;
    var days = today.getDate();
    
    var pkVarName = "undefinedId";
    var pkDataType = "String";
    it.entity.fields.forEach(function(field){
        if(field.primaryKey){
            pkVarName = it.func.camel(field.defKey, false);
            pkDataType = field["type"];
            return;
        }
    });
    
    var pkgName = it.entity.env.base.nameSpace;
    var beanClass = it.entity.env.base.codeRoot;
    var beanVarName = beanClass.charAt(0).toLowerCase() + beanClass.slice(1);
    var serviceClass = beanClass + 'Service';
    var serviceVarName = beanVarName + 'Service';
}}
package {{=pkgName}}.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
{{? it.entity.fields.some(field => field.type.includes('Date')) }}
import java.util.Date;
{{?}}
{{? it.entity.fields.some(field => field.primaryKey) }}
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
{{?}}
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * {{=it.func.join(it.entity.defName, it.entity.comment, '-')}}
 * @author : xueqing li
 * @date : {{=fullYear}}-{{=month}}-{{=days}}
 */
@ApiModel("{{=it.func.join(it.entity.defName, it.entity.comment, '-')}}")
@TableName("{{=it.entity.defKey}}")
@Data
public class {{=it.func.camel(it.entity.defKey, true)}} implements Serializable, Cloneable {
    
    private static final long serialVersionUID = 1L;
    
{{~it.entity.fields:field:index}}
    @ApiModelProperty("{{=it.func.join(field.defName, field.comment, ';')}}")
    {{? field.primaryKey }}
    @TableId(type = IdType.AUTO)
    {{?}}
    private {{=field.type}} {{=it.func.camel(field.defKey, false)}};
{{~}}

}
~~~

service
~~~
{{
    var pkgName = it.entity.env.base.nameSpace;
    var beanClass = it.func.camel(it.entity.defKey, true);
    var tableComment = it.func.join(it.entity.defName, it.entity.comment, '-');
}}
package {{=pkgName}}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import {{=pkgName}}.entity.{{=beanClass}};

/**
 * {{=tableComment}};({{=it.entity.defKey}})表服务接口
 * @author : Xueqing
 */
public interface {{=beanClass}}Service extends IService<{{=beanClass}}> {}
~~~
serviceImpl
~~~
{{
    var pkgName = it.entity.env.base.nameSpace;
    var beanClass = it.func.camel(it.entity.defKey, true);
    var tableComment = it.func.join(it.entity.defName, it.entity.comment, '-');
}}
package {{=pkgName}}.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import {{=pkgName}}.entity.{{=beanClass}};
import {{=pkgName}}.mapper.{{=beanClass}}Mapper;
import {{=pkgName}}.service.{{=beanClass}}Service;

/**
 * {{=tableComment}};({{=it.entity.defKey}})表服务实现类
 *
 * @author : Xueqing
 */
@Service
public class {{=beanClass}}ServiceImpl extends ServiceImpl<{{=beanClass}}Mapper, {{=beanClass}}> implements {{=beanClass}}Service {}
~~~
mapper
~~~
{{
    var pkgName = it.entity.env.base.nameSpace;
    var entityClass = it.func.camel(it.entity.defKey, true);
    var tableComment = it.func.join(it.entity.defName, it.entity.comment, '-');
}}
package {{=pkgName}}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import {{=pkgName}}.entity.{{=entityClass}};

/**
 * {{=tableComment}};({{=it.entity.defKey}})表数据库访问层
 *
 * @author : Xueqing
 */
@Mapper
public interface {{=entityClass}}Mapper extends BaseMapper<{{=entityClass}}> {}
~~~

controller
~~~
{{
    var pkgName = it.entity.env.base.nameSpace;
    var beanClass = it.func.camel(it.entity.defKey, true);
    var beanVarName = beanClass.charAt(0).toLowerCase() + beanClass.slice(1);
    var serviceClass = beanClass + 'Service';
    var serviceVarName = beanVarName + 'Service';
    var tableComment = it.func.join(it.entity.defName, it.entity.comment, '-');
    
    var pkVarName = "id";
    var pkDataType = "Long";
    it.entity.fields.forEach(function(field){
        if(field.primaryKey){
            pkVarName = it.func.camel(field.defKey, false);
            pkDataType = field["type"];
            return;
        }
    });
}}
package {{=pkgName}}.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import {{=pkgName}}.entity.{{=beanClass}};
import {{=pkgName}}.service.{{=serviceClass}};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * {{=tableComment}};({{=it.entity.defKey}})表控制层
 * @author : Xueqing
 */
@Api(tags = "{{=tableComment}}对象功能接口")
@RestController
@RequestMapping("/{{=it.func.camel(it.entity.defKey, false)}}")
public class {{=beanClass}}Controller extends BaseController {
    @Autowired
    private {{=serviceClass}} {{=serviceVarName}};

    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    public ResponseEntity<{{=beanClass}}> queryById(@PathVariable {{=pkDataType}} id){
        return ResponseEntity.ok({{=serviceVarName}}.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseEntity<Page<{{=beanClass}}>> paginQuery({{=beanClass}} {{=beanVarName}}, 
                                                    @RequestParam(defaultValue = "1") Long current, 
                                                    @RequestParam(defaultValue = "10") Long size){
        return ResponseEntity.ok({{=serviceVarName}}.page(getPage(current, size), new LambdaQueryWrapper<>({{=beanVarName}})));
    }
    
    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody {{=beanClass}} {{=beanVarName}}){
        return ResponseEntity.ok({{=serviceVarName}}.saveOrUpdate({{=beanVarName}}));
    }
    
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable {{=pkDataType}} id){
        return ResponseEntity.ok({{=serviceVarName}}.removeById(id));
    }
}
~~~