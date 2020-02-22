package com.bothub.reservoir;

import com.bothub.reservoir.biz.lark.LarkConfig;
import com.bothub.reservoir.context.ContextConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackageClasses = {
        ContextConfig.class,
        LarkConfig.class,
    }
)
@Slf4j
public class Main {

  public static void main(String[] args) {
    log.info("starting...");
    SpringApplication.run(Main.class, args);
    log.info("end...");
  }
}