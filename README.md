# DevelopTalentRank

## 1. 概述

本项目是一个开源的一款开发者评估应用。

## 2. 运行

1. 运行前请先安装好开发依赖库。
2. Python 算法端 Demo 运行使用 Python-3.13.0 测试通过
3. Java 后端 Demo 运行使用 Java-22.0.2 测试通过
4. Vue 前端 Demo 运行使用 Vite-5.4.10 测试通过

## 3. 框架结构树

``` tree
LeyramuLersosa    DevelopTalentRank
├─lersosa-java    Java 后端
│  ├─lersosa-api    微服务 API 模块
│  │  └─lersosa-api-system    系统模块
│  ├─lersosa-gateway    微服务网关模块
│  │  └─lersosa-gateway-api    API 网关模块
│  ├─lersosa-common    微服务公共模块
│  │  ├─lersosa-common-algorithm    算法模块
│  │  ├─lersosa-common-base    基本模块
│  │  ├─lersosa-common-core    核心模块
│  │  ├─lersosa-common-datascope    数据范围模块
│  │  ├─lersosa-common-datasource    多数据源模块
│  │  ├─lersosa-common-datascope    数据范围模块
│  │  ├─lersosa-common-dependency    业务依赖模块
│  │  ├─lersosa-common-log    消息日志模块
│  │  ├─lersosa-common-redis    数据缓存模块
│  │  ├─lersosa-common-security    数据安全模块
│  │  ├─lersosa-common-sensitive    数据脱敏模块
│  │  └─lersosa-common-swagger    Swagger 模块
│  ├─lersosa-service    微服务业务模块
│  │  ├─lersosa-service-auth    认证中心业务模块
│  │  ├─lersosa-service-file    文件业务模块
│  │  ├─lersosa-service-gen    代码生成模块
│  │  ├─lersosa-service-job    定时任务业务模块
│  │  ├─lersosa-service-system    系统业务模块
│  │  └─lersosa-service-openai    OpenAI 业务模块
│  ├─lersosa-visual    微服务可视化模块
│  │  ├─lersosa-visual-resgistry    注册中心模块
│  │  ├─lersosa-visual-sentinel    限流熔断模块
│  │  └─lersosa-visual-monitor    微服务监控模块
│  └─lersosa-web    微服务页面渲染模块
│      ├─lersosa-web-search    搜索渲染模块
│      └─lersosa-web-page    页面渲染模块
├─lersosa-python    Python 算法端
│  └─src    源代码目录
│       ├─app    应用模块
│       │  ├─routes    路由模块
│       │  └─utils    工具模块
│       └─center    注册中心模块
└─lersosa-vite    Vite 前端
    ├─lersosa-vite-admin    前端管理层模块
    └─lersosa-vite-user    前端用户层模块
```

## 4. 技术栈

1. 算法端：Python、Flask、Flask-RESTful
2. 后端：Java、Spring Cloud、Spring Boot、Spring Security、Spring Data JPA、Spring Cloud Gateway、Spring Cloud OpenFeign、Spring
   Cloud Alibaba、Spring Cloud Stream、Spring Cloud Config、Spring Cloud Sleuth、Spring Cloud
   Zipkin、Redis、MySQL、Docker、Nacos、Swagger、JWT、JWT-OAuth2.0、Feign、Hystrix、RabbitMQ、Shiro、MyBatis、MyBatis-Plus、Lombok、Fastjson、Jackson
3. 前端：Vue、Vite、Element UI Plus、Vue Router、Axios、Pinia、Sass、Js-cookie、Javascript、Typescript、Swagger

## 5. 贡献

1. 后端贡献者：[Miraitowa_zcx](https://github.com/Miraitowa-zcx)

## 6. 版权声明

* Copyright (c) 2024 Leyramu. All rights reserved.
* This project (Lersosa), including its source code, documentation, and any associated materials, is
  the intellectual property of Leyramu. No part of this software may be reproduced, distributed, or transmitted in any
  form or by any means, including photocopying, recording, or other electronic or mechanical methods, without the prior
  written permission of the copyright owner, Miraitowa_zcx, except in the case of brief quotations embodied in critical
  reviews and certain other noncommercial uses permitted by copyright law.
* For inquiries related to licensing or usage outside the scope of this notice, please contact the copyright holder at
  2038322151@qq.com.
* The author disclaims all warranties, express or implied, including but not limited to the warranties of
  merchantability and fitness for a particular purpose. Under no circumstances shall the author be liable for any
  special, incidental, indirect, or consequential damages arising from the use of this software.
* By using this project, users acknowledge and agree to abide by these terms and conditions.
