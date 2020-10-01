package com.work.app.controller;

import com.work.app.domain.User;
import com.work.app.dto.UserNameDTO;
import com.work.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User userSaved = service.saveUser(user);
        return ResponseEntity.status(CREATED).body(userSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        User user = service.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<User>> findByName(@PathVariable String name) {
        List<User> user = service.findByName(name);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateName(@PathVariable String id, @RequestBody UserNameDTO userNameDTO) {
        service.updateName(id, userNameDTO.getName());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
