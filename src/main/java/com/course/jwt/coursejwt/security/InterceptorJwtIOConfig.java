package com.course.jwt.coursejwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorJwtIOConfig  implements WebMvcConfigurer {

    @Value("${jms.jwt.security.enabled}")
    private boolean securityEnable;

    @Autowired
    private InterceptorJWTIO interceptorJWTIO;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        if(securityEnable){
            registry.addInterceptor(interceptorJWTIO);
        }
    }

}
