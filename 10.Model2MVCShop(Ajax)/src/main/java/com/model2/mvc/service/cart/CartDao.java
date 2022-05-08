package com.model2.mvc.service.cart;

import java.util.List;
import java.util.Map;

import com.model2.mvc.service.domain.Cart;

public interface CartDao {
	
	public int addCart(Cart cart) throws Exception;
	
	public Cart getCart(Map<String, Object> map) throws Exception;
	
	public List<Cart> getCartList(String userId) throws Exception;
	
	public int updateCart(Cart cart) throws Exception;
	
	public int deleteCart(Cart cart) throws Exception;
	
	public int getTotalCount(String userId) throws Exception;
}
