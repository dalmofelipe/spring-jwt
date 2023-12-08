package com.dalmofelipe.SpringJWT.Role;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_roles")
public class Role implements GrantedAuthority {

    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
