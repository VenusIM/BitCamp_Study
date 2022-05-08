package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	public PurchaseDAO() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseVO findPurchase(int no) throws Exception {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStmt = null;
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		StackTraceElement beforeStack = stacks[1];
		System.out.println("실행한 메소드 이름 :: " + beforeStack.getMethodName());
		
		String sql = "SELECT * FROM transaction ";
		
		if (beforeStack.getMethodName().contains("2")) {
			sql += "WHERE prod_no = '" + no +"'";
		} else {			
			sql += "WHERE tran_no = '" + no +"'";
		}
		
		System.out.println("findPurchase sql 확인 :: " + sql);
		pStmt = conn.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		PurchaseVO purchaseVO = new PurchaseVO();
		ProductVO productVO = new ProductVO();
		UserVO userVO = new UserVO();
		if (rs.next()) {
			purchaseVO.setTranNo(rs.getInt(1));
			productVO.setProdNo(rs.getInt(2));
			userVO.setUserId(rs.getString(3));
			purchaseVO.setPaymentOption(rs.getString(4));
			purchaseVO.setReceiverName(rs.getString(5));
			purchaseVO.setReceiverPhone(rs.getString(6));
			purchaseVO.setDivyAddr(rs.getString(7));
			purchaseVO.setDivyRequest(rs.getString(8));
			purchaseVO.setTranCode(rs.getString(9).trim());
			purchaseVO.setOrderDate(rs.getDate(10));
			
			if (rs.getString(11) != null) {
				purchaseVO.setDivyDate(rs.getString(11));
			} else {
				purchaseVO.setDivyDate("");
			}
			
			purchaseVO.setPurchaseProd(productVO);
			purchaseVO.setBuyer(userVO);
		}
		
		pStmt.close();
		rs.close();
		conn.close();
		
		return purchaseVO;
	}

	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		String sql = "SELECT * FROM transaction "
						+ "WHERE buyer_id = ?";
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		pStmt.setString(1, buyerId);
		
		ResultSet rs = pStmt.executeQuery();
		rs.last();
		
		int total = rs.getRow(); //총 row 수
		System.out.println(total + " :: total PurchaseDAO");
		
		map.put("count", new Integer(total));
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		List<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i<searchVO.getPageUnit(); i++) {
				PurchaseVO purchaseVO = new PurchaseVO();
				ProductVO productVO = new ProductVO();
				UserVO userVO = new UserVO();
				purchaseVO.setTranNo(rs.getInt(1));
				productVO.setProdNo(rs.getInt(2));
				userVO.setUserId(rs.getString(3));
				purchaseVO.setPaymentOption(rs.getString(4));
				purchaseVO.setReceiverName(rs.getString(5));
				purchaseVO.setReceiverPhone(rs.getString(6));
				purchaseVO.setDivyAddr(rs.getString(7));
				purchaseVO.setDivyRequest(rs.getString(8));
				purchaseVO.setTranCode(rs.getString(9).trim());
				purchaseVO.setOrderDate(rs.getDate(10));
				purchaseVO.setDivyDate(rs.getString(11));
				
				purchaseVO.setBuyer(userVO);
				purchaseVO.setPurchaseProd(productVO);
				
				list.add(purchaseVO);
				if (!rs.next()) {
					break;
				}
			}
		}
		
		map.put("list", list);
		System.out.println("purchase list :: " + map.get("list"));
		
		rs.close();
		pStmt.close();
		conn.close();
		
		return map;
	}

	
	public HashMap<String, Object> getSaleList(SearchVO searchVO) {
		return null;
	}

	
	public void insertPurchase(PurchaseVO purchaseVO) throws Exception {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStmt = conn.prepareStatement("INSERT INTO transaction VALUES(seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)");
		
		pStmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		pStmt.setString(2, purchaseVO.getBuyer().getUserId());
		pStmt.setString(3, purchaseVO.getPaymentOption());
		pStmt.setString(4, purchaseVO.getReceiverName());
		pStmt.setString(5, purchaseVO.getReceiverPhone());
		pStmt.setString(6, purchaseVO.getDivyAddr());
		pStmt.setString(7, purchaseVO.getDivyRequest());
		pStmt.setString(8, purchaseVO.getTranCode());
		pStmt.setDate(9, null);
		//Date date = Date.valueOf(purchaseVO.getDivyDate());
		System.out.println(purchaseVO.getDivyDate()+"Divy");
		if (!"".equals(purchaseVO.getDivyDate())) {
			pStmt.setDate(9, Date.valueOf(purchaseVO.getDivyDate()));
		} 
		
		if (pStmt.executeUpdate() == 1) {
			System.out.println("구매등록 성공");
		} else {
			System.out.println("구매등록 실패");			
		}
		
		pStmt.close();
		conn.close();
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {
		String sql = "UPDATE transaction "
								+ "SET payment_option=?, receiver_name=?, receiver_phone=?, "
										+ "demailaddr=?, dlvy_request=?, dlvy_date=? "
						  + "WHERE tran_no=?";
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStmt = conn.prepareStatement(sql);
		System.out.println("update vo ::" + purchaseVO);
		pStmt.setString(1, purchaseVO.getPaymentOption());
		pStmt.setString(2, purchaseVO.getReceiverName());
		pStmt.setString(3, purchaseVO.getReceiverPhone());
		pStmt.setString(4, purchaseVO.getDivyAddr());
		pStmt.setString(5, purchaseVO.getDivyRequest());
		pStmt.setString(6, purchaseVO.getDivyDate());
		pStmt.setInt(7, purchaseVO.getTranNo());
		
		if (pStmt.executeUpdate() == 1) {
			System.out.println("Purchase update 성공");
			//purchaseVO = findPurchase(purchaseVO.getTranNo()); //==> 새로운 주소값을 참조
			//System.out.println("성공 :: " + purchaseVO);
		} else {
			System.out.println("Purchase update 실패");		
		}
		pStmt.close();
		conn.close();
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		Connection conn = DBUtil.getConnection();
		System.out.println(purchaseVO.getTranCode()+" "+purchaseVO.getPurchaseProd().getProdNo());	
		PreparedStatement pStmt = conn.prepareStatement("UPDATE transaction "
														+ "SET tran_status_code=? "
														+ "WHERE prod_no=?");
		
		pStmt.setString(1, purchaseVO.getTranCode());
		pStmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());

		if (pStmt.executeUpdate() == 1) {
			System.out.println("TranCode 업데이트 성공");
		} else {
			System.out.println("TranCode 업데이트 실패");			
		}
		
		pStmt.close();
		conn.close();
	}
}
