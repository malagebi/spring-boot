package com.example.demo.config;

/**
 * @author lishunli
 * @create 2017-11-14 17:00
 **/
//@Configuration
public class SqlSessionFactoryConfig {
//    @Resource
//    private DataSource dataSource;
//
//    //private String typeAliasPackage = "com.example.demo.dao.model";
//    private String typeAliasPackage = "com.example.demo.entity";
//    private static String MYBATIS_CONFIG = "mybatis-config.xml";
//    private static String MAPPER_PATH = "mapper/*.xml";
//
//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean() throws  Exception {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        /** 设置mybatis configuration 扫描路径 */
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));
//        /** 添加mapper 扫描路径 */
//        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
//        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_PATH;
//        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
//        /** 设置datasource */
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        /** 设置typeAlias 包扫描路径 */
//        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
//
//        return sqlSessionFactoryBean;
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
////    @Bean
////    public PlatformTransactionManager annotationDrivenTransactionManager() {
////        return new DataSourceTransactionManager(dataSource);
////    }

}
