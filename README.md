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
![](https://ws1.sinaimg.cn/mw690/aeead39fly1g3obphvbidj20j00xyn0b.jpg)
![](https://ws1.sinaimg.cn/mw690/aeead39fly1g3obqgf2ndj20j00xyjub.jpg)
![](https://ws1.sinaimg.cn/mw690/aeead39fly1g3obqqhipwj20j00xy778.jpg)

### 登录页
![](https://ws1.sinaimg.cn/mw690/aeead39fly1g3obrbvz9bj20j00xyn06.jpg)

### 主页(所有图书)
![](https://ws1.sinaimg.cn/mw690/aeead39fly1g3obrwysovj20j00xytdw.jpg)

### 图书详情页
![](https://ws1.sinaimg.cn/mw690/aeead39fly1g3obsloh3fj20j00xydkz.jpg)

### 个人信息及功能菜单页
![](https://ws1.sinaimg.cn/mw690/aeead39fly1g3obt5tw4yj20j00xy0x7.jpg)

### 借阅记录页
![](https://ws1.sinaimg.cn/mw690/aeead39fly1g3obtmd8dvj20j00xyq7h.jpg)

### 关于页
![](https://ws1.sinaimg.cn/mw690/aeead39fly1g3obu4hphuj20j00xyq6k.jpg)