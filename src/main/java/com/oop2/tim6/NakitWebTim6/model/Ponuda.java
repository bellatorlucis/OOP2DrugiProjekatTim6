package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the ponuda database table.
 * 
 */
@Entity
@Table(name="ponuda")
@NamedQuery(name="Ponuda.findAll", query="SELECT p FROM Ponuda p")
public class Ponuda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ponude")
	private int idPonude;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum_vreme")
	private Date datumVreme;

	@Column(name="ponuda_pare")
	private double ponudaPare;

	//bi-directional many-to-one association to Ogla
	@ManyToOne
	@JoinColumn(name="Ogla_id_ogla")
	private Ogla ogla;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="Korisnik_id_korisnika")
	private Korisnik korisnik;

	public Ponuda() {
	}

	public int getIdPonude() {
		return this.idPonude;
	}

	public void setIdPonude(int idPonude) {
		this.idPonude = idPonude;
	}

	public Date getDatumVreme() {
		return this.datumVreme;
	}

	public void setDatumVreme(Date datumVreme) {
		this.datumVreme = datumVreme;
	}

	public double getPonudaPare() {
		return this.ponudaPare;
	}

	public void setPonudaPare(double ponudaPare) {
		this.ponudaPare = ponudaPare;
	}

	public Ogla getOgla() {
		return this.ogla;
	}

	public void setOgla(Ogla ogla) {
		this.ogla = ogla;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	public void setDateTime() {
		LocalDateTime ldt = LocalDateTime.now();		
		this.setDatumVreme(java.sql.Timestamp.valueOf(ldt));
	}
}