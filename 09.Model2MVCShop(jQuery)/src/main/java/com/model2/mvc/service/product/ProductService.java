package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.service.domain.Product;

//��ǰ���� �ʼ� ���� �޼ҵ� (interface) => �ݵ�� Overriding
public interface ProductService {
	
	public int addProduct(Product product) throws Exception;

	public Product getProduct(int prodNo) throws Exception;

	public Map<String,Object> getProductList(Map<String, Object> map) throws Exception;

	public int updateProduct(Product productVO) throws Exception;
	
}