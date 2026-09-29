package com.cmp_student.controller;

import com.cmp_student.dto.StudentDto;
import com.cmp_student.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Flux<StudentDto> findAll() {
        return studentService.findAll();
    }

    /** HU-004: PUT /student/{id} -> 200 con el objeto modificado, 404 si no existe. */
    @PutMapping("/{id}")
    public Mono<StudentDto> update(@PathVariable Integer id, @RequestBody StudentDto dto) {
        return studentService.update(id, dto);
    }
}