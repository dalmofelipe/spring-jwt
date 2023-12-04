package com.dalmofelipe.SpringJWT.Auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalmofelipe.SpringJWT.User.LoginDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthEndpoint {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    
    @PostMapping
    public ResponseEntity<String> auth(@Validated @RequestBody LoginDTO login) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
            = login.toAuthToken();

        try {
            Authentication autheticate = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

            String token = this.tokenService.generateJWT(autheticate);
            String fullToken = "Bearer " + token;
            
            return ResponseEntity.ok(fullToken);
        } 
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
