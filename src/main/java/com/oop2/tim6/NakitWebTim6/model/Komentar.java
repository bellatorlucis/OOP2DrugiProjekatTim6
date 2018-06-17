package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum_vreme")
	private Date datumVreme;

	@Column(name="komentar_roditelj_id")
	private int komentarRoditeljId;

	private String sadrzaj;

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

	public Date getDatumVreme() {
		return this.datumVreme;
	}

	public void setDatumVreme(Date datumVreme) {
		this.datumVreme = datumVreme;
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