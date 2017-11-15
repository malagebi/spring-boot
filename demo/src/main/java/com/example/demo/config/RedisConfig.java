package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.lang.reflect.Method;

/**
 * RedisConfig
 *
 * @author lishunli
 * @create 2017-11-13 15:05
 **/
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

        @Autowired
        private RedisProperties redisConn;

        /**
         * 生产key的策略
         *
         * @return
         */

        @Bean
        @Override
        public KeyGenerator keyGenerator() {
            return new KeyGenerator() {

                @Override
                public Object generate(Object target, Method method, Object... params) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.getClass().getName());
                    sb.append(method.getName());
                    for (Object obj : params) {
                        sb.append(obj.toString());
                    }
                    return sb.toString();
                }
            };

        }

        /**
         * 管理缓存
         *
         * @param redisTemplate
         * @return
         */

        @SuppressWarnings("rawtypes")
        @Bean
        public CacheManager cacheManager(RedisTemplate redisTemplate) {
            RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
            // 设置cache过期时间,时间单位是秒
            rcm.setDefaultExpiration(60);
           /* Map<String, Long> map = new HashMap<String, Long>();
            map.put("test", 60L);
            rcm.setExpires(map);*/
            return rcm;
        }

        /**
         * redis 数据库连接池
         * @return
         */

        @Bean
        public JedisConnectionFactory redisConnectionFactory() {
            JedisConnectionFactory factory = new JedisConnectionFactory();
            factory.setHostName(redisConn.getHost());
            factory.setPort(redisConn.getPort());
            factory.setTimeout(redisConn.getTimeout());
            factory.setPassword(redisConn.getPassword());
            return factory;
        }

        /**
         * redisTemplate配置
         *
         * @param factory
         * @return
         */
        @SuppressWarnings({ "rawtypes", "unchecked" })
        @Bean
        public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory,RedisSerializer fastJson2JsonRedisSerializer) {
            StringRedisTemplate template = new StringRedisTemplate(factory);
            template.setValueSerializer(fastJson2JsonRedisSerializer);
            template.afterPropertiesSet();
            return template;
        }

    @Bean
    @SuppressWarnings("rawtypes")
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }
    }
