package com.model2.mvc.service.product.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/*.xml"})
public class ProductServiceTest {
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//@Test
	public void testAddProduct() throws Exception {
		Product product = new Product();
		product.setProdName("테스트");
		product.setProdDetail("테스트입니다.");
		//product.setPrice(1000);
		product.setManuDate("20210521");
		//product.setFileName(null);
		
		System.out.println("insert 결과 :: " + productService.addProduct(product));
		
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		Product product = new Product();
		product.setProdNo(10020);
		
		System.out.println(productService.getProduct(product.getProdNo()));
	}
	
	@Test
	public void testGetProductList() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("100");
		String order = "0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("order", order);
		
		//map = (Map<String, Object>) productService.getProductList(search);
		productService.getProductList(map);
		
		List<Product> list = new ArrayList<Product>();
		list = (List<Product>) map.get("list");
		
		int totalCount = (int) map.get("totalCount");
		
		System.out.println("totalCount :: " + totalCount);
		
		for (Object ob:list) {
			System.out.println(ob);
		}
	}
	
	//@Test
	public void testUpdateProduct() throws Exception {
		Product product = new Product();
		product.setProdName("테스트업뎃");
		product.setProdDetail("테스트를 업데이트했습니다.");
		product.setPrice(2000);
		product.setManuDate("20210718");
		product.setFileName("없음");
		product.setProdNo(10020);
		
		System.out.println("update 결과 :: " + productService.updateProduct(product));
		testGetProduct();
	}
}
