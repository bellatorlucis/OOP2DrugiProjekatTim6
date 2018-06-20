package com.oop2.tim6.NakitWebTim6.config;

import com.oop2.tim6.NakitWebTim6.NakitWebTim6Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


public class ServletInitializerTim6 extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NakitWebTim6Application.class);
    }
}
