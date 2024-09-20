package com.spring.dockerturorial1.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DemoDto {
    private String name;
    private String email;
}
