package com.cmp_student.service;

import com.cmp_student.dto.StudentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class StudentService {

    @Value("${url.base.student:http://localhost:8080/students}")
    private String urlBase;

    private final WebClient webClient;

    public StudentService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<StudentDto> findAll() {
        return webClient.get()
                .uri(urlBase)
                .retrieve()
                .bodyToFlux(StudentDto.class);
    }
}
