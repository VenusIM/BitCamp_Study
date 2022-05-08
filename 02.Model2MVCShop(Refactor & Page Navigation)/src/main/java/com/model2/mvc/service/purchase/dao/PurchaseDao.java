package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDao {

	public PurchaseDao() {
		// TODO Auto-generated constructor stub
	}

	public Purchase findPurchase(int no) throws Exception {
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
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = new User();
		if (rs.next()) {
			purchase.setTranNo(rs.getInt(1));
			product.setProdNo(rs.getInt(2));
			user.setUserId(rs.getString(3));
			purchase.setPaymentOption(rs.getString(4));
			purchase.setReceiverName(rs.getString(5));
			purchase.setReceiverPhone(rs.getString(6));
			purchase.setDivyAddr(rs.getString(7));
			purchase.setDivyRequest(rs.getString(8));
			purchase.setTranCode(CommonUtil.null2str(rs.getString(9)));
			purchase.setOrderDate(rs.getDate(10));
			purchase.setDivyDate(CommonUtil.null2str(rs.getString(11)));
	
			purchase.setPurchaseProd(product);
			purchase.setBuyer(user);
		}
		
		pStmt.close();
		rs.close();
		conn.close();
		
		return purchase;
	}

	public Map<String, Object> getPurchaseList(Search search, String buyerId) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<Purchase> list = new ArrayList<Purchase>();
		
		String sql = "SELECT * "
					 + "FROM transaction "
					+ "WHERE buyer_id = '"+ buyerId +"'";
		
		System.out.println("getPurchaseList sql :: " + sql);
		
		try {
			System.out.println(search);
			int totalCount = getTotalCount(sql);
			sql = makeCurrentPageSql(sql, search);
			
			conn = DBUtil.getConnection();
			pStmt = conn.prepareStatement(sql);
			
			rs = pStmt.executeQuery();

			while (rs.next()) {
				Purchase purchase = new Purchase();
				Product product = new Product();
				User user = new User();
				
				purchase.setTranNo(rs.getInt(1));
				product.setProdNo(rs.getInt(2));
				user.setUserId(rs.getString(3));
				purchase.setPaymentOption(rs.getString(4));
				purchase.setReceiverName(rs.getString(5));
				purchase.setReceiverPhone(rs.getString(6));
				purchase.setDivyAddr(rs.getString(7));
				purchase.setDivyRequest(rs.getString(8));
				purchase.setTranCode(CommonUtil.null2str(rs.getString(9)));
				purchase.setOrderDate(rs.getDate(10));
				purchase.setDivyDate(rs.getString(11));
				
				purchase.setPurchaseProd(product);
				purchase.setBuyer(user);
				
				list.add(purchase);
			}
			
			map.put("list", list);
			map.put("totalCount", totalCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (pStmt != null) {
					pStmt.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return map;
	}

	public Map<String, Object> getSaleList(Search search) {
		return null;
	}

	public void insertPurchase(Purchase purchase) throws Exception {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStmt = conn.prepareStatement("INSERT INTO transaction VALUES(seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)");
		
		pStmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		pStmt.setString(2, purchase.getBuyer().getUserId());
		pStmt.setString(3, purchase.getPaymentOption());
		pStmt.setString(4, purchase.getReceiverName());
		pStmt.setString(5, purchase.getReceiverPhone());
		pStmt.setString(6, purchase.getDivyAddr());
		pStmt.setString(7, purchase.getDivyRequest());
		pStmt.setString(8, purchase.getTranCode());
		pStmt.setDate(9, null);
		//Date date = Date.valueOf(purchase.getDivyDate());
		System.out.println(purchase.getDivyDate()+"Divy");
		if (!"".equals(purchase.getDivyDate())) {
			pStmt.setDate(9, Date.valueOf(purchase.getDivyDate()));
		} 
		
		if (pStmt.executeUpdate() == 1) {
			System.out.println("구매등록 성공");
		} else {
			System.out.println("구매등록 실패");			
		}
		
		pStmt.close();
		conn.close();
	}
	
	public void updatePurchase(Purchase purchase) throws Exception {
		String sql = "UPDATE transaction "
								+ "SET payment_option=?, receiver_name=?, receiver_phone=?, "
										+ "demailaddr=?, dlvy_request=?, dlvy_date=? "
						  + "WHERE tran_no=?";
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStmt = conn.prepareStatement(sql);
		System.out.println("update vo ::" + purchase);
		pStmt.setString(1, purchase.getPaymentOption());
		pStmt.setString(2, purchase.getReceiverName());
		pStmt.setString(3, purchase.getReceiverPhone());
		pStmt.setString(4, purchase.getDivyAddr());
		pStmt.setString(5, purchase.getDivyRequest());
		pStmt.setString(6, purchase.getDivyDate());
		pStmt.setInt(7, purchase.getTranNo());
		
		if (pStmt.executeUpdate() == 1) {
			System.out.println("Purchase update 성공");
		} else {
			System.out.println("Purchase update 실패");		
		}
		pStmt.close();
		conn.close();
	}
	
	public void updateTranCode(Purchase purchase) throws Exception {
		Connection conn = DBUtil.getConnection();
		System.out.println("왜 안돼~~"+purchase.getTranCode()+" "+purchase.getPurchaseProd().getProdNo());	
		PreparedStatement pStmt = conn.prepareStatement("UPDATE transaction "
														+ "SET tran_status_code=? "
														+ "WHERE prod_no=?");
		
		pStmt.setString(1, purchase.getTranCode());
		pStmt.setInt(2, purchase.getPurchaseProd().getProdNo());

		if (pStmt.executeUpdate() == 1) {
			System.out.println("TranCode 업데이트 성공");
		} else {
			System.out.println("TranCode 업데이트 실패");			
		}
	}
	
	private int getTotalCount(String sql) throws Exception {
		sql = "SELECT COUNT(*) "
			  + "FROM ("+ sql +") countTable";
		
		System.out.println("getTotalCount SQL :: " + sql);
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement pStmt = conn.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if (rs.next()) {
			totalCount = rs.getInt(1);
			System.out.println("totalCount :: " + totalCount);
		}
		
		rs.close();
		pStmt.close();
		conn.close();
	
		return totalCount;
	}
	
	private String makeCurrentPageSql(String sql , Search search) {
		sql = "SELECT * "
			  + "FROM (SELECT inner_table.*, ROWNUM row_seq "
			  		+ "FROM ("+ sql +") inner_table "
			  		+ "WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize() +") "
			 + "WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("PurchaseDao :: make SQL :: "+ sql);	
		
		return sql;
	}
}
