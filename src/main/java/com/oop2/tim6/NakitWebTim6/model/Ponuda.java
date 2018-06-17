package com.oop2.tim6.NakitWebTim6.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.*;


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

	private String datum;

	@Column(name="ponuda_pare")
	private double ponudaPare;

	private String vreme;

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

	public String getDatum() {
		return this.datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public double getPonudaPare() {
		return this.ponudaPare;
	}

	public void setPonudaPare(double ponudaPare) {
		this.ponudaPare = ponudaPare;
	}

	public String getVreme() {
		return this.vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
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
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDate danasnjiDatum = dateTime.toLocalDate();
		LocalTime trenutnoVreme = dateTime.toLocalTime();
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
		this.setDatum(danasnjiDatum.format(dateFormatter));
		
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm");
		this.setDatum(trenutnoVreme.format(timeFormatter));
	}
}