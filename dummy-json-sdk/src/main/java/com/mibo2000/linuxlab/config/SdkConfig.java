package com.mibo2000.linuxlab.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : MibO2000
 */
@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "sdk-dummy")
public class SdkConfig {
    String url;
}
