package com.dalmofelipe.SpringJWT.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleEndpoints {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    public Role createRole(@RequestBody RoleRecord record) {
        return this.roleRepository.save(record.toModel());
    }
}
