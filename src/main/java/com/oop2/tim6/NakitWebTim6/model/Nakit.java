package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the nakit database table.
 * 
 */
@Entity
@Table(name="nakit")
@NamedQuery(name="Nakit.findAll", query="SELECT n FROM Nakit n")
public class Nakit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_nakita")
	private int idNakita;

	private String boja;

	private String materijal;

	@Column(name="slika_nakita")
	private String slikaNakita;

	//bi-directional many-to-one association to Tip
	@ManyToOne
	@JoinColumn(name="Tip_id_tipa")
	private Tip tip;

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

	public Tip getTip() {
		return this.tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
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
	
	public String opisNakitaToHTML() {
		
		String html = "<h2>"+tip.getNaziv()+"</h2>"
				+ "<p>OPIS: <br> Boja: " + boja + " <br> Materijal: "+materijal+"</p>";
		
		return html;
	}

}