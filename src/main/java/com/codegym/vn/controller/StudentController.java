package com.codegym.vn.controller;


import com.codegym.vn.model.Student;
import com.codegym.vn.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService iStudentService;

    @GetMapping
    public ResponseEntity<Iterable<Student>>ShowAll() {
        Iterable<Student> students = iStudentService.findAll();
        if (!students.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Student>>showPage(@PageableDefault(value = 3)Pageable pageable){
        Page<Student>students= iStudentService.findPage(pageable);
        if(!students.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student studentCreate= iStudentService.save(student);
        return new ResponseEntity<>(studentCreate,HttpStatus.CREATED);
    }



    @PutMapping("{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student studentEdit,@PathVariable("id") Long id){
        Optional<Student> student = iStudentService.findById(id);
        if(!student.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentEdit.setId(student.get().getId());
        studentEdit= iStudentService.save(studentEdit);
        return new ResponseEntity<>(studentEdit,HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") Long id){
        Optional<Student> student = iStudentService.findById(id);
        if(!student.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iStudentService.delete(id);
        return new ResponseEntity<>(student.get(),HttpStatus.OK);
    }

   @GetMapping("/search")
    public  ResponseEntity<Iterable<Student>> showAllByName(@RequestParam("search") String search){
        Iterable<Student> students = iStudentService.findByName(search);
        if(!students.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students,HttpStatus.OK);
   }
}
