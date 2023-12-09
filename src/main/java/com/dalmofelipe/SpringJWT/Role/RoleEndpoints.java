package com.dalmofelipe.SpringJWT.Role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalmofelipe.SpringJWT.Exceptions.ApiError;

@RestController
@RequestMapping("/roles")
public class RoleEndpoints {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Object> createRole(@RequestBody RoleRecord record) {
        Role role = this.roleService.save(record.toModel());
        
        if (role == null) {
            var err = new ApiError();
            err.setMessage("a ROLE ("+record.name()+") já existe");

            return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(err);
        }

        return ResponseEntity.ok().body(role);
    }

    @GetMapping
    public List<Role> listAll() {
        return this.roleService.listAll();
    }
}
