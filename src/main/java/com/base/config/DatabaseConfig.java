package com.base.config;

import com.base.inteceptor.CameHumpInterceptor;
import com.base.inteceptor.PerformanceInterceptor;
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

        Interceptor[] interceptorArray = new Interceptor[]{cameHumpInterceptor, performanceInterceptor};
        bean.setPlugins(interceptorArray);

        return bean.getObject();
    }
}
