package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@Table(name="korisnik")
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_korisnika")
	private int idKorisnika;
	
	@Column(name="ime")
    @Size(min = 3, max = 30, message ="Ime mora biti izmedju 3 i 30 karaktera" )
	private String ime;

	@Column(name="korisnicko_ime")
	@NotEmpty(message = "Korisnicko ime ne sme biti prazno")

	private String korisnickoIme;

	@Column(name="kratak_opis")
	private String kratakOpis;
	
	@Column(name="lozinka")
	@NotEmpty(message = "Lozinka ne sme prazna")
	@Size(min = 8, message = "Lozinka mora da sadrzi vise od 8 karaktera")
	private String lozinka;

	@Column(name="prezime")
	private String prezime;

	@Lob
	@Column(name="slika")
	private byte[] slika;
	
	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="korisnik")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Uloga
	@ManyToOne
	@JoinColumn(name="Uloga_id_uloge")
	private Uloga uloga;

	//bi-directional many-to-one association to Ogla
	@OneToMany(mappedBy="korisnik")
	private List<Ogla> oglas;

	//bi-directional many-to-one association to Ponuda
	@OneToMany(mappedBy="korisnik")
	private List<Ponuda> ponudas;

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


	public byte[] getSlika() {
		return this.slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setKorisnik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setKorisnik(null);

		return komentar;
	}

	public Uloga getUloga() {
		return this.uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
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

	public List<Ponuda> getPonudas() {
		return this.ponudas;
	}

	public void setPonudas(List<Ponuda> ponudas) {
		this.ponudas = ponudas;
	}

	public Ponuda addPonuda(Ponuda ponuda) {
		getPonudas().add(ponuda);
		ponuda.setKorisnik(this);

		return ponuda;
	}

	public Ponuda removePonuda(Ponuda ponuda) {
		getPonudas().remove(ponuda);
		ponuda.setKorisnik(null);

		return ponuda;
	}

}