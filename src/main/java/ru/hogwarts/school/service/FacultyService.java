package ru.hogwarts.school.service;

import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty deleteFaculty(long id) {
        Faculty faculty = facultyRepository.findById(id).get();
        facultyRepository.deleteById(id);
        return faculty;
    }

    public Collection<Faculty> findByColor(String color) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Неверно задан цвет");
        }
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findByColorOrNameContainsIgnoreCase(String pattern) {
        if (pattern == null || pattern.isBlank()) {
            throw new IllegalArgumentException("Неверно задана строка поиска");
        }
        return facultyRepository.findByNameContainingIgnoreCaseOrColorContainingIgnoreCase(pattern, pattern);
    }

    public Collection<Student> getStudentsOnFaculty(long id) {
        Faculty faculty = facultyRepository.findById(id).get();
        if (faculty != null) {
            return faculty.getStudents();
        }
        return Collections.emptyList();
    }
}
