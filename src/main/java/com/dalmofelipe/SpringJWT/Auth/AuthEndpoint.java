package com.dalmofelipe.SpringJWT.Auth;

import com.dalmofelipe.SpringJWT.Auth.dtos.RegisterDTO;
import com.dalmofelipe.SpringJWT.Exceptions.ApiError;
import com.dalmofelipe.SpringJWT.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalmofelipe.SpringJWT.Auth.dtos.LoginDTO;

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

    @Autowired
    private UserService userService;

    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody LoginDTO login) {

        // TODO: Mover logica do toAuthToken para controller do login
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
            var err = new ApiError();
            err.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(err);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Validated @RequestBody RegisterDTO dto) {
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
}
