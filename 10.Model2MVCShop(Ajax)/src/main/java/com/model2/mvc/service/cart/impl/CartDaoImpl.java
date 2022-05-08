package com.model2.mvc.service.cart.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.cart.CartDao;
import com.model2.mvc.service.domain.Cart;

@Repository("cartDaoImpl")
public class CartDaoImpl implements CartDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public CartDaoImpl(SqlSession sqlSession) {
		System.out.println(getClass().getName()+"½ÇÇà");
	}

	@Override
	public int addCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("CartMapper.insertCart", cart);
	}

	@Override
	public Cart getCart(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("CartMapper.getCart", map);
	}

	@Override
	public List<Cart> getCartList(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("CartMapper.getCartList", userId);
	}

	@Override
	public int updateCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("CartMapper.updateCart", cart);
	}

	@Override
	public int deleteCart(Cart cart) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("CartMapper.deleteCart", cart);
	}

	@Override
	public int getTotalCount(String userId) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("CartMapper.getTotalCount", userId);
	}
}
