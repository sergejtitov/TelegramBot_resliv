package com.resliv.stitov.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("telegrambot")
public class TelegramConfig {

    private String username;

    private String tmToken;
}
