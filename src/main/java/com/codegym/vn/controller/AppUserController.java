package com.codegym.vn.controller;

import com.codegym.vn.model.AppUser;
import com.codegym.vn.service.IAppUserService;
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
@RequestMapping("/admin/users")
public class AppUserController {
    @Autowired
    private IAppUserService iAppUserService;

    @GetMapping
    public ResponseEntity<Iterable<AppUser>> showAll() {
        Iterable<AppUser> users = iAppUserService.findAll();
        if (!users.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> showOne(@PathVariable("id") Long id) {
        Optional<AppUser> appUser = iAppUserService.findById(id);
        if (!appUser.isPresent()) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(appUser.get(), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<AppUser>> showPage(@PageableDefault(value = 3) Pageable pageable) {
        Page<AppUser> users = iAppUserService.findPage(pageable);
        if (!users.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<AppUser> createAppUser(@RequestPart("json") AppUser appUser){
        AppUser appUser1 = iAppUserService.save(appUser);
        return new ResponseEntity<>(appUser1,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AppUser> editStudent(@RequestBody AppUser appUserEdit,@PathVariable("id") Long id){
        Optional<AppUser> appUser = iAppUserService.findById(id);
        if(!appUser.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        appUserEdit.setId(appUser.get().getId());
        appUserEdit= iAppUserService.save(appUserEdit);
        return new ResponseEntity<>(appUserEdit,HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<AppUser> delete(@PathVariable("id") Long id){
        Optional<AppUser> appUser = iAppUserService.findById(id);
        if(!appUser.isPresent()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iAppUserService.delete(id);
        return new ResponseEntity<>(appUser.get(),HttpStatus.OK);
    }

    @GetMapping("/search")
    public  ResponseEntity<Iterable<AppUser>> showAllByName(@RequestParam("search") String search){
        Iterable<AppUser> appUsers = iAppUserService.findByName(search);
        if(!appUsers.iterator().hasNext()){
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(appUsers,HttpStatus.OK);
    }
}
