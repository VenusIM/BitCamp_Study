package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDao;

	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
		productDao = new ProductDAO();
	}

	@Override
	public void addProduct(ProductVO productVO) throws Exception {
		// TODO Auto-generated method stub
		productDao.insertProduct(productVO);
	}
	
	@Override
	public ProductVO getProduct(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return productDao.findProduct(prodNo);
	}
	
	@Override
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getProductList(searchVO);
	}
	
	@Override
	public void updateProduct(ProductVO productVO) throws Exception {
		// TODO Auto-generated method stub
		productDao.updateProduct(productVO);
	}
}
