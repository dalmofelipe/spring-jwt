package com.dalmofelipe.SpringJWT.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserEndpoints {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody RegisterDTO dto) {
        return ResponseEntity.ok(this.userRepository.save(dto.toModel()));
    }

    @GetMapping("/{id}/role")
    public ResponseEntity<User> setUserRole(@PathVariable(name = "id") Long id)  {
        return null;
    }
}
