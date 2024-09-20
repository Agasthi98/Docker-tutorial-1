package com.spring.dockerturorial1.service.impl;

import com.spring.dockerturorial1.entity.DemoEntity;
import com.spring.dockerturorial1.model.DemoDto;
import com.spring.dockerturorial1.model.response.DemoResponse;
import com.spring.dockerturorial1.repository.DemoRepository;
import com.spring.dockerturorial1.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoServiceImpl implements DemoService {

    private final DemoRepository demoRepository;

    @Override
    public DemoResponse getDemo() {
        ArrayList<DemoDto> demoDtos = new ArrayList<>();
        try {
            List<DemoEntity> allData = demoRepository.findAll();

            if (allData.isEmpty()) {
                log.error("No data found");
                throw new NullPointerException("No data found");
            }
            log.info("Data found");

            for (DemoEntity demoEntity : allData) {
                DemoDto demoDto = DemoDto.builder()
                        .name(demoEntity.getName())
                        .email(demoEntity.getEmail())
                        .build();
                log.info("Data: {}", demoDto);
                demoDtos.add(demoDto);
            }

            HashMap<String, Object> data = new HashMap<>();
            data.put("demo", demoDtos);

            return DemoResponse.builder()
                    .code(200)
                    .title("Success")
                    .message("Get all demo data successfully")
                    .data(data)
                    .build();

        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            return null;
        }
    }
}
