package org.code.challenge.boot;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:configuration.properties")
@Getter
@Setter
public class ConfigurationProperties {

    @Value("${conf.messageLength}")
    private Integer messageLength;
}
