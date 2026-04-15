package ru.hogwarts.school.service;

import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student deleteStudent(long id) {
        Student student = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return student;
    }

    public Collection<Student> findByAge(int age) {
        if (age >= 0) {
            return studentRepository.findByAge(age);
        }
        return Collections.emptyList();
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        if (minAge >= 0 && maxAge >= 0) {
            return studentRepository.findByAgeBetween(minAge, maxAge);
        }
        return Collections.emptyList();
    }

    public Faculty getFacultyOfStudent(Long id) {
        Student student = studentRepository.findById(id).get();
        if (student != null) {
            return student.getFaculty();
        }
        return null;
    }
}
