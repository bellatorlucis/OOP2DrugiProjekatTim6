package com.oop2.tim6.NakitWebTim6.controller;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Uloga;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikCrudRepo;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikJpaRepo;
import com.oop2.tim6.NakitWebTim6.repository.IUlogaRepo;
import com.oop2.tim6.NakitWebTim6.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class RootController {

    @Autowired
    IKorisnikJpaRepo korisnikJpaRepo;

    @Autowired
    UserSecurityService userSecurityService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    IUlogaRepo ulogaRepo;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin() {
        System.out.println("STIGAO SI");
        return "login";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashBoard() {
        return "dashboard";
    }

    @GetMapping(value = "/403")
    public String error403() {
        return "error/403";
    }

    @GetMapping("dashboardAdmin")
    public String getdashboardAdmin(){
        return "dashboardAdmin";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        //Ovo cenmo zapakovati u servis, ovo je samo da vidim da li lepo radi
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    //Ovo je samo pomocni kontroler dok ne unesemo sve nove korisnike
    //Posle ce se napraviti novi kontrolor ya registraciju
    @GetMapping("/noviKorisnik")
    public String noviKorisnik(){
        //Videti u bazi koji je ID korisnika
        Korisnik korisnik = korisnikJpaRepo.findByIdKorisnika(1);
        //Uneti username korisnika koji ce biti password
        //
        userSecurityService.pomocniMetodZaKreiranjeHasha("mknez");
        Korisnik novi  = userSecurityService.saveNewKorisnik(korisnik);
        System.out.println("Novi hash" + novi.getLozinka());

        return "uspeh";
    }

    @GetMapping("/user/kreiraj")
    public String kreiraj(){

        //PRavi novog korisnika u bazi
        //koristio da bi dobio dobre hashove pasvorda
        Korisnik korisnik = new Korisnik();
        Uloga uloga = ulogaRepo.findByIdUloge(1);
        korisnik.setUloga(uloga);
        korisnik.setKorisnickoIme("nmasnikosa");
        korisnik.setLozinka("nmasnikosa");
        korisnik.setKratakOpis("KIDA KIDAM KIDAM");
        korisnik.setIme("Nemanja");
        korisnik.setPrezime("Masnikosa");
        korisnik.setSlika("putanja/korisnici/slika12");


        Korisnik novi = userSecurityService.saveNewKorisnik(korisnik);
        System.out.println(novi.getLozinka());

        return "uspeh";
    }
}

