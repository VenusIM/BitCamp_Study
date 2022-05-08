package com.model2.mvc.service.cart;

import java.util.Map;

import com.model2.mvc.service.domain.Cart;

//회원 장바구니 행위 메소드
public interface CartService {
	
	public int addCart(Cart cart) throws Exception;
	
	public Cart getCart(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> getCartList(String userId) throws Exception;
	
	public int updateCart(Cart cart) throws Exception;
	
	public int deleteCart(Cart cart) throws Exception;
}
