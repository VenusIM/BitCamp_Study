package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.product.dao.ProductDao;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDao;

public class PurchaseServlceImpl implements PurchaseService {
	
	PurchaseDao purchaseDao = null;
	ProductDao productDAO = null;

	public PurchaseServlceImpl() {
		// TODO Auto-generated constructor stub
		purchaseDao = new PurchaseDao();
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		purchaseDao.insertPurchase(purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.findPurchase(tranNo);
	}

	@Override
	public Purchase getPurchase2(int ProdNo) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.findPurchase(ProdNo);
	}

	
	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.getPurchaseList(search, buyerId);
	}

	@Override
	public Map<String, Object> getSaleList(Search search) throws Exception {
		// TODO Auto-generated method stub
		return purchaseDao.getSaleList(search);
	}

	@Override
	public void updatePurcahse(Purchase purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		purchaseDao.updatePurchase(purchaseVO);
	}

	@Override
	public void updateTranCode(Purchase purchaseVO) throws Exception {
		// TODO Auto-generated method stub
		purchaseDao.updateTranCode(purchaseVO);
	}
}
