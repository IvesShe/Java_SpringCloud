# Java SpringCloud

SpringCloud筆記

# SpringCloud簡介

SpringCloud是分布式微服務架構的一站式解決方案，是多種服務架構落地技術的集合體，俗稱微服務全家桶。

SpringCloud版本採用名稱而非版本號命名，這些版本的名字採用倫敦地鐵站的名字，根據字母表的順序來對應版本時間順序。(新版本似乎又使用版本號)

## 官網

https://spring.io/projects/spring-cloud#learn

![image](./images/20210705195443.png)

SpringCould對應的SpringBoot版本

![image](./images/20210705195813.png)


## GitHub

https://github.com/spring-projects/spring-cloud/wiki

已經不再維護wiki

![image](./images/20210705195658.png)

## 版本對應參考


https://start.spring.io/actuator/info

![image](./images/20210705201806.png)

## SpringCloud 文檔

https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/

![image](./images/20210705205852.png)
### 中文文檔

https://www.bookstack.cn/read/spring-cloud-docs/docs-index.md

![image](./images/20210705205451.png)

## SpringBoot 文檔

![image](./images/20210705205721.png)

https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/htmlsingle/

# 建立新專案

![image](./images/20210705210249.png)

![image](./images/20210705210421.png)

![image](./images/20210705210443.png)

設定編碼

![image](./images/20210705210750.png)

註解生效激活

![image](./images/20210705210907.png)

設定java編譯版本

![image](./images/20210705211103.png)

File Type過濾(過濾IDEA文件)

![image](./images/20210705211620.png)

# dependencyManagement

Maven使用dependencyManagement元素來提供一種管理依賴版本號的方式

通常會在一個組織或者項目的最頂層的父POM中看到dependencyManagement元素

使用pom.xml中的dependencyManagement元素能讓所有在子項目中引用一個依賴，而不用顯示的列出版本號。

這樣作的好處是，如果有多個子項目都引用同一依賴，則可以避免在每個使用的子項目裡都聲明一個版本號，這樣當頁升級或切換到另一版本時，只需要在頂層父容器裡更新，而不需要一個一個子項目的修改。

另外如果某個子項目需要另外的一個版本，只需要聲明version即可。

**dependencyManagement裡只是聲明依賴，並不實現引入，因為子項目需要顯示的聲明需要用的依賴**

# 微服務模塊

1. 建module
2. 改pom
3. 寫yml
4. 主啟動
5. 業務類
    - 建表SQL
    - entities
    - dao
    - service
    - controller

# 新建模塊

![image](./images/20210706102825.png)

![image](./images/20210706102803.png)

創建完成，父工程pom多一個module

![image](./images/20210706105126.png)



# 父工程

pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.ives.springcloud</groupId>
  <artifactId>cloud2021</artifactId>
  <version>1.0-SNAPSHOT</version>

  <modules>
    <module>cloud-provider-payment8001</module>
  </modules>
  <packaging>pom</packaging>

  <!--統一管理jar包版本-->
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <junit.version>4.12</junit.version>
    <log4j.version>1.2.17</log4j.version>
    <lombok.version>1.16.18</lombok.version>
    <mysql.version>5.1.47</mysql.version>
    <druid.version>1.1.16</druid.version>
    <spring.boot.version>2.2.2.RELEASE</spring.boot.version>
    <spring.cloud.version>Hoxton.SR1</spring.cloud.version>
    <spring.cloud.alibaba.version>2.1.0.RELEASE</spring.cloud.alibaba.version>
    <mybatis.spring.boot.version>1.3.0</mybatis.spring.boot.version>
  </properties>

  <!--子模塊繼承後，提供作用：鎖定版本+子module不用groupId和version-->
  <dependencyManagement>
    <dependencies>
      <!--springboot 2.2.2-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-dependencies</artifactId>
        <version>${spring.boot.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>

      <!--Spring cloud Hoxton.SR1-->
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-dependencies</artifactId>
        <version>${spring.cloud.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <!--Spring cloud alibaba 2.1.0.RELEASE-->
      <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-alibaba-dependencies</artifactId>
        <version>${spring.cloud.alibaba.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>

      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql.version}</version>
      </dependency>
      <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>${druid.version}</version>
      </dependency>
      <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>${mybatis.spring.boot.version}</version>
      </dependency>
      <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>${log4j.version}</version>
      </dependency>
      <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
      </dependency>
      <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
        <optional>true</optional>
      </dependency>
    </dependencies>
  </dependencyManagement>

</project>

```

# could-provider-payment8001

## pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2021</artifactId>
        <groupId>com.ives.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-provider-payment8001</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.10</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>


    </dependencies>
</project>
```

## application.yml

```yml
server:
  port: 8001

spring:
  application:
    name: cloud-payment-service
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource  # 當前數據源操作類型
    driver-class-name: org.gjt.mm.mysql.Driver    # mysql驅動包
    url: jdbc:mysql://localhost:3306/db2021_cloud?useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: root

mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.ives.springcloud.entities # 所有Entity別名類所在包

```

## PaymentMain8001.java

```java
package com.ives.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
```

## 建立數據庫及表

建立數據庫db2021_cloud

建表

```sql
CREATE TABLE `payment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主鍵',
  `serial` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付流水號',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付表' ROW_FORMAT = Dynamic;
```

![image](./images/20210706113023.png)

## entities

Payment.java

```java
package com.ives.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}

```

CommonResult.java

```java
package com.ives.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}

```

## dao

PaymentDao.java

```java
package com.ives.springcloud.dao;

import com.ives.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}

```

## service

PaymentService.java

```java
package com.ives.springcloud.service;

import com.ives.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
```

impl/PaymentServiceImpl.java

```java
package com.ives.springcloud.service.impl;

import com.ives.springcloud.dao.PaymentDao;
import com.ives.springcloud.entities.Payment;
import com.ives.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}

```

## controller

PaymentController.java

```java
package com.ives.springcloud.controller;

import com.ives.springcloud.entities.CommonResult;
import com.ives.springcloud.entities.Payment;
import com.ives.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("======插入結果： "+result);

        if(result>0){
            return new CommonResult(200,"插入數據成功",result);
        }else{
            return new CommonResult(404,"插入數據失敗",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("======查詢結果： "+payment);

        if(payment != null){
            return new CommonResult(200,"查詢成功",payment);
        }else{
            return new CommonResult(404,"沒有對應記錄，查詢失敗，查詢id: "+id,null);
        }
    }

}

```


## 資料夾結構

![image](./images/20210706164824.png)

## 運行

![image](./images/20210706164931.png)

## 訪問

![image](./images/20210706154342.png)

![image](./images/20210706154356.png)

POST使用POSTMAN測試

![image](./images/20210706162557.png)

成功寫入資料到數據庫

![image](./images/20210706162619.png)

# 熱部署 Devtools


## 子工程pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>

```

## 父工程pom.xml

```xml
  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
          <fork>true</fork>
          <addResources>true</addResources>
        </configuration>
      </plugin>
    </plugins>
  </build>
```

## 修改設定

![image](./images/20210706170559.png)

按 ctrl + shift + alt + /


![image](./images/20210706170829.png)

![image](./images/20210706170912.png)

![image](./images/20210706171100.png)

## 重啟IDEA

之後只要修改存檔後，服務器就會自動重啟了，不用再手動停止、手動開啟服務器了!!!

![image](./images/20210706171854.png)

# 新建cloud-consumer-order80模塊

![image](./images/20210706194517.png)

## RestTemplate

RestTemplate提供了多種便捷訪問遠程Http服務的方法，是一種簡單便捷的訪問result服務模板類，是Spring提供用於訪問Rest服務的客戶端模板工具類。

官網地址

https://docs.spring.io/spring-framework/docs/5.2.2.RELEASE/javadoc-api/org/springframework/web/client/RestTemplate.html

## cloud-consumer-order80 - pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2021</artifactId>
        <groupId>com.ives.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-consumer-order80</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
    </dependencies>

</project>
```

## cloud-consumer-order80 - application.yml

```yml
server:
  port: 80
```

## cloud-consumer-order80 - OrderMain80.java

```java
@SpringBootApplication
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}

```

## cloud-consumer-order80 - entities

CommonResult.java

Payment.java

內容與cloud-provider-payment8001類似

## cloud-consumer-order80 - ApplicationContextConfig.java

```java
@Configuration
public class ApplicationContextConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

```

## cloud-consumer-order80 - OrderController.java

```java
@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}

```

## cloud-consumer-order80 - 資料夾結構

![image](./images/20210706205428.png)

因為是透過order80的服務調用8001的服務，所以80服務本身不需要dao及serveice

## 運行及訪問測試

![image](./images/20210706205523.png)

![image](./images/20210706205615.png)

![image](./images/20210706205633.png)

![image](./images/20210706205653.png)

# 新建cloud-api-commons模塊

![image](./images/20210706210832.png)

要將80及8001 entities功能相同的部分(CommonResult、Payment)，抽取出來成一個共用的模塊

![image](./images/20210706210647.png)

# cloud-api-commons - pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>cloud2021</artifactId>
        <groupId>com.ives.springcloud</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>cloud-api-commons</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.1.0</version>
        </dependency>
    </dependencies>

</project>
```

## cloud-api-commons - 拷貝其它工程的entities

![image](./images/20210706212844.png)


在右邊maven選單中，先clean再install，並刪除80及8001工程的entities包及裡面的兩個java檔案，並在兩個工程的pom.xml依賴的開頭加上

```xml
<dependency>
    <groupId>com.ives.springcloud</groupId>
    <artifactId>cloud-api-commons</artifactId>
    <version>${project.version}</version>
</dependency>
```

IDEA會先報紅字，等一下下，就會重新載到cloud-api-commons這個包裡的entities了

作好以上的步驟，刪除80、8001工程的entities，及修改pom.xml
![image](./images/20210706213210.png)


# Eureka 簡介