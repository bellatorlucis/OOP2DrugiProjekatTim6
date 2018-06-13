package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nakit database table.
 * 
 */
@Entity
@NamedQuery(name="Nakit.findAll", query="SELECT n FROM Nakit n")
@Table(name="nakit")
public class Nakit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNakita;

	private String boja;

	private String materijal;

	private String slikaNakita;

	private int tip;

	//bi-directional many-to-one association to Nakittag
	@OneToMany(mappedBy="nakit")
	private List<Nakittag> nakittags;

	//bi-directional many-to-one association to Ogla
	@OneToMany(mappedBy="nakit")
	private List<Ogla> oglas;

	public Nakit() {
	}

	public int getIdNakita() {
		return this.idNakita;
	}

	public void setIdNakita(int idNakita) {
		this.idNakita = idNakita;
	}

	public String getBoja() {
		return this.boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}

	public String getMaterijal() {
		return this.materijal;
	}

	public void setMaterijal(String materijal) {
		this.materijal = materijal;
	}

	public String getSlikaNakita() {
		return this.slikaNakita;
	}

	public void setSlikaNakita(String slikaNakita) {
		this.slikaNakita = slikaNakita;
	}

	public int getTip() {
		return this.tip;
	}

	public void setTip(int tip) {
		this.tip = tip;
	}

	public List<Nakittag> getNakittags() {
		return this.nakittags;
	}

	public void setNakittags(List<Nakittag> nakittags) {
		this.nakittags = nakittags;
	}

	public Nakittag addNakittag(Nakittag nakittag) {
		getNakittags().add(nakittag);
		nakittag.setNakit(this);

		return nakittag;
	}

	public Nakittag removeNakittag(Nakittag nakittag) {
		getNakittags().remove(nakittag);
		nakittag.setNakit(null);

		return nakittag;
	}

	public List<Ogla> getOglas() {
		return this.oglas;
	}

	public void setOglas(List<Ogla> oglas) {
		this.oglas = oglas;
	}

	public Ogla addOgla(Ogla ogla) {
		getOglas().add(ogla);
		ogla.setNakit(this);

		return ogla;
	}

	public Ogla removeOgla(Ogla ogla) {
		getOglas().remove(ogla);
		ogla.setNakit(null);

		return ogla;
	}

}