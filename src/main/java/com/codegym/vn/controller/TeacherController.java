package com.codegym.vn.controller;


import com.codegym.vn.model.Teacher;
import com.codegym.vn.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService iTeacherService;

    @GetMapping
    public ResponseEntity<Iterable<Teacher>> showAll() {
        Iterable<Teacher> teachers = iTeacherService.findAll();
        if (!teachers.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Teacher>> showPage(@PageableDefault(value = 3) Pageable pageable) {
        Page<Teacher> teachers = iTeacherService.findPage(pageable);
        if (!teachers.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
   @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
        Teacher teacherCreate = iTeacherService.save( teacher);
        return new ResponseEntity<>(teacherCreate,HttpStatus.OK);
   }

    @PutMapping("{id}")
    public ResponseEntity<Teacher> editStudent(@RequestBody Teacher teacherEdit, @PathVariable("id") Long id){
        Optional<Teacher> teacher = iTeacherService.findById(id);
        if(!teacher.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        teacherEdit.setId(teacher.get().getId());
        teacherEdit= iTeacherService.save(teacherEdit);
        return new ResponseEntity<>(teacherEdit,HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Teacher> delete(@PathVariable("id") Long id){
        Optional<Teacher> teacher = iTeacherService.findById(id);
        if(!teacher.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iTeacherService.delete(id);
        return new ResponseEntity<>(teacher.get(),HttpStatus.OK);
    }

    @GetMapping("/search")
    public  ResponseEntity<Iterable<Teacher>> showAllByName(@RequestParam("search") String search){
        Iterable<Teacher> teachers = iTeacherService.findByName(search);
        if(!teachers.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teachers,HttpStatus.OK);
    }

}
