package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

import client.app.http.HttpConnection;



public class RestHttpClientPurchaseApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
//		addPurchseGetTest();
//		addPurchsePostTest();
		
//		getPurchseGetTest();
//		updatePurchseGetTest();
		
//		updatePurchsePostTest();
//		updateTranCodeByProdGetTest();
		
		listPurchasePostTest();
		listPurchaseGetTest();
	}
	
	//========================================================================
	//211027~211028 추가부분
	
	public static void addPurchseGetTest() throws Exception {
		JSONObject jsonObject = HttpConnection.HttpGETConnection("/purchase/json/addPurchase/10001");
	
		ObjectMapper objectMapper = new ObjectMapper();
		Product product = objectMapper.readValue(jsonObject.toString(), Product.class);
		System.out.println(product);
	}
	
	public static void addPurchsePostTest() throws Exception {
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = new User();
		
		product.setProdNo(10001);
		user.setUserId("user12");
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("dbdbdb");
		purchase.setReceiverPhone("010-4444-4444");
		purchase.setDivyAddr("종각");
		//purchase.setTranCode("1");
		purchase.setMemberCheck("1");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", purchase);
		
		JSONObject jsonObject = HttpConnection.HttpPOSTConnection("/purchase/json/addPurchase", map);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Purchase returnPurchase = objectMapper.readValue(jsonObject.toString(), Purchase.class);
		System.out.println(returnPurchase);
	}
	
	public static void getPurchseGetTest() throws Exception {
		
		JSONObject jsonObject = HttpConnection.HttpGETConnection("/purchase/json/getPurchase/10000");
		
		ObjectMapper objectMapper = new ObjectMapper();
		Purchase purchase = objectMapper.readValue(jsonObject.toString(), Purchase.class);
		System.out.println(purchase);
	}
	
	public static void updatePurchseGetTest() throws Exception {
		
		JSONObject jsonObject = HttpConnection.HttpGETConnection("/purchase/json/updatePurchase/10000");
		
		ObjectMapper objectMapper = new ObjectMapper();
		Purchase purchase = objectMapper.readValue(jsonObject.toString(), Purchase.class);
		System.out.println(purchase);
		
	}
	
	//===========================================================================================
	//211109 추가부분
	
	public static void updatePurchsePostTest() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Purchase purchase = new Purchase();
		User user = new User();
		Product product = new Product();
		
		user.setUserId("user12");
		product.setProdNo(10001);
		purchase.setTranNo(10000);
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setDivyAddr("종각비트비트 test");
		purchase.setDivyDate("20211109");
		purchase.setDivyRequest("빠른배에송 test");
		purchase.setReceiverName("test");
		purchase.setReceiverPhone("010-222-2222");
		
		map.put("domain", purchase);
		
		JSONObject jsonObject = HttpConnection.HttpPOSTConnection("/purchase/json/updatePurchase", map);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Purchase resultPurchase = objectMapper.readValue(jsonObject.toString(), Purchase.class);
		System.out.println(resultPurchase);
		
	}

	public static void updateTranCodeByProdGetTest() throws Exception {
	
		JSONObject jsonObject = HttpConnection.HttpGETConnection("/purchase/json/updateTranCodeByProd/10001/2");
		
		ObjectMapper objectMapper = new ObjectMapper();
		Purchase purchase = objectMapper.readValue(jsonObject.toString(), Purchase.class);
		System.out.println(purchase);
	
	}
	
	public static void listPurchaseGetTest() throws Exception {
		
		HttpConnection.HttpGETConnection("/purchase/json/listPurchase");
	}
	
	public static void listPurchasePostTest() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
				
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", search);
		
		JSONObject jsonObject = HttpConnection.HttpPOSTConnection("/purchase/json/listPurchase",map);
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Purchase> list = objectMapper.readValue(jsonObject.get("list").toString(), ArrayList.class);
		System.out.println(list);
	
	}
}




