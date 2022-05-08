package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

//상품관리 필수 행위 메소드 (interface) => 반드시 Overriding
public interface ProductService {
	
	public void addProduct(Product product) throws Exception;

	public Product getProduct(int prodNo) throws Exception;

	public Map<String,Object> getProductList(Search searchVO) throws Exception;

	public void updateProduct(Product productVO) throws Exception;
	
}