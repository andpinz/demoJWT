package com.course.jwt.coursejwt.service;

import com.course.jwt.coursejwt.dto.JwtResponse;
import com.course.jwt.coursejwt.dto.UsuarioDto;
import com.course.jwt.coursejwt.security.JwtIO;
import com.course.jwt.coursejwt.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private JwtIO jwtIO;

    @Autowired
    private DateUtils dateUtils;

    @Value("${jms.jwt.token.expires-in}")
    private int EXPIRES_IN;

    UUID uid = UUID.randomUUID();

    UsuarioDto user = UsuarioDto.builder()
            .name("Andres")
            .lastName("Pinzon")
            .role("ADMIN")
            .conuntry("Colombia")
            .uid(uid.toString())
            .build();

    public JwtResponse login(String clientId,String clientSecret){
        JwtResponse jwt = JwtResponse.builder()
                .tokenType("bearer")
                .accessToken(jwtIO.generateToken(user))
                .issuedAt(dateUtils.getDateMillis()+"")
                .clientId(clientId)
                .expiresIn(EXPIRES_IN)
                .build();

        return jwt;
    }
}
