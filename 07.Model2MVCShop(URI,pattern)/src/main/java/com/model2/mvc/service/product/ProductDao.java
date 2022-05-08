package com.model2.mvc.service.product;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductDao {
	public int addProduct(Product product) throws Exception ;
	
	public Product getProduct(int prodNo) throws Exception;
	
	public List<Product> getProductList(Map<String, Object> map) throws Exception;
	
	public int updateProduct(Product product) throws Exception;
	
	public int getTotalCount(Map<String, Object> map) throws Exception;
}
