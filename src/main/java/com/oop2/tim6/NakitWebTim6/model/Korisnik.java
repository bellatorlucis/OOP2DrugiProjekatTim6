package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
@Table(name="korisnik")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int idKorisnika;
	
	@Column(name="ime")
	private String ime;

	private String korisnickoIme;

	private String kratakOpis;

	private String lozinka;

	private String prezime;
	
	private String slika;

	//bi-directional many-to-one association to Ogla
	@OneToMany(mappedBy="korisnik")
	private List<Ogla> oglas;

	public Korisnik() {
	}

	public int getIdKorisnika() {
		return this.idKorisnika;
	}

	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getKratakOpis() {
		return this.kratakOpis;
	}

	public void setKratakOpis(String kratakOpis) {
		this.kratakOpis = kratakOpis;
	}

	public String getLozinka() {
		return this.lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public List<Ogla> getOglas() {
		return this.oglas;
	}

	public void setOglas(List<Ogla> oglas) {
		this.oglas = oglas;
	}

	public Ogla addOgla(Ogla ogla) {
		getOglas().add(ogla);
		ogla.setKorisnik(this);

		return ogla;
	}

	public Ogla removeOgla(Ogla ogla) {
		getOglas().remove(ogla);
		ogla.setKorisnik(null);

		return ogla;
	}

	@Override
	public String toString() {
		return "Korisnik [idKorisnika=" + idKorisnika + ", ime=" + ime + ", korisnickoIme=" + korisnickoIme
				+ ", kratakOpis=" + kratakOpis + ", lozinka=" + lozinka + ", prezime=" + prezime + ", slika=" + slika
				+ ", oglas=" + oglas + "]";
	}
	

}