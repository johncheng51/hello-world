package com.jm.config;

import com.jm.model.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@ConfigurationProperties("second.datasource")
public class SecondDataSource extends DataSource{
    
}
