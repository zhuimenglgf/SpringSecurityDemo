package com.zhuimeng.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/8.
 */
@Configuration
public class SqlSessionFactoryBeanConfiguration {

    @Value("${mybatis.config-location}")
    public String configfile;
    @Value("${mybatis.mapper-locations}")
    public String mapperfile;
    @Value("${mybatis.type-aliases-package}")
    public String pojofile;

    @Autowired
    @Qualifier("dataSource")
    public DataSource dataSource;

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(configfile));
        PathMatchingResourcePatternResolver patternResolver=new PathMatchingResourcePatternResolver();
        String mapperpath= PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperfile;
        sqlSessionFactoryBean.setMapperLocations(patternResolver.getResources(mapperpath));
        sqlSessionFactoryBean.setTypeAliasesPackage(pojofile);
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
}
