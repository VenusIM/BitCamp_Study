package com.model2.mvc.service.domain;

import java.sql.Date;

import com.model2.mvc.common.util.CommonUtil;

//상품정보 관리 객체 => Information Hiding(getter, setter)
public class Product {
	
	private String fileName;
	private String manuDate;
	private int price;
	private String prodDetail;
	private String prodName;
	private int prodNo;
	private Date regDate;
	private String proTranCode;
	
	// JSON ==> Domain Object  Binding을 위해 추가된 부분
	private String regDateString;
	
	public Product(){
	}
	
	public String getProTranCode() {
		return proTranCode;
	}
	public void setProTranCode(String proTranCode) {
		this.proTranCode = CommonUtil.null2str(proTranCode);
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getManuDate() {
		return manuDate;
	}
	public void setManuDate(String manuDate) {
		this.manuDate = manuDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProdDetail() {
		return prodDetail;
	}
	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
		
		if (regDate != null) {
			this.setRegDateString( regDate.toString().split("-")[0]
					+"-"+ regDate.toString().split("-")[1]
					+ "-" +regDate.toString().split("-")[2] );
		}
	}
	
	public String getRegDateString() {
		return regDateString;
	}

	public void setRegDateString(String regDateString) {
		this.regDateString = regDateString;
	}

	@Override
	public String toString() {
		return "Product [fileName=" + fileName + ", manuDate=" + manuDate + ", price=" + price + ", prodDetail="
				+ prodDetail + ", prodName=" + prodName + ", prodNo=" + prodNo + ", regDate=" + regDate
				+ ", proTranCode=" + proTranCode + ", regDateString=" + regDateString + "]";
	}
}