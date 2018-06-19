package com.oop2.tim6.NakitWebTim6.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ogla database table.
 * 
 */
@Entity
@Table(name="ogla")
@NamedQuery(name="Ogla.findAll", query="SELECT o FROM Ogla o")
public class Ogla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ogla")
	private int idOgla;
	
	@Column(name="aktivan")
	private int aktivan;

	@Column(name="min_ponuda")
	private double minPonuda;
	
	@Column(name="naslov")
	private String naslov;

	@Column(name="tekst")
	private String tekst;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datum_vreme")
	private Date datumVreme;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="ogla")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="Korisnik_id_korisnika")
	private Korisnik korisnik;

	//bi-directional many-to-one association to Nakit
	@ManyToOne
	@JoinColumn(name="Nakit_id_nakita")
	private Nakit nakit;

	//bi-directional many-to-one association to Ponuda
	@OneToMany(mappedBy="ogla")
	private List<Ponuda> ponudas;

	public Ogla() {
	}

	public int getIdOgla() {
		return this.idOgla;
	}

	public void setIdOgla(int idOgla) {
		this.idOgla = idOgla;
	}

	public int getAktivan() {
		return this.aktivan;
	}

	public void setAktivan(int aktivan) {
		this.aktivan = aktivan;
	}

	public double getMinPonuda() {
		return this.minPonuda;
	}

	public void setMinPonuda(double minPonuda) {
		this.minPonuda = minPonuda;
	}

	public String getNaslov() {
		return this.naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTekst() {
		return this.tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setOgla(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setOgla(null);

		return komentar;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Nakit getNakit() {
		return this.nakit;
	}

	public void setNakit(Nakit nakit) {
		this.nakit = nakit;
	}

	public List<Ponuda> getPonudas() {
		return this.ponudas;
	}

	public void setPonudas(List<Ponuda> ponudas) {
		this.ponudas = ponudas;
	}

	public Ponuda addPonuda(Ponuda ponuda) {
		getPonudas().add(ponuda);
		ponuda.setOgla(this);

		return ponuda;
	}

	public Ponuda removePonuda(Ponuda ponuda) {
		getPonudas().remove(ponuda);
		ponuda.setOgla(null);

		return ponuda;
	}
	
	public String printOglasToHTML() {
		String html = "<div class=\"widget-box sample-widget\">\n" + 
				"			<div class=\"widget-header\">\n" + 
				"					<h2>"+naslov+"</h2>\n" + 
				"					<i class=\"fa fa-cog\"></i>\n" + 
				"				</div>\n" +
				"				<p>"+ nakit.opisNakitaToHTML() +"</p><br>"+
				"               <h3>	Minimalna Ponuda: "+minPonuda+"</h3>"+
				"               <a href=\"https://www.w3schools.com\">Visit W3Schools</a>                                           "+
				"			<div class=\"widget-content\"></div>\n" + 
				"		</div>";
		return html;
	}

}