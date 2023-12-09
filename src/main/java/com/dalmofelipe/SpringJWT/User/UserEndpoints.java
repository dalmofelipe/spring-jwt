package com.dalmofelipe.SpringJWT.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalmofelipe.SpringJWT.Auth.dtos.RegisterDTO;
import com.dalmofelipe.SpringJWT.Exceptions.ApiError;
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
    public ResponseEntity<Object> createUser(@Validated @RequestBody RegisterDTO dto) {
        try {
            return ResponseEntity.ok(this.userService.saveUser(dto));
        } 
        catch (RuntimeException e) {
            var err = new ApiError();
            err.setMessage(e.getMessage());

            return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(err);
        }
    }

    @PostMapping("/{id}/role")
    public ResponseEntity<Object> setUserRole(@PathVariable(name = "id") Long id,
            @RequestBody RoleRecord role) {

        try {
            return ResponseEntity.ok().body(this.userService.addUserRole(id, role));
        } 
        catch (RuntimeException e) {
            var err = new ApiError();
            err.setMessage(e.getMessage());

            return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(err);
        }
    }
}
