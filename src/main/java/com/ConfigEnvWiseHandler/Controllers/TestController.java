package com.ConfigEnvWiseHandler.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Value("${common.application.property}")
    public String commonProperty;

    @Value("${different.application.property}")
    public String specificProperty;

    private final Environment environment;

    public TestController(Environment env)
    {
        this.environment = env;
    }

    @GetMapping("/getDefaultProperties")
    public ResponseEntity<String> getProfileSpecificProperties(){
        return new ResponseEntity<>(String.format("Common property = %s and specific property = %s", commonProperty, specificProperty), HttpStatus.OK);
    }

    @GetMapping("/getDefaultPropertiesFromEnv")
    public ResponseEntity<String> getProfileSpecificPropertiesFromEnv(){
        return new
                ResponseEntity<>
                (String.format("Common property = %s and specific property = %s",
                        environment.getProperty("common.application.property"),
                        environment.getProperty("different.application.property")),
                        HttpStatus.OK);
    }
}
