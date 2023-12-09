package com.dalmofelipe.SpringJWT.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalmofelipe.SpringJWT.Role.RoleRecord;

@RestController
@RequestMapping("/users")
public class UserEndpoints {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        return ResponseEntity.ok().body(this.userRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody RegisterDTO dto) {
        return ResponseEntity.ok(this.userRepository.save(dto.toModel()));
    }

    @PostMapping("/{id}/role")
    public ResponseEntity<User> setUserRole(@PathVariable(name = "id") Long id,
            @RequestBody RoleRecord role)  {
        return ResponseEntity.ok().body(this.userService.addUserRole(id, role));
    }
}
