package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ponuda database table.
 * 
 */
@Entity
@NamedQuery(name="Ponuda.findAll", query="SELECT p FROM Ponuda p")
@Table(name="ponuda")
public class Ponuda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPonude;

	private String datum;

	private double ponudaPare;

	private String vreme;

	//bi-directional many-to-one association to Ogla
	@ManyToOne
	@JoinColumn(name="Oglas_idOglasa")
	private Ogla ogla;

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

}