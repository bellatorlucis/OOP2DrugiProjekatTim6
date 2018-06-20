package com.oop2.tim6.NakitWebTim6.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop2.tim6.NakitWebTim6.service.KorisnikServiceTim6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLoginSuccessfulHandlerTim6 implements AuthenticationSuccessHandler {

    KorisnikServiceTim6 korisnikService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
	  request.getSession().setAttribute("korisnik",korisnikService.getKorisnikWithUsername(authentication.getName()));
	  response.sendRedirect("/nakitWeb/dashboard");
  }

  @Autowired
  public void setKorisnikService(KorisnikServiceTim6 korisnikService) { this.korisnikService = korisnikService; }
}