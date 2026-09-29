package com.cmp_student.service;

import com.cmp_student.dto.StudentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Value("${url.base.student:http://localhost:8080/student}")
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

    /** Busca un estudiante por id; si no existe emite 404. */
    public Mono<StudentDto> findById(Integer id) {
        return webClient.get()
                .uri(urlBase + "/{id}", id)
                .retrieve()
                .onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
                        response -> Mono.error(notFound(id)))
                .bodyToMono(StudentDto.class);
    }

    /**
     * HU-004: actualiza un estudiante existente.
     * 1) Valida que exista (si no, 404).
     * 2) Aplica los cambios en el servicio de persistencia con PUT.
     * 3) Retorna el objeto modificado.
     */
    public Mono<StudentDto> update(Integer id, StudentDto dto) {
        dto.setId(id); // el id de la URL manda sobre el del body
        return findById(id)
                .flatMap(existing -> webClient.put()
                        .uri(urlBase + "/{id}", id)
                        .bodyValue(dto)
                        .retrieve()
                        .onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
                                response -> Mono.error(notFound(id)))
                        .bodyToMono(StudentDto.class));
    }

    private ResponseStatusException notFound(Integer id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Estudiante con id " + id + " no encontrado");
    }
}