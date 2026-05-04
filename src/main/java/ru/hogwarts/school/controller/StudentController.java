package ru.hogwarts.school.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        studentService.uploadAvatar(id, avatar);
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = studentService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = studentService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

}
