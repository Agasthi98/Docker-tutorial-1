package com.spring.dockerturorial1.controller;

import com.spring.dockerturorial1.model.DemoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @PostMapping("/")
    public ResponseEntity<String> home(@RequestBody DemoDto demoDto) {
        return ResponseEntity.ok(demoDto.getName());
    }

}
