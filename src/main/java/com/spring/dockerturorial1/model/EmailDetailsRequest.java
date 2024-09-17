package com.spring.dockerturorial1.model;

import lombok.*;

@Getter
@Setter
public class EmailDetailsRequest {
    // Class data members
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
