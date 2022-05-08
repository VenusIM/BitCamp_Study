package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.CommonUtil;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;

public class ProductDao {

	Connection conn;

	public ProductDao() {
		// TODO Auto-generated constructor stub
		conn = DBUtil.getConnection();
	}

	//상품 검색 
	public Product findProduct(int prodNo) throws Exception {
		Product Product = new Product();
		
		PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM product "
													   + "WHERE prod_no = ?");
		pStmt.setInt(1, prodNo);
		ResultSet rs = pStmt.executeQuery();
		
		if (rs.next()) {
			Product.setProdNo(rs.getInt(1));
			Product.setProdName(rs.getString(2));
			Product.setProdDetail(rs.getString(3));
			Product.setManuDate(rs.getString(4));
			Product.setPrice(rs.getInt(5));
			Product.setFileName(rs.getString(6));
			Product.setRegDate(rs.getDate(7));
		}
		
		conn.close();
		pStmt.close();
		
		return Product;
	}

	public Map<String, Object> getProductList(Search search) {
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		Map<String, Object> hashMap = new HashMap<String, Object>();
		String sql = "SELECT p.prod_no, p.prod_name, p.prod_detail, "
							+ "p.manufacture_day, p.price, p.image_file, "
							+ "p.REG_DATE, t.tran_status_code "
					+ "FROM product p, transaction t "
					+ "WHERE";
		
		try {	
			if (search.getSearchCondition() != null) {
				if (!"".equals(search.getSearchKeyword())) {
					if (search.getSearchCondition().equals("0")) {
						sql += " p.prod_no LIKE '%" + search.getSearchKeyword()
								+ "%' AND";
					} else if (search.getSearchCondition().equals("1")) {
						sql += " p.prod_name LIKE '%" + search.getSearchKeyword()
								+ "%' AND";
					} else if (search.getSearchCondition().equals("2")) {
						sql += " p.price LIKE '%" + search.getSearchKeyword()
						+ "%' AND";
					}
				}
			}
			sql += " p.prod_no = t.prod_no(+) order by p.prod_no";
			
			System.out.println("ProductDao::Original SQL :: " + sql);
			
			//==> TotalCount GET
			int totalCount = this.getTotalCount(sql);
			System.out.println("ProductDao :: totalCount  :: " + totalCount);
			
			//==> CurrentPage 게시물만 받도록 Query 다시구성
			sql = makeCurrentPageSql(sql, search);
			pStmt = conn.prepareStatement(sql);
			rs = pStmt.executeQuery();
			
			System.out.println(search);
			
			List<Product> list = new ArrayList<Product>();
					
			//상품정보 List가 존재한다면 정보 저장
			while (rs.next()) {
				Product product = new Product();
				product.setProdNo(rs.getInt("prod_no"));
				product.setProdName(rs.getString("prod_name"));
				product.setProdDetail(rs.getString("prod_detail"));
				product.setManuDate(rs.getString("manufacture_day"));
				product.setPrice(rs.getInt("price"));
				product.setFileName(rs.getString("image_file"));
				product.setRegDate(rs.getDate("REG_DATE"));
				product.setProTranCode(CommonUtil.null2str(rs.getString("tran_status_code"), null));
				list.add(product);
			}
				
			//==> totalCount 정보 저장
			hashMap.put("totalCount", new Integer(totalCount));	
			//==> currentPage 의 게시물 정보 갖는 List 저장
			hashMap.put("list", list);
		} catch (Exception e) {
			// TODO: handle exception
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return hashMap;
	}

	public void insertProduct(Product Product) {
		PreparedStatement pStmt = null;

		try {
			pStmt = conn.prepareStatement(
					"INSERT INTO product VALUES (seq_product_prod_no.nextval, ?, ?, ?, ?, ?, sysdate)");
			pStmt.setString(1, Product.getProdName());
			pStmt.setString(2, Product.getProdDetail());
			pStmt.setString(3, Product.getManuDate());
			pStmt.setInt(4, Product.getPrice());
			pStmt.setString(5, Product.getFileName());

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

	public void updateProduct(Product Product) throws Exception {
		String sql = "UPDATE product "
					+ "SET prod_name=?, prod_detail=?, "
					+ "manufacture_day=?, price=?, image_file=? "
					+ "WHERE prod_no=?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		pStmt.setString(1, Product.getProdName());
		pStmt.setString(2, Product.getProdDetail());
		pStmt.setString(3, Product.getManuDate());
		pStmt.setInt(4, Product.getPrice());
		pStmt.setString(5, Product.getFileName());
		pStmt.setInt(6, Product.getProdNo());
		
		if (pStmt.executeUpdate() == 1) {
			System.out.println("product 업데이트 성공 :: "+Product);
		}
		
		pStmt.close();
		conn.close();
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		      "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		System.out.println("totalCount 실행");
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
			System.out.println("totalCount 성공");
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search search){
		sql = "SELECT * "
			+ "FROM (SELECT inner_table. * , ROWNUM AS row_seq " 
					+ "FROM (" +sql+ ") inner_table "
					+ "WHERE ROWNUM <="+ search.getCurrentPage()*search.getPageSize() +") " 
			+ "WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("페이지? :: "+search.getCurrentPage()*search.getPageSize()); //해당페이지까지의 갯수
		System.out.println("페이지?? :: "+((search.getCurrentPage()-1)*search.getPageSize()+1)); //해당페이지까지의 첫번째 게시물의 순서
		
		System.out.println("UserDAO :: make SQL :: "+ sql);	

		return sql;
	}
}
