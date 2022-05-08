package com.model2.mvc.service.product;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.vo.ProductVO;

//상품관리 필수 행위 메소드 (interface) => 반드시 Overriding
public interface ProductService {
	
	public void addProduct(ProductVO productVO) throws Exception;

	public ProductVO getProduct(int prodNo) throws Exception;

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception;

	public void updateProduct(ProductVO productVO) throws Exception;
	
}