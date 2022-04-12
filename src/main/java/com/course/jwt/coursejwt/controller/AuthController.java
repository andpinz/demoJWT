package com.course.jwt.coursejwt.controller;

import com.course.jwt.coursejwt.exception.Apiunauthorized;
import com.course.jwt.coursejwt.service.AuthService;
import com.course.jwt.coursejwt.validator.AuthValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "v1.0")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthValidator validator;

    @PostMapping(path = "oauth/client_credential/accestoken",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>login(@RequestBody MultiValueMap<String,String>paramMap,
                                       @RequestParam("grant_type")String grantType) throws Apiunauthorized {
        validator.validate(paramMap,grantType);
        return ResponseEntity.ok(authService.login(paramMap.getFirst("client_id"),paramMap.getFirst("client_secret")));
    }
}
