package com.oop2.tim6.NakitWebTim6.model;

import java.util.ArrayList;
import java.util.List;

import com.oop2.tim6.NakitWebTim6.model.Tip;

public class OglasSearchDto {
	private int izabranTip;
	private String boja;
	private String materijal;
	private String naslov;

	public OglasSearchDto(int izabranTip, String boja, String materijal, String naslov) {
		this.izabranTip = izabranTip;
		this.boja = boja;
		this.materijal = materijal;
		this.naslov = naslov;
	}

	public int getIzabranTip() {
		return izabranTip;
	}

	public void setIzabranTip(int izabranTip) {
		this.izabranTip = izabranTip;
	}

	public String getBoja() {
		return boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}

	public String getMaterijal() {
		return materijal;
	}

	public void setMaterijal(String materijal) {
		this.materijal = materijal;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String generateQueryExtensionForOglas() {
		return generateQueryFromSearchCriteria(getSearchCriterias());
	}

	private String generateQueryFromSearchCriteria(List<SearchCriteria> searchCriterias) {
		if(searchCriterias.isEmpty()) {
			return "";
		}
		
		SearchCriteria lastSearchCriteria = searchCriterias.remove(searchCriterias.size() - 1);
		String query = " and ";
		for (SearchCriteria sc : searchCriterias) {
			query += "o." + sc.toString() + " and ";
		}

		query += lastSearchCriteria.toString();
		return query;
	}

	private List<SearchCriteria> getSearchCriterias() {
		List<SearchCriteria> searchCriteria = new ArrayList<SearchCriteria>();
		if (this.boja != null && !this.boja.isEmpty()) {
			searchCriteria.add(new SearchCriteria(SearchOption.BOJA, boja));
		}

		if (this.naslov != null && !this.naslov.isEmpty()) {
			searchCriteria.add(new SearchCriteria(SearchOption.NASLOV, naslov));
		}

		if (this.materijal != null && !this.materijal.isEmpty()) {
			searchCriteria.add(new SearchCriteria(SearchOption.MATERIJAL, materijal));
		}

		if (this.izabranTip != 0) {
			searchCriteria.add(new SearchCriteria(SearchOption.ID_TIPA, "" + izabranTip));
		}

		return searchCriteria;
	}

	private class SearchCriteria {
		SearchOption searchOption;
		String value;

		protected SearchCriteria(SearchOption searchOption, String value) {
			this.searchOption = searchOption;
			this.value = value;
		}

		@Override
		public String toString() {
			String valueFormated = "";
			if (searchOption.isId()) {
				valueFormated += " = " + this.value;
			} else {
				valueFormated += " like '%" + this.value + "%'";
			}

			return searchOption.getColumn() + valueFormated;
		}
	}
	
	private enum SearchOption {
		ID_TIPA("nakit.tip.idTipa", true), BOJA("nakit.boja", false), MATERIJAL("nakit.materijal", false), NASLOV("naslov", false);

		private String column;
		private boolean isId;

		SearchOption(String column, boolean isId) {
			this.column = column;
			this.isId = isId;
		}

		String getColumn() {
			return column;
		}

		boolean isId() {
			return isId;
		}
	}
}
