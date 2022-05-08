package com.model2.mvc.service.domain;


//备概沥焊 包府 按眉 => Information Hiding(getter, setter)
public class Cart {
	
	private User cartUser;
	private Product cartProduct;
	private int cartNo;
	private int amount=1;
	
	public Cart(){
	}

	public User getCartUser() {
		return cartUser;
	}

	public Product getCartProduct() {
		return cartProduct;
	}

	public int getCartNo() {
		return cartNo;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setCartUser(User cartUser) {
		this.cartUser = cartUser;
	}

	public void setCartProduct(Product cartProduct) {
		this.cartProduct = cartProduct;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Cart [cartUser=" + cartUser + ", cartProduct=" + cartProduct + ", cartNo=" + cartNo + ", amount="
				+ amount + "]";
	}
}