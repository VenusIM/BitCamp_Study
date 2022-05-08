package com.model2.mvc.service.purchase.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDao;

@Repository("purchaseDaoImpl")
public class PurchaseDaoImpl implements PurchaseDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public PurchaseDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addPurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("PurchaseMapper.insertPurchase", purchase);
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getPurchaseTranNo",tranNo);
	}

	@Override
	public Purchase getPurchase2(int prodNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getPurchaseProdNo",prodNo);
	}

	@Override
	public List<Purchase> getPurchaseList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("PurchaseMapper.getPurchaseList", map);
	}

	@Override
	public List<Purchase> getSaleList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("PurchaseMapper.getSaleList", map);
	}

	@Override
	public int updatePurchase(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("PurchaseMapper.updatePurchase", purchase);
	}

	@Override
	public int getTotalCount(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("PurchaseMapper.getTotalCount", map);
	}

	@Override
	public int updateTranCode(Purchase purchase) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("PurchaseMapper.updateTranCode", purchase);
	}


}
