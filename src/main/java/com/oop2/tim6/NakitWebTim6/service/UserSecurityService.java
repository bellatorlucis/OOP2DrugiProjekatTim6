package com.oop2.tim6.NakitWebTim6.service;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.model.Uloga;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikCrudRepo;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityService implements UserDetailsService {

    private IKorisnikJpaRepo korisnikJpaRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private IKorisnikCrudRepo korisnikCrudRepo;
    private String pass;

    @Override
    public UserDetails loadUserByUsername(String korisnickoIme) throws UsernameNotFoundException {
        Korisnik korisnik = korisnikJpaRepo.findByKorisnickoIme(korisnickoIme);
        if (korisnik == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(korisnik.getKorisnickoIme(),
                korisnik.getLozinka(),getAuthority()
                );
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }


    //Koristiti ovaj metod za cuvanje novih korisnika, ili za update vec postojecih
   public Korisnik saveNewKorisnik(Korisnik korisnik){
        //ovde privremeni korisnim private polje pass
       //da bi modifikovali korisnmika
       //kasnije ce ga on sam dobavljati iz korisnik.getLozinka() metoda
        korisnik.setLozinka(bCryptPasswordEncoder.encode(korisnik.getLozinka()));

        return  korisnikCrudRepo.save(korisnik);
   }

   public void pomocniMetodZaKreiranjeHasha(String pass){
        this.pass = pass;
   }

   @Autowired
   public void setKorisnikCrudRepo(IKorisnikCrudRepo korisnikCrudRepo) { this.korisnikCrudRepo = korisnikCrudRepo; }

   @Autowired
   public void setKorisnikJpaRepo(IKorisnikJpaRepo korisnikJpaRepo) { this.korisnikJpaRepo = korisnikJpaRepo; }

   @Autowired
   public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) { this.bCryptPasswordEncoder = bCryptPasswordEncoder; }
}
