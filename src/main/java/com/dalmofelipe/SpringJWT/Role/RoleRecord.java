package com.dalmofelipe.SpringJWT.Role;

import org.springframework.beans.BeanUtils;

public record RoleRecord(String name) {

    public Role toModel() {
        Role role = new Role();
        BeanUtils.copyProperties(this, role);
        return role;
    }
}
