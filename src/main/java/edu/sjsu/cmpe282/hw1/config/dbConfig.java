package edu.sjsu.cmpe282.hw1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by dexterwei on 3/3/16.
 */
//@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class dbConfig {

    private static String uri;
    private static int port;
    private static String name;

    public static void setUri(String uri) {
        dbConfig.uri = uri;
    }

    public static void setPort(int port) {
        dbConfig.port = port;
    }

    public static void setName(String name) {
        dbConfig.name = name;
    }

    public static String getUri() {
        return uri;
    }

    public static int getPort() {
        return port;
    }

    public static String getName() {
        return name;
    }
}
