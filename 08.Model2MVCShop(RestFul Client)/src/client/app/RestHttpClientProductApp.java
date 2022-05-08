package client.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

import client.app.http.HttpConnection;



public class RestHttpClientProductApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		addProductPostTest();
		
//		updateProductGetTest(); //Codehaus
//		updateProductPostTest(); //Codehaus	
		
//		getProductGetTest();
		
//		listProductPostTest();
//		listProductGetTest();
	}
	
	//========================================================================
	//211019-211027 추가부분 (Product)
	
	private static void addProductPostTest() throws Exception {
		Product product = new Product();
		product.setFileName("aaaaa.jpg");
		product.setManuDate("20211025");
		product.setPrice(10000);
		product.setProdDetail("json test이다요");
		product.setProdName("jsonTest");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", product);
		
		JSONObject jsonObject = HttpConnection.HttpPOSTConnection("/product/json/addProduct", map);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Product resultProduct = objectMapper.readValue(jsonObject.toString(), Product.class);
		System.out.println(resultProduct);
	}
	
	public static void getProductGetTest() throws Exception {
		JSONObject jsonobj = HttpConnection.HttpGETConnection("/product/json/getProduct/manage/10001");
		
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		System.out.println(product);
	}
	
	public static void updateProductGetTest() throws Exception {
		
		JSONObject jsonobj = HttpConnection.HttpGETConnection("/product/json/updateProduct/10001");
	
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		System.out.println(product);
		
	}
	
	public static void updateProductPostTest() throws Exception {
		Product product = new Product();
		product.setProdNo(10008);
		product.setProdName("change");
		product.setPrice(0);
		product.setProdDetail("변경했슴돠");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", product);
		
		JSONObject jsonobj = HttpConnection.HttpPOSTConnection("/product/json/updateProduct", map);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Product product01 = objectMapper.readValue(jsonobj.toString(), Product.class);
		System.out.println(product01);
	}
	
	public static void listProductPostTest() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		//search.setSearchCondition("1");
		//search.setSearchKeyword("자");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", search);
		
		JSONObject jsonobj = HttpConnection.HttpPOSTConnection("/product/json/listProduct", map);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		List list = (List) jsonobj.get("list");
		System.out.println(list);
		
		for(int i=0;i<list.size();i++) {
			Product product = objectMapper.readValue(list.get(i).toString(), Product.class);
			System.out.println(product);
		}
//		List list = objectMapper.readValue(object.toString(), ArrayList.class);
//		System.out.println(list);
	}
	
	public static void listProductGetTest() throws Exception {

		HttpConnection.HttpGETConnection("/product/json/listProduct");
	}
	
}




