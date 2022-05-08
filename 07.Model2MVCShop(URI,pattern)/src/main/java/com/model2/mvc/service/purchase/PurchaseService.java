package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.service.domain.Purchase;

//구매관리 필수 행위 메소드 (interface) => 반드시 Overriding
public interface PurchaseService {

	public int addPurchase(Purchase purchaseVO) throws Exception;
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public Purchase getPurchase2(int prodNo) throws Exception;
	
	public Map<String,Object> getPurchaseList(Map<String, Object> map) throws Exception;
	
	public Map<String,Object> getSaleList(Map<String, Object> map) throws Exception;
	
	public int updatePurcahse(Purchase purchase) throws Exception;
	
	public int updateTranCode(Purchase purchase) throws Exception;
	
}