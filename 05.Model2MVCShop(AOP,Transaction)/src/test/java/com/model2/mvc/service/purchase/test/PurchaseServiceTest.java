package com.model2.mvc.service.purchase.test;

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
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/*.xml"})
public class PurchaseServiceTest {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = new User();
		
		product.setProdNo(10002);
		purchase.setPurchaseProd(product);
		user.setUserId("user12");
		purchase.setBuyer(user);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("회원");
		purchase.setDivyAddr("종각");
		purchase.setTranCode("1");
		purchase.setMemberCheck("0");
		
		System.out.println("insert 결과 :: " + purchaseService.addPurchase(purchase));
	}
	
	//@Test
	public void testUpdatePurchase() throws Exception {
		Purchase purchase = new Purchase();
		purchase.setTranNo(10000);
		purchase.setPaymentOption("1");
		purchase.setReceiverPhone("000-1234-4567");
		purchase.setDivyRequest("조심히");
		purchase.setDivyDate("20220101");
		purchase.setMemberCheck("0");
		
		System.out.println("update 결과 :: " + purchaseService.updatePurcahse(purchase));
	}
	
	//@Test
	public void testUpdateTranCode() throws Exception {
		Purchase purchase = new Purchase();
		Product product = new Product();
		
		product.setProdNo(10002);
		purchase.setPurchaseProd(product);
		purchase.setTranCode("2");
			
		System.out.println("update 결과 :: " + purchaseService.updateTranCode(purchase));
	}
	
	//@Test
	public void testGetPurchaseTranNo() throws Exception {
		Purchase purchase = new Purchase();
		purchase.setTranNo(10000);
	
		System.out.println(purchaseService.getPurchase(purchase.getTranNo()));
	}
	
	//@Test
	public void testGetPurchaseProdNo() throws Exception {
		Purchase purchase = new Purchase();
		Product product = new Product();
		product.setProdNo(10002);
		purchase.setPurchaseProd(product);
				
		System.out.println(purchaseService.getPurchase2(purchase.getPurchaseProd().getProdNo()));
	}
	
	@Test
	public void testGetPurchaseList() throws Exception {
		Purchase purchase = new Purchase();
		Search search = new Search();
		User user = new User();
		
		//user.setUserId("user12");
		purchase.setBuyer(user);
		purchase.setReceiverName("비회원");
		purchase.setReceiverPhone("010-111-2222");
		search.setCurrentPage(1);
		search.setPageSize(3);
		
		System.out.println("listPurchase :: "+purchase);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("purchase", purchase);
		
		purchaseService.getPurchaseList(map);
		
		List<Purchase> list = new ArrayList<Purchase>();
		list = (List<Purchase>)map.get("list");
		
		int totalCount = (int)map.get("totalCount");
		
		System.out.println("totalCount :: " + totalCount);
		
		for (Object ob:list) {
			System.out.println(ob);
		}
		
		if (totalCount == 0) {
			System.out.println("정보가 없습니다. 다시 검색해주세요.");
		}
	}
}
