package com.example.demo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author lishunli
 * @create 2017-11-13 17:05
 **/
@Configuration
//@EnableScheduling
public class SchedulingConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // 每20秒执行一次
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void scheduler() {
//    }

}
