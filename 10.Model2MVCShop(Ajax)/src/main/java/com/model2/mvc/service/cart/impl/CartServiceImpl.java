package com.model2.mvc.service.cart.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.cart.CartDao;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.domain.Cart;

@Service("cartServiceImpl")
public class CartServiceImpl implements CartService {
	
	@Autowired
	@Qualifier("cartDaoImpl")
	private CartDao cartDao;

	@Override
	public int addCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		return cartDao.addCart(cart);
	}
	
	@Override
	public Cart getCart(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return cartDao.getCart(map);
	}

	@Override
	public Map<String, Object> getCartList(String userId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", cartDao.getCartList(userId));
		map.put("totalCount", cartDao.getTotalCount(userId));
		
		return map;
	}

	//수량 변경 시 사용???
	@Override
	public int updateCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		return cartDao.updateCart(cart);
	}

	@Override
	public int deleteCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		return cartDao.deleteCart(cart);
	}

}
