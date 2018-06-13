package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tag database table.
 * 
 */
@Entity
@NamedQuery(name="Tag.findAll", query="SELECT t FROM Tag t")
@Table(name="tag")
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTaga;

	private String naziv;

	//bi-directional many-to-one association to Nakittag
	@OneToMany(mappedBy="tag")
	private List<Nakittag> nakittags;

	public Tag() {
	}

	public int getIdTaga() {
		return this.idTaga;
	}

	public void setIdTaga(int idTaga) {
		this.idTaga = idTaga;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Nakittag> getNakittags() {
		return this.nakittags;
	}

	public void setNakittags(List<Nakittag> nakittags) {
		this.nakittags = nakittags;
	}

	public Nakittag addNakittag(Nakittag nakittag) {
		getNakittags().add(nakittag);
		nakittag.setTag(this);

		return nakittag;
	}

	public Nakittag removeNakittag(Nakittag nakittag) {
		getNakittags().remove(nakittag);
		nakittag.setTag(null);

		return nakittag;
	}

}