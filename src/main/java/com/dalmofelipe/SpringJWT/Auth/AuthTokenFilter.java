package com.dalmofelipe.SpringJWT.Auth;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dalmofelipe.SpringJWT.User.User;
import com.dalmofelipe.SpringJWT.User.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
            FilterChain filterChain) 
                throws ServletException, IOException {
        String tokenWithoutBearer = this.getTokenWithoutBearer(request);
        Boolean isValid = tokenService.isTokenValid(tokenWithoutBearer);

        if(isValid) {
            this.doAuthUser(tokenWithoutBearer);
        }
    }


    private void doAuthUser(String tokenWithoutBearer) {
        Long userId = this.tokenService.getSubject(tokenWithoutBearer);
        Optional<User> optional = this.userRepository.findByIdWithJoinFecth(userId);
        User user = optional.orElseThrow(RuntimeException::new);

        UsernamePasswordAuthenticationToken authetication = 
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authetication);
    }

    
    private String getTokenWithoutBearer(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token.isBlank() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }

}
