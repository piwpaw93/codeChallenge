package org.code.challenge.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.code.challenge.*")
public class CodeChallengeBootApplication  {

    public static void main(String[] args){
        SpringApplication.run(CodeChallengeBootApplication.class,args);
    }
}
