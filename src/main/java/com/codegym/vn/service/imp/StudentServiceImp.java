package com.codegym.vn.service.imp;


import com.codegym.vn.model.Student;
import com.codegym.vn.repository.IStudentRepository;
import com.codegym.vn.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImp implements IStudentService {
@Autowired
private IStudentRepository iStudentRepository;

    @Override
    public Iterable<Student> findAll() {
        return iStudentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return iStudentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return iStudentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
iStudentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findPage(Pageable pageable) {
        return iStudentRepository.findAll(pageable);
    }

    @Override
    public Iterable<Student> findByName(String name) {
        return iStudentRepository.findAllByFullNameContaining(name);
    }


}
