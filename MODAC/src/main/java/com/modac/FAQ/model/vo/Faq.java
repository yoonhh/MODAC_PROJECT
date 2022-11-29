package com.modac.FAQ.model.vo;

public class Faq {

	private String faqNo;
	private String faqTitle;
	private String faqContent;
	private String faqWriter;
	
	public Faq() {
		super();
	}
	
	public Faq(String faqNo, String faqTitle, String faqContent, String faqWriter) {
		super();
		this.faqNo = faqNo;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
		this.faqWriter = faqWriter;
	}

	public Faq(String faqNo, String faqTitle, String faqContent) {
		super();
		this.faqNo = faqNo;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
	}
	
	public String getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(String faqNo) {
		this.faqNo = faqNo;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public String getFaqWriter() {
		return faqWriter;
	}

	public void setFaqWriter(String faqWriter) {
		this.faqWriter = faqWriter;
	}

	@Override
	public String toString() {
		return "Faq [faqNo=" + faqNo + ", faqTitle=" + faqTitle + ", faqContent=" + faqContent + ", faqWriter="
				+ faqWriter + "]";
	}
	
	
	
}
