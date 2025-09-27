# flower-market-backend
flower-market-backend 花卉市场后端

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
import vtc.xueqing.flower.common.ResponseResult;
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
    public ResponseResult<{{=beanClass}}> queryById(@PathVariable {{=pkDataType}} id){
        return ResponseResult.ok({{=serviceVarName}}.getById(id));
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResponseResult<Page<{{=beanClass}}>> paginQuery({{=beanClass}} {{=beanVarName}}, 
                                                    @RequestParam(defaultValue = "1") Long current, 
                                                    @RequestParam(defaultValue = "10") Long size){
        return ResponseResult.ok({{=serviceVarName}}.page(getPage(current, size), new LambdaQueryWrapper<>({{=beanVarName}})));
    }
    
    @ApiOperation("新增/更新数据")
    @PostMapping
    public ResponseResult<Boolean> add(@RequestBody {{=beanClass}} {{=beanVarName}}){
        return ResponseResult.ok({{=serviceVarName}}.saveOrUpdate({{=beanVarName}}));
    }
    
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("{id}")
    public ResponseResult<Boolean> deleteById(@PathVariable {{=pkDataType}} id){
        return ResponseResult.ok({{=serviceVarName}}.removeById(id));
    }
}
~~~