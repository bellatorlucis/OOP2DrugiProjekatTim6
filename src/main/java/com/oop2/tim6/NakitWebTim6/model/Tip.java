package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tip database table.
 * 
 */
@Entity
@Table(name="tip")
@NamedQuery(name="Tip.findAll", query="SELECT t FROM Tip t")
public class Tip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipa")
	private int idTipa;
	
	@Column(name="naziv")
	private String naziv;

	//bi-directional many-to-one association to Nakit
	@OneToMany(mappedBy="tip")
	private List<Nakit> nakits;

	public Tip() {
	}

	public int getIdTipa() {
		return this.idTipa;
	}

	public void setIdTipa(int idTipa) {
		this.idTipa = idTipa;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Nakit> getNakits() {
		return this.nakits;
	}

	public void setNakits(List<Nakit> nakits) {
		this.nakits = nakits;
	}

	public Nakit addNakit(Nakit nakit) {
		getNakits().add(nakit);
		nakit.setTip(this);

		return nakit;
	}

	public Nakit removeNakit(Nakit nakit) {
		getNakits().remove(nakit);
		nakit.setTip(null);

		return nakit;
	}

	@Override
	public String toString() {
		return getNaziv();
	}
	
	
	

}