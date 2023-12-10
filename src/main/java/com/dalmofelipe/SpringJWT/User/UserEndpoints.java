package com.dalmofelipe.SpringJWT.User;

import com.dalmofelipe.SpringJWT.Exceptions.ApiError;
import com.dalmofelipe.SpringJWT.Role.RoleRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
