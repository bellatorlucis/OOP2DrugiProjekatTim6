package com.oop2.tim6.NakitWebTim6.controller;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.service.IKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

@Controller
public class KorisnikControllerTim6 {

    IKorisnikService korisnikService;



    @RequestMapping(value = "/korisnikSlika/{username}")
    @ResponseBody
    public byte[] getImageKorisnika(@PathVariable String korisnikUsername)  {

        Korisnik korisnik = korisnikService.getKorisnikWithUsername(korisnikUsername);

        return korisnik.getSlika();
    }

    @Autowired
    public void setKorisnikService(IKorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }
}
