package com.model2.mvc.service.product.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	@Autowired //연결
	@Qualifier("sqlSessionTemplate") //SqlSessionFactoryBean
	private SqlSession sqlSession; //캡슐화
	
//	public void setSqlSession(SqlSession sqlSession) {
//		this.sqlSession = sqlSession;
//	}
	
	public ProductDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(getClass().getName()+"실행");
	}

	@Override
	public int addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("ProductMapper.insertProduct", product);
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}

	@Override
	public List<Product> getProductList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("이곳은 전이다...");
		System.out.println("이곳???   " + sqlSession.selectList("ProductMapper.getProductList", map));
		return sqlSession.selectList("ProductMapper.getProductList", map);
	}

	@Override
	public int updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("ProductMapper.updateProduct", product);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductMapper.getTotalCount", map);
	}

}
