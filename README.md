# MYLIBRARY图书管理系统(安卓学生端)

## 接口文档

**MYLIBRARY图书管理系统 使用 Swagger2 构建 API**


**简介**：API 描述

**HOST**:library.luhailiang.top

**联系人**:肆意。-

**Version**:1.0

**接口路径**：/v2/api-docs


# 安卓端用户登录
## 用户登录


**接口描述**:


**接口地址**:`/api/user/login`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`



**请求参数**：

| 参数名称     | 参数说明 | in    | 是否必须 | 数据类型 | schema |
| ------------ | -------- | ----- | -------- | -------- | ------ |
| userName     | 用户名   | query | true     | string   |        |
| userPassword | 密码     | query | true     | string   |        |

**响应示例**:

```json
{
	"data": {},
	"msg": "",
	"ret": true
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型    | schema |
| -------- | -------- | ------- | ------ |
| data     |          | object  |        |
| msg      |          | string  |        |
| ret      |          | boolean |        |





**响应状态**:


| 状态码 | 说明         | schema   |
| ------ | ------------ | -------- |
| 200    | OK           | JsonData |
| 201    | Created      |          |
| 401    | Unauthorized |          |
| 403    | Forbidden    |          |
| 404    | Not Found    |          |
# 安卓端获取图书信息

## 获取所有图书信息


**接口描述**:


**接口地址**:`/api/books`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：
暂无



**响应示例**:

```json
{
	"data": {},
	"msg": "",
	"ret": true
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型    | schema |
| -------- | -------- | ------- | ------ |
| data     |          | object  |        |
| msg      |          | string  |        |
| ret      |          | boolean |        |





**响应状态**:


| 状态码 | 说明         | schema   |
| ------ | ------------ | -------- |
| 200    | OK           | JsonData |
| 401    | Unauthorized |          |
| 403    | Forbidden    |          |
| 404    | Not Found    |          |
## 获取单本图书信息


**接口描述**:


**接口地址**:`/api/books/{bookId}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称 | 参数说明 | in   | 是否必须 | 数据类型 | schema |
| -------- | -------- | ---- | -------- | -------- | ------ |
| bookId   | 图书编号 | path | true     | integer  |        |

**响应示例**:

```json
{
	"data": {},
	"msg": "",
	"ret": true
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型    | schema |
| -------- | -------- | ------- | ------ |
| data     |          | object  |        |
| msg      |          | string  |        |
| ret      |          | boolean |        |





**响应状态**:


| 状态码 | 说明         | schema   |
| ------ | ------------ | -------- |
| 200    | OK           | JsonData |
| 401    | Unauthorized |          |
| 403    | Forbidden    |          |
| 404    | Not Found    |          |
# 安卓端获取用户借还记录
## 获取用户借阅记录


**接口描述**:


**接口地址**:`/api/lendReturnRecords/{userId}`


**请求方式**：`GET`


**consumes**:``


**produces**:`["*/*"]`



**请求参数**：

| 参数名称 | 参数说明 | in   | 是否必须 | 数据类型 | schema |
| -------- | -------- | ---- | -------- | -------- | ------ |
| userId   | 用户编号 | path | true     | integer  |        |

**响应示例**:

```json
{
	"data": {},
	"msg": "",
	"ret": true
}
```

**响应参数**:


| 参数名称 | 参数说明 | 类型    | schema |
| -------- | -------- | ------- | ------ |
| data     |          | object  |        |
| msg      |          | string  |        |
| ret      |          | boolean |        |





**响应状态**:


| 状态码 | 说明         | schema   |
| ------ | ------------ | -------- |
| 200    | OK           | JsonData |
| 401    | Unauthorized |          |
| 403    | Forbidden    |          |
| 404    | Not Found    |          |


## APP运行效果
### 欢迎页
![](http://library.luhailiang.top/uploads/2019/06/03/3/8/4538cbd5-98d7-4f76-aa71-3c51eca6b332.png)
![](http://library.luhailiang.top//uploads/2019/06/03/4/0/b3314008-2491-4acc-ad7a-66847dd277d0.png)
![](http://library.luhailiang.top/uploads/2019/06/03/5/8/95f7dac0-573c-48e1-bc2b-a79b29e27c87.png)
### 登录页
![](http://library.luhailiang.top/uploads/2019/06/03/6/3/842c4f97-4d41-435a-ac06-2617a7ddeae4.png)

### 主页(所有图书)
![](http://library.luhailiang.top/uploads/2019/06/03/5/12/1073fde9-5f74-4d98-9e24-0fa1d077b5a9.png)


### 图书详情页
![](http://library.luhailiang.top/uploads/2019/06/03/9/12/d86cc84e-1535-4cbb-b43a-78f9450e99f8.png)

### 个人信息及功能菜单页
![](http://library.luhailiang.top/uploads/2019/06/03/14/7/38104151-bdf8-49a2-9979-3279c8c31330.png)

### 借阅记录页
![](http://library.luhailiang.top/uploads/2019/06/03/5/2/2c9802d3-cc4d-4dbb-9676-7dd5d615c13c.png)

### 关于页
![](http://library.luhailiang.top/uploads/2019/06/03/5/9/716f348f-af43-4207-bc44-1a036a1ccf64.png)