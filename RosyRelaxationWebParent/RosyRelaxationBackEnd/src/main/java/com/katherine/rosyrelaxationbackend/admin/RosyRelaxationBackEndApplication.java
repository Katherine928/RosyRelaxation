package com.katherine.rosyrelaxationbackend.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.katherine.rosyrelaxationcommon.entity","com.katherine.rosyrelaxationbackend.admin"})
public class RosyRelaxationBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(RosyRelaxationBackEndApplication.class, args);
    }

}
