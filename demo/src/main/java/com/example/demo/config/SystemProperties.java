package com.example.demo.config;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Properties
 *
 * @author lishunli
 * @create 2017-11-10 17:51
 **/

@ConfigurationProperties(prefix = "datasourse" )
@Component
@Validated
public class SystemProperties {
    @NotEmpty
    private String url;

    @NotEmpty
    private String username;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
