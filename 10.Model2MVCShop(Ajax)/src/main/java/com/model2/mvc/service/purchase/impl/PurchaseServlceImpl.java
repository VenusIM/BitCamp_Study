package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;

@Service("purchaseServiceImpl")
public class PurchaseServlceImpl implements PurchaseService {
	
	@Autowired
	@Qualifier("purchaseDaoImpl")
	PurchaseDao purchaseDao = null;
	
	@Autowired
	@Qualifier("productDaoImpl")
	ProductDao productDAO = null;

	public PurchaseServlceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.addPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.getPurchase(tranNo);
	}

	@Override
	public Purchase getPurchase2(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.getPurchase2(prodNo);
	}

	
	@Override
	public Map<String, Object> getPurchaseList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		map.put("list", purchaseDao.getPurchaseList(map));
		map.put("totalCount", purchaseDao.getTotalCount(map));
		
		return map;
	}

	@Override
	public Map<String, Object> getSaleList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		map.put("saleList", purchaseDao.getSaleList(map));
		map.put("totalCount", purchaseDao.getTotalCount(map));
		
		return map;
	}

	@Override
	public int updatePurcahse(Purchase purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.updatePurchase(purchaseVO);
	}

	@Override
	public int updateTranCode(Purchase purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.updateTranCode(purchaseVO);
	}

	
}
