# 一.项目架构
## 1.1.层级依赖图
![github]()
## 1.2.层级说明
### 1.2.1.common-dependency
管理整个common工程子包以及公共三方依赖版本

### 1.2.2.common-base
基础包，依赖进来统一的工具、插件，定义普遍要使用的实体对象(page等)、异常对象、结果对象等

### 1.2.3.common-api
> 此工程内无含义，为指导作用

用于远程rpc接口定义，此包内定义远程调用接口信息与需要开放给三方使用的实体与工具类信息

### 1.2.4.common-rpc
> 此工程内无含义，为指导作用

为api包的feign接口定义，包括异常回滚策略也会定义在此处。如果需要提供相应的快速启动bean也是在此包内定义

### 1.2.5.common-service
定义服务层所需要的公共依赖

### 1.2.6.common-interaction
用户交互层，主要用于定义与外部交互的类，包括但不仅限于controller,mq,rpc

### 1.2.7.common-start
整个应用启动入口，提供了nacos的关联配置，日志的相关配置，启动类注解

# 二.使用方式
## 2.1.pom管理
1.应用服务的父pom从spring-boot修改为

```
<parent>
    <groupId>com.baiyan</groupId>
    <artifactId>common</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <relativePath/>
</parent>
```
2.增加整体版本依赖管理

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.baiyan</groupId>
            <artifactId>common-dependency</artifactId>
            <version>${project.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## 2.2.业务包划分
业务包按照common包分包规则进行划分。

## 2.3.配置说明
- 日期时间序列化与反序列化在web与rpc传输时开关

```
common:
  config:
    jackson:
      enable: true
```


---
- swagger3.0配置

```
swagger:
  config:
    enable: true
    groupName: 分组
    basePackage: com.examp.demo.controller
    title: demo
    description: demo描述
    version: 测试版本
```

---

- 领域事件服务

```
common:
  config:
    domain:
      event:
        enable: true
```



