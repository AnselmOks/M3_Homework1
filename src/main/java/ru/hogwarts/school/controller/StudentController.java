package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public Student editStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("{id}")
    public Student deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @GetMapping
    public Collection<Student> findStudents(@RequestParam(required = false) int age) {
        return studentService.findByAge(age);
    }

    @GetMapping("/ages")
    public Collection<Student> findStudentsByAgeBetween(@RequestParam(name = "min", required = true) int minAge,
                                                        @RequestParam(name = "max", required = true) int maxAge) {
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    @GetMapping("/faculty/{id}")
    public Faculty getFacultyOfStudent(@PathVariable Long id) {
        return studentService.getFacultyOfStudent(id);
    }

}
