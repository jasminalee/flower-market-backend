# 花卉市场后端接口文档

## 1. 概述

本文档提供了花卉市场项目后端API接口的详细说明，方便前端开发人员进行接口联调。

### 1.1 响应格式

所有接口返回统一的响应格式：

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {},
  "timestamp": 1640995200000
}
```

- `code`: 响应状态码
  - 200: 成功
  - 400: 请求参数错误
  - 401: 未认证
  - 403: 未授权
  - 404: 资源不存在
  - 500: 服务器内部错误
- `message`: 响应信息
- `data`: 响应数据
- `timestamp`: 时间戳

### 1.2 图片资源说明

产品图片等静态资源可以通过以下方式访问：
- 直接通过路径访问：`http://localhost:18091/images/products/{图片名称}.svg`
- 例如：`http://localhost:18091/images/products/rose.svg`

## 2. 产品相关接口

### 2.1 获取主页商品列表

获取主页展示的商品列表，支持分类筛选和关键字搜索。

**请求URL**: `GET /product/homepage`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| categoryId | Long | 否 | 分类ID |
| keyword | String | 否 | 搜索关键字 |
| current | Long | 否 | 页码，默认1 |
| size | Long | 否 | 每页数量，默认10 |

**响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "records": [
      {
        "id": 1,
        "productName": "红玫瑰",
        "productCode": "FLOWER-001",
        "categoryId": 4,
        "brand": "花语",
        "description": "鲜艳的红玫瑰，适合表达爱意",
        "mainImage": "/images/products/rose.svg",
        "subImages": null,
        "detail": null,
        "productType": 1,
        "status": 1,
        "createTime": "2025-09-29T10:00:00",
        "updateTime": "2025-09-29T10:00:00"
      }
    ],
    "total": 1,
    "size": 10,
    "current": 1,
    "pages": 1
  },
  "timestamp": 1640995200000
}
```

### 2.2 获取产品详情

获取指定产品的详细信息，包括SKU信息。

**请求URL**: `GET /product/detail/{id}`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | Long | 是 | 产品ID |

**响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "product": {
      "id": 1,
      "productName": "红玫瑰",
      "productCode": "FLOWER-001",
      "categoryId": 4,
      "brand": "花语",
      "description": "鲜艳的红玫瑰，适合表达爱意",
      "mainImage": "/images/products/rose.svg",
      "subImages": null,
      "detail": null,
      "productType": 1,
      "status": 1,
      "createTime": "2025-09-29T10:00:00",
      "updateTime": "2025-09-29T10:00:00"
    },
    "skus": [
      {
        "id": 1,
        "productId": 1,
        "skuName": "红玫瑰-11朵装",
        "skuCode": "ROSE-11",
        "price": 99.00,
        "stock": 100,
        "specifications": "{\"颜色\": \"红色\", \"数量\": 11}",
        "status": 1,
        "createTime": "2025-09-29T10:00:00",
        "updateTime": "2025-09-29T10:00:00"
      }
    ]
  },
  "timestamp": 1640995200000
}
```

## 3. 产品分类相关接口

### 3.1 获取主页分类列表

获取主页展示的分类列表（只包含启用的一级分类）。

**请求URL**: `GET /productCategory/homepage`

**响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "categoryName": "花卉植物",
      "parentId": 0,
      "categoryLevel": 1,
      "sort": 1,
      "status": 1,
      "createTime": "2025-09-29T10:00:00",
      "updateTime": "2025-09-29T10:00:00"
    }
  ],
  "timestamp": 1640995200000
}
```

### 3.2 获取分类树结构

获取完整的分类树结构。

**请求URL**: `GET /productCategory/tree`

**响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "categoryName": "花卉植物",
      "parentId": 0,
      "categoryLevel": 1,
      "sort": 1,
      "status": 1,
      "children": [
        {
          "id": 4,
          "categoryName": "玫瑰花",
          "parentId": 1,
          "categoryLevel": 2,
          "sort": 1,
          "status": 1,
          "children": []
        }
      ]
    }
  ],
  "timestamp": 1640995200000
}
```

## 4. 商户产品相关接口

### 4.1 商户产品分页查询（支持根据产品名称或编码查询）

根据产品名称或编码查询商户产品信息。

**请求URL**: `GET /merchantProduct/page`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| merchantId | String | 否 | 商户ID |
| productName | String | 否 | 产品名称（模糊查询） |
| productCode | String | 否 | 产品编码（模糊查询） |
| current | Long | 否 | 页码，默认1 |
| size | Long | 否 | 每页数量，默认10 |

**响应示例**:
``json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "records": [
      {
        "id": 1,
        "merchantId": 1,
        "productId": 1,
        "productName": "红玫瑰",
        "productCode": "FLOWER-001",
        "skuId": 1,
        "skuName": "红玫瑰-11朵装",
        "skuCode": "ROSE-11",
        "price": 119.00,
        "stock": 15,
        "status": 1,
        "createTime": "2025-09-29T10:00:00",
        "updateTime": "2025-09-29T10:00:00"
      }
    ],
    "total": 1,
    "size": 10,
    "current": 1,
    "pages": 1
  },
  "timestamp": 1640995200000
}
```

### 4.2 查询商品在各商户的价格和库存

获取指定商品在各商户的价格和库存信息。

**请求URL**: `GET /merchantProduct/product/{productId}`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| productId | Long | 是 | 产品ID |

**响应示例**:
```json
{
  "code": 200,
  "message": "操作成功",
  "data": [
    {
      "id": 1,
      "merchantId": 2,
      "merchant": {
        "id": 2,
        "username": "user1",
        "realName": "用户1",
        "phone": "13800000001",
        "email": "user1@example.com",
        "avatar": null,
        "status": 1,
        "createTime": "2025-09-29T10:00:00",
        "updateTime": "2025-09-29T10:00:00"
      },
      "productId": 1,
      "skuId": 1,
      "price": 109.00,
      "stock": 20,
      "status": 1
    }
  ],
  "timestamp": 1640995200000
}
```

## 5. 其他通用接口

### 5.1 分页查询接口

大多数实体都支持分页查询接口：

**请求URL**: `GET /{entity}/page`

**请求参数**:
| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| current | Long | 否 | 页码，默认1 |
| size | Long | 否 | 每页数量，默认10 |

### 5.2 列表查询接口

**请求URL**: `GET /{entity}/list`

### 5.3 详情查询接口

**请求URL**: `GET /{entity}/{id}`

### 5.4 新增/更新接口

**请求URL**: `POST /{entity}`

**请求体**: 实体对象JSON

### 5.5 删除接口

**请求URL**: `DELETE /{entity}/{id}`