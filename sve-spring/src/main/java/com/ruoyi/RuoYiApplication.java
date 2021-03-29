package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
public class RuoYiApplication {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("  ______                     ____           \n" +
                " /_  __/__  ____ _____ ___  / __ \\____  ___ \n" +
                "  / / / _ \\/ __ `/ __ `__ \\/ / / / __ \\/ _ \\\n" +
                " / / /  __/ /_/ / / / / / / /_/ / / / /  __/\n" +
                "/_/  \\___/\\__,_/_/ /_/ /_/\\____/_/ /_/\\___/ \n" +
                "                                            ");
    }
}
