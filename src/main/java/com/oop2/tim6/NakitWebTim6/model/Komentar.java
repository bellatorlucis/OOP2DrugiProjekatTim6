package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;

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
@NamedQuery(name="Komentar.findAll", query="SELECT k FROM Komentar k")
@Table(name="komentar")
public class Komentar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKomentara;

	private String datumK;

	private int ref;

	private String sadrzaj;

	private String vremeK;

	//bi-directional many-to-one association to Ogla
	@ManyToOne
	@JoinColumn(name="Oglas_idOglasa")
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

	public int getRef() {
		return this.ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
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

	public Ogla getOgla() {
		return this.ogla;
	}

	public void setOgla(Ogla ogla) {
		this.ogla = ogla;
	}

}