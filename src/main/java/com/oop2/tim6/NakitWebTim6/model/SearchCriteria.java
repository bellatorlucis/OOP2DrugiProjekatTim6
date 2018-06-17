package com.oop2.tim6.NakitWebTim6.model;

public class SearchCriteria {
	String column;
	String value;
	boolean isId;
	
	public SearchCriteria(String column, String value, boolean isId){
		this.column=column;
		this.value=value;
		this.isId = isId;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean isId() {
		return isId;
	}

	public void setId(boolean isId) {
		this.isId = isId;
	}

	@Override
	public String toString() {
		String valueFormated = "";
		if(isId) {
			valueFormated += " = "+ getValue();
		}
		else
		{
			valueFormated += " like '%"+this.getValue()+"%'";
		}
		
		return this.getColumn() + valueFormated;
	}
}