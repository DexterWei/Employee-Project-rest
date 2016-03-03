package edu.sjsu.cmpe282.hw1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by dexter on 2/28/16.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"edu.sjsu.cmpe282.hw1.controller","edu.sjsu.cmpe282.hw1.model","edu.sjsu.cmpe282.hw1.dao","edu.sjsu.cmpe282.hw1.config"})
public class application {
    public static void main(String[] args){
        SpringApplication.run(application.class, args);
    }
}
