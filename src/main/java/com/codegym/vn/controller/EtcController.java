package com.codegym.vn.controller;

import com.codegym.vn.model.Classes;
import com.codegym.vn.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/etc")
public class EtcController {
    @Autowired
    IClassesService iClassesService;

    @GetMapping
    public ResponseEntity<?> showRole() {
        Iterable<Classes> roles = iClassesService.findAll();
        if (!roles.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
