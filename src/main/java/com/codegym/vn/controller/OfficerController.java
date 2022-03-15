package com.codegym.vn.controller;


import com.codegym.vn.model.Officer;
import com.codegym.vn.service.IOfficerService;
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
@RequestMapping("/officer")

public class OfficerController {

    @Autowired
    private IOfficerService iOfficerService;


    @GetMapping
    public ResponseEntity<Iterable<Officer>> ShowAll() {
        Iterable<Officer> officers = iOfficerService.findAll();
        if (!officers.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(officers, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Officer>>showPage(@PageableDefault(value = 3) Pageable pageable){
        Page<Officer>officers= iOfficerService.findPage(pageable);
        if(!officers.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(officers,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Officer> createStudent(@RequestBody Officer officer){
        Officer officerCreate= iOfficerService.save(officer);
        return new ResponseEntity<>(officerCreate,HttpStatus.CREATED);
    }



    @PutMapping("{id}")
    public ResponseEntity<Officer> editOfficer(@RequestBody Officer officerEdit,@PathVariable("id") Long id){
        Optional<Officer> officer = iOfficerService.findById(id);
        if(!officer.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        officerEdit.setId(officer.get().getId());
        officerEdit= iOfficerService.save(officerEdit);
        return new ResponseEntity<>(officerEdit,HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Officer> delete(@PathVariable("id") Long id){
        Optional<Officer> officer = iOfficerService.findById(id);
        if(!officer.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iOfficerService.delete(id);
        return new ResponseEntity<>(officer.get(),HttpStatus.OK);
    }


}
