package com.dalmofelipe.SpringJWT.User;

import com.dalmofelipe.SpringJWT.Role.Role;
import com.dalmofelipe.SpringJWT.Role.RoleRecord;
import com.dalmofelipe.SpringJWT.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public User addUserRole(Long id, RoleRecord roleRecord) {
        Optional<User> userOpt = this.userRepository.findById(id);
        var user = userOpt.orElseThrow(() -> new RuntimeException("usuário não encontrado"));

        Optional<Role> roleOpt = this.roleRepository.findByName(roleRecord.name());
        var role = roleOpt.orElseThrow(() -> new RuntimeException("função não encontrada"));

        user.getRoles().add(role);
        return this.userRepository.save(user);
    }
}
