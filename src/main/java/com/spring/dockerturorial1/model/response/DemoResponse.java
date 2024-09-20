package com.spring.dockerturorial1.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Getter
@Builder
public class DemoResponse {
    private int code;
    private String title;
    private String message;
    private HashMap<String, Object> data;

}
