package com.codegym.vn.service;

import com.codegym.vn.model.Classes;
import com.codegym.vn.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IStudentService {

    Iterable<Student>findAll();

    Optional<Student> findById(Long id);

    Student save (Student student);

    void delete(Long id);

    Page<Student>findPage(Pageable pageable);

    Iterable<Student>findByName(String name);

}
