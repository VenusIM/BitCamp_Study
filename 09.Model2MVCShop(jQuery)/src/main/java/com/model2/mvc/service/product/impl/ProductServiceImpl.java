package com.model2.mvc.service.product.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.domain.Product;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	@Qualifier("productDaoImpl")
	ProductDao productDao;

	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(getClass().getName()+"»ý¼º");
	}

	@Override
	public int addProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return productDao.addProduct(product);
	}
	
	@Override
	public Product getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProduct(prodNo);
	}
	
	@Override
	public Map<String, Object> getProductList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		//Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", productDao.getProductList(map));
		map.put("totalCount", productDao.getTotalCount(map));

		return map;
	}
	
	@Override
	public int updateProduct(Product product) throws Exception {
		// TODO Auto-generated method stub
		return productDao.updateProduct(product);
	}
}
