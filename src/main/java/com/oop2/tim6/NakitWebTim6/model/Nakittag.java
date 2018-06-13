package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nakittag database table.
 * 
 */
@Entity
@NamedQuery(name="Nakittag.findAll", query="SELECT n FROM Nakittag n")
@Table(name="nakittag")
public class Nakittag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;


	//bi-directional many-to-one association to Nakit
	@ManyToOne
	private Nakit nakit;

	//bi-directional many-to-one association to Tag
	@ManyToOne
	private Tag tag;

	public Nakittag() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Nakit getNakit() {
		return this.nakit;
	}

	public void setNakit(Nakit nakit) {
		this.nakit = nakit;
	}

	public Tag getTag() {
		return this.tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

}