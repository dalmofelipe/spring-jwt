package com.dalmofelipe.SpringJWT.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class RegisterDTO {

    @Size(min = 3, max = 20)
    private String name;

    @Size(min = 3, max = 50)
    private String username;

    @Email
    private String email;

    @Size(min = 3, max = 50)
    private String password;

    public User toModel() {
        var user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }
}
