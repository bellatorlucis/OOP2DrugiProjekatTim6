package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the komentar database table.
 * 
 */
@Entity
@Table(name="komentar")
@NamedQuery(name="Komentar.findAll", query="SELECT k FROM Komentar k")
public class Komentar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_komentara")
	private int idKomentara;

	@Column(name="datum_k")
	private String datumK;

	@Column(name="komentar_roditelj_id")
	private int komentarRoditeljId;

	private String sadrzaj;

	@Column(name="vreme_k")
	private String vremeK;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="Korisnik_id_korisnika")
	private Korisnik korisnik;

	//bi-directional many-to-one association to Ogla
	@ManyToOne
	@JoinColumn(name="Ogla_id_ogla")
	private Ogla ogla;

	public Komentar() {
	}

	public int getIdKomentara() {
		return this.idKomentara;
	}

	public void setIdKomentara(int idKomentara) {
		this.idKomentara = idKomentara;
	}

	public String getDatumK() {
		return this.datumK;
	}

	public void setDatumK(String datumK) {
		this.datumK = datumK;
	}

	public int getKomentarRoditeljId() {
		return this.komentarRoditeljId;
	}

	public void setKomentarRoditeljId(int komentarRoditeljId) {
		this.komentarRoditeljId = komentarRoditeljId;
	}

	public String getSadrzaj() {
		return this.sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public String getVremeK() {
		return this.vremeK;
	}

	public void setVremeK(String vremeK) {
		this.vremeK = vremeK;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Ogla getOgla() {
		return this.ogla;
	}

	public void setOgla(Ogla ogla) {
		this.ogla = ogla;
	}

}