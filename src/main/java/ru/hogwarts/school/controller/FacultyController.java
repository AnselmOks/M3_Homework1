package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public Faculty editFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public Faculty deleteFaculty(@PathVariable Long id) {
        return facultyService.deleteFaculty(id);
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }

    @GetMapping
    public Collection<Faculty> findFaculties(@RequestParam(required = false) String color) {
        return facultyService.findByColor(color);
    }

    @GetMapping("/find/{pattern}")
    public Collection<Faculty> findFacultiesExtended(@PathVariable("pattern") String pattern) {
        return facultyService.findByColorOrNameContainsIgnoreCase(pattern);
    }

    @GetMapping("/students/{id}")
    public Collection<Student> getStudentsOnFaculty(@PathVariable("id") long id) {
        return facultyService.getStudentsOnFaculty(id);
    }

}
