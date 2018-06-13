package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the oglas database table.
 * 
 */
@Entity
@Table(name="oglas")
@NamedQuery(name="Ogla.findAll", query="SELECT o FROM Ogla o")
public class Ogla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOglasa;

	private int idKorisnika;

	private double minPonuda;

	private String tekst;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="ogla")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Nakit
	@ManyToOne
	private Nakit nakit;

	//bi-directional many-to-one association to Ponuda
	@OneToMany(mappedBy="ogla")
	private List<Ponuda> ponudas;

	public Ogla() {
	}

	public int getIdOglasa() {
		return this.idOglasa;
	}

	public void setIdOglasa(int idOglasa) {
		this.idOglasa = idOglasa;
	}

	public int getIdKorisnika() {
		return this.idKorisnika;
	}

	public void setIdKorisnika(int idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public double getMinPonuda() {
		return this.minPonuda;
	}

	public void setMinPonuda(double minPonuda) {
		this.minPonuda = minPonuda;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setOgla(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setOgla(null);

		return komentar;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Nakit getNakit() {
		return this.nakit;
	}

	public void setNakit(Nakit nakit) {
		this.nakit = nakit;
	}

	public List<Ponuda> getPonudas() {
		return this.ponudas;
	}

	public void setPonudas(List<Ponuda> ponudas) {
		this.ponudas = ponudas;
	}

	public Ponuda addPonuda(Ponuda ponuda) {
		getPonudas().add(ponuda);
		ponuda.setOgla(this);

		return ponuda;
	}

	public Ponuda removePonuda(Ponuda ponuda) {
		getPonudas().remove(ponuda);
		ponuda.setOgla(null);

		return ponuda;
	}

}