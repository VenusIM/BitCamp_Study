package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;

public class ProductDAO {

	Connection conn;

	public ProductDAO() {
		// TODO Auto-generated constructor stub
		conn = DBUtil.getConnection();
	}

	//상품 검색 
	public ProductVO findProduct(int prodNo) throws Exception {
		ProductVO productVO = new ProductVO();
		
		PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM product "
																								+ "WHERE prod_no = ?");
		
		pStmt.setInt(1, prodNo);
		ResultSet rs = pStmt.executeQuery();
		
		if (rs.next()) {
			productVO.setProdNo(rs.getInt(1));
			productVO.setProdName(rs.getString(2));
			productVO.setProdDetail(rs.getString(3));
			productVO.setManuDate(rs.getString(4));
			productVO.setPrice(rs.getInt(5));
			productVO.setFileName(rs.getString(6));
			productVO.setRegDate(rs.getDate(7));
		}
		
		conn.close();
		pStmt.close();
		
		return productVO;
	}

	public HashMap<String, Object> getProductList(SearchVO searchVO) {
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		String sql = "SELECT * FROM product p, transaction t WHERE ";
		
		try {	
			if (searchVO.getSearchCondition() != null) {
				if (searchVO.getSearchCondition().equals("0")) {
					sql += "p.prod_no LIKE '%" + searchVO.getSearchKeyword()
							+ "%' AND ";
				} else if (searchVO.getSearchCondition().equals("1")) {
					sql += "p.prod_name LIKE '%" + searchVO.getSearchKeyword()
							+ "%' AND ";
				} else if (searchVO.getSearchCondition().equals("2")) {
					sql += "p.price LIKE '%" + searchVO.getSearchKeyword()
					+ "%' AND ";
				}
			}
			sql += "p.prod_no = t.prod_no(+) order by p.prod_no";
			
			pStmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pStmt.executeQuery();
			
			rs.last();
			int total = rs.getRow(); 
			System.out.println(total + " :: total ProductDAO");
			
			hashMap.put("count", new Integer(total));
			
			rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
			System.out.println("searchVO.getPage():" + searchVO.getPage());
			System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
			
			//회원정보 List가 존재한다면 정보 저장
			ArrayList<ProductVO> list = new ArrayList<ProductVO>();
			if (total > 0) {
				for (int i = 0; i < searchVO.getPageUnit(); i++) {
					ProductVO vo = new ProductVO();
					vo.setProdNo(rs.getInt("prod_no"));
					vo.setProdName(rs.getString("prod_name"));
					vo.setProdDetail(rs.getString("prod_detail"));
					vo.setManuDate(rs.getString("manufacture_day"));
					vo.setPrice(rs.getInt("price"));
					vo.setFileName(rs.getString("image_file"));
					vo.setRegDate(rs.getDate("REG_DATE"));
					
					if (rs.getString("tran_status_code") != null) {
						vo.setProTranCode(rs.getString("tran_status_code").trim());
					} else {
						vo.setProTranCode(rs.getString("tran_status_code"));
					}
					
					list.add(vo);
					if (!rs.next())
						break;
				}
			}
			System.out.println("list.size() : "+ list.size());
			hashMap.put("list", list);
			System.out.println("map().size() : "+ hashMap.size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null) {
					pStmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return hashMap;
	}

	public void insertProduct(ProductVO productVO) {
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(
					"INSERT INTO product VALUES (seq_product_prod_no.nextval, ?, ?, ?, ?, ?, sysdate)");
			pStmt.setString(1, productVO.getProdName());
			pStmt.setString(2, productVO.getProdDetail());
			pStmt.setString(3, productVO.getManuDate());
			pStmt.setInt(4, productVO.getPrice());
			pStmt.setString(5, productVO.getFileName());

			if (pStmt.executeUpdate() == 1) {
				System.out.println("상품등록 완료");
			} else {
				System.out.println("상품등록 실패");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pStmt != null) {
					pStmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateProduct(ProductVO productVO) throws Exception {
		String sql = "UPDATE product "
						+ "SET prod_name=?, prod_detail=?, "
						+ "manufacture_day=?, price=?, image_file=? "
						+ "WHERE prod_no=?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		pStmt.setString(1, productVO.getProdName());
		pStmt.setString(2, productVO.getProdDetail());
		pStmt.setString(3, productVO.getManuDate());
		pStmt.setInt(4, productVO.getPrice());
		pStmt.setString(5, productVO.getFileName());
		pStmt.setInt(6, productVO.getProdNo());
		
		if (pStmt.executeUpdate() == 1) {
			System.out.println("product 업데이트 성공 :: "+productVO);
		}
		
		pStmt.close();
		conn.close();
	}
}
