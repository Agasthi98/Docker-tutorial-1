package com.spring.dockerturorial1.controller;

import com.spring.dockerturorial1.model.DemoDto;
import com.spring.dockerturorial1.model.response.DemoResponse;
import com.spring.dockerturorial1.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demo")
public class DemoController {

    private final DemoService demoService;

    @PostMapping("/")
    public ResponseEntity<String> home(@RequestBody DemoDto demoDto) {
        return ResponseEntity.ok(demoDto.getName());
    }

    @GetMapping("/test")
    public DemoResponse test() {
        DemoResponse response = demoService.getDemo();

        if (response.getCode() == 200) {
            return response;
        } else {
            return DemoResponse.builder()
                    .code(500)
                    .title("Error")
                    .message("Internal server error")
                    .data(null)
                    .build();
        }
    }
}
