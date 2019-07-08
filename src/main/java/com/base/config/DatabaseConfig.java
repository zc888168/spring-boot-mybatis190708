package com.base.config;

import com.base.inteceptor.*;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
@Component
@MapperScan("com.base.**.dao")
public class DatabaseConfig {

    @Resource
    private DataSource datasource;
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);


        // 驼峰插件
        CameHumpInterceptor cameHumpInterceptor = new CameHumpInterceptor();

        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        JdbcTypeInterceptor jdbcTypeInterceptor = new JdbcTypeInterceptor();

        ResultTypeInterceptor resultTypeInterceptor = new ResultTypeInterceptor();
        PageInterceptor pageInterceptor = new PageInterceptor();
        Interceptor[] interceptorArray = new Interceptor[]{cameHumpInterceptor, performanceInterceptor,
                jdbcTypeInterceptor, resultTypeInterceptor, pageInterceptor};
        bean.setPlugins(interceptorArray);

        return bean.getObject();
    }
}
