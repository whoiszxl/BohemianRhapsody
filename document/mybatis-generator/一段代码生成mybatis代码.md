## 一段代码生成mybatis代码

```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--mybatis-plus的springboot支持-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.0.5</version>
        </dependency>
        <!--mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <!--简化代码的工具包-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
            <version>1.18.4</version>
        </dependency>
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.28</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>
    </dependencies>
```



```java

package com.whoiszxl;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generator {

    public static void main(String[] args) throws SQLException {



        DataSourceConfig dsc1 = new DataSourceConfig();
        dsc1.setUrl("jdbc:mysql://localhost:3306/xexchange?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true");
        dsc1.setDriverName("com.mysql.jdbc.Driver");
        dsc1.setUsername("xexchange");
        dsc1.setPassword("xxxxxxx");
        Connection conn = dsc1.getConn();
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (tables.next()) {
            System.out.println("当前生成的表名为：" + tables.getString(3));

            // 创建代码生成器
            AutoGenerator mpg = new AutoGenerator();
            GlobalConfig gc = new GlobalConfig();
            String projectPath = System.getProperty("user.dir");
            gc.setOutputDir(projectPath + "/src/main/java"); //设置输出目录
            gc.setAuthor("whoiszxl"); //设置作者
            gc.setOpen(false);
            mpg.setGlobalConfig(gc);

            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://localhost:3306/xexchange?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true");
            dsc.setDriverName("com.mysql.jdbc.Driver");
            dsc.setUsername("xexchange");
            dsc.setPassword("xxxxxxx");
            mpg.setDataSource(dsc);
            // 配置模块名和包名
            PackageConfig pc = new PackageConfig();
            pc.setModuleName("whoiszxl");
            pc.setParent("com.whoiszxl.core.entity");
            mpg.setPackageInfo(pc);

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                // todo
                }
            };
            List<FileOutConfig> focList = new ArrayList<>();
            focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPath + "/src/main/resources/mapper/" +
                            pc.getModuleName()
                            + "/" + tableInfo.getEntityName() + "Mapper" +
                            StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);
            mpg.setTemplate(new TemplateConfig().setXml(null));
            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            //strategy.setSuperEntityClass("cn.whoiszxl.BasePojo");
            strategy.setEntityLombokModel(true);
            strategy.setRestControllerStyle(true);

            strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
            strategy.setInclude(tables.getString(3));
            strategy.setSuperEntityColumns("id");
            strategy.setControllerMappingHyphenStyle(true);
            strategy.setTablePrefix(pc.getModuleName() + "_");
            mpg.setStrategy(strategy);
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            mpg.execute();

        }



    }
}


```