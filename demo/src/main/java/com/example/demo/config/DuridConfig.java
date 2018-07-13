package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author lishunli
 * @create 2017-11-14 10:25
 **/
@Configuration
public class DuridConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private DruidProperties druidProperties;

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername", "druid");
        reg.addInitParameter("loginPassword", "druid");
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
        return filterRegistrationBean;
    }

    @Bean(name="dataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource druidDataSource(){
      //  DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl(druidProperties.getUrl());
//        datasource.setUsername(druidProperties.getUsername());
//        datasource.setPassword(druidProperties.getPassword());
//        datasource.setDriverClassName(druidProperties.getDriverClassName());
//        datasource.setInitialSize(druidProperties.getInitialSize());
//        datasource.setMinIdle(druidProperties.getMinIdle());
//        datasource.setMaxActive(druidProperties.getMaxActive());
//        datasource.setMaxWait(druidProperties.getMaxWait());
//        datasource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
//        datasource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
//        datasource.setValidationQuery(druidProperties.getValidationQuery());
//        datasource.setTestWhileIdle(druidProperties.isTestWhileIdle());
//        datasource.setTestOnBorrow(druidProperties.isTestOnBorrow());
//        datasource.setTestOnReturn(druidProperties.isTestOnReturn());
//        datasource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
//        try {
//            datasource.setFilters(druidProperties.getFilters());
//        } catch (SQLException e) {
//            logger.error("druid configuration initialization filter", e);
//        }
        return new DruidDataSource();

    }



}
