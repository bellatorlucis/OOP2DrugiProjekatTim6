package com.oop2.tim6.NakitWebTim6.service;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikCrudRepoTim6;
import com.oop2.tim6.NakitWebTim6.repository.IKorisnikJpaRepoTim6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserSecurityServiceTim6 implements UserDetailsService {

    private IKorisnikJpaRepoTim6 korisnikJpaRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private IKorisnikCrudRepoTim6 korisnikCrudRepo;

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

        Korisnik rez = korisnikJpaRepo.findByKorisnickoIme(korisnik.getKorisnickoIme());
        if(rez != null)
            return null;

        korisnik.setLozinka(bCryptPasswordEncoder.encode(korisnik.getLozinka()));

        return  korisnikCrudRepo.save(korisnik);
   }


   @Autowired
   public void setKorisnikCrudRepo(IKorisnikCrudRepoTim6 korisnikCrudRepo) { this.korisnikCrudRepo = korisnikCrudRepo; }

   @Autowired
   public void setKorisnikJpaRepo(IKorisnikJpaRepoTim6 korisnikJpaRepo) { this.korisnikJpaRepo = korisnikJpaRepo; }

   @Autowired
   public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) { this.bCryptPasswordEncoder = bCryptPasswordEncoder; }
}
