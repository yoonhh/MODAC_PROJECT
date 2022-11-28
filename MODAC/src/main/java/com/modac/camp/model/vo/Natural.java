package com.modac.camp.model.vo;

public class Natural {

	private String naturalNo;
	private String naturalType;
	private String naturalAttri;
	
	public Natural() {
		
	}

	public Natural(String naturalNo, String naturalType, String naturalAttri) {
		super();
		this.naturalNo = naturalNo;
		this.naturalType = naturalType;
		this.naturalAttri = naturalAttri;
	}

	public String getNaturalNo() {
		return naturalNo;
	}

	public void setNaturalNo(String naturalNo) {
		this.naturalNo = naturalNo;
	}

	public String getNaturalType() {
		return naturalType;
	}

	public void setNaturalType(String naturalType) {
		this.naturalType = naturalType;
	}

	public String getNaturalAttri() {
		return naturalAttri;
	}

	public void setNaturalAttri(String naturalAttri) {
		this.naturalAttri = naturalAttri;
	}

	@Override
	public String toString() {
		return "Natural [naturalNo=" + naturalNo + ", naturalType=" + naturalType + ", naturalAttri=" + naturalAttri
				+ "]";
	}
	
	
	
	
	
	
}
