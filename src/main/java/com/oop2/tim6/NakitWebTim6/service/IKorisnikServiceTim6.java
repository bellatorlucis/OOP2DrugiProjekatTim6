package com.oop2.tim6.NakitWebTim6.service;

import com.oop2.tim6.NakitWebTim6.model.Korisnik;

public interface IKorisnikServiceTim6 {
    Korisnik getKorisnikWithUsername(String username);
    Korisnik getKorisnikWithId(int id);
}
