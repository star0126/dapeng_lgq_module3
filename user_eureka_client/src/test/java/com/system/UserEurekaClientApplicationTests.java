package com.system;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/18 11:51
 * @version: 1.0
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEurekaClientApplicationTests {

    /**
     * 数据库驱动
     */
    private static String DRIVER_NAME = "com.mysql.jdbc.Driver";
    /**
     * 数据库链接
     */
    private static String URL = "jdbc:mysql://localhost:3306/boot_module?serverTimezone=GMT&useUnicode=true&characterEncoding=UTF8";
    /**
     * 数据库账号
     */
    private static String USER_NAME = "root";
    /**
     * 数据库密码
     */
    private static String PASS_WORD = "123456";
    /**
     * 生成模块所在路径(一般是项目的基本路径)
     */
    private static String PACKAGE_INFO = "com.system";
    /**
     * controller包名
     */
    private static String CONTROLLER = "controllers";
    /**
     * service包名
     */
    private static String SERVICE = "service";
    private static String SERVICEIMPL = "service.impl";
    /**
     * dao包名
     */
    private static String MAPPER = "dao";
    /**
     * mapper.xml包名
     */
    private static String XML = "dao.mybatis";
    /**
     * 实体类包名
     */
    private static String ENTITY = "entity";
    /**
     * 是否生成swagger注解
     */
    private static boolean SWAGGER = true;
    /**
     * 是否生成lombok注解
     */
    private static boolean LOMBOK = true;
    /**
     * 主键策略
     */
    private static IdType ID_TYPE = IdType.AUTO;

    /**
     * 代码生成
     */
    private static void generator(String[] tableNames) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setOpen(false);
        //是否生成swagger注解
        globalConfig.setSwagger2(SWAGGER);
        //主键策略
        globalConfig.setIdType(ID_TYPE);
        //
        globalConfig.setBaseResultMap(Boolean.TRUE);
        globalConfig.setBaseColumnList(Boolean.TRUE);
        mpg.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(URL);
        dataSourceConfig.setDriverName(DRIVER_NAME);
        dataSourceConfig.setUsername(USER_NAME);
        dataSourceConfig.setPassword(PASS_WORD);
        mpg.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
//    pc.setModuleName(table.get("moduleName"));
//    pc.setModuleName("");
        pc.setParent(PACKAGE_INFO);
        pc.setController(CONTROLLER);
        pc.setService(SERVICE);
        pc.setServiceImpl(SERVICEIMPL);
        pc.setEntity(ENTITY);
        pc.setMapper(MAPPER);
        pc.setXml(XML);
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略(驼峰)
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否为lombok模型
        strategy.setEntityLombokModel(LOMBOK);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(Boolean.FALSE);
        strategy.setInclude(tableNames);
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);

        mpg.setStrategy(strategy);

        mpg.execute();
    }

    //代码生成器
    @Test
    public void contextLoads() {
        List<String> tableList = Arrays.asList(
                "bm_dept",
                "bm_emp"
        );

        generator(tableList.stream().collect(Collectors.joining(",")).split(","));
    }


}
