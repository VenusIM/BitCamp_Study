package client.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;

import client.app.http.HttpConnection;



public class RestHttpClientUserApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get ��� Request : JsonSimple lib ���
		RestHttpClientUserApp.getUserTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientUserApp.getUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post ��� Request : JsonSimple lib ���
//		RestHttpClientUserApp.LoginTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Post ��� Request : CodeHaus lib ���
//		RestHttpClientUserApp.LoginTest_Codehaus();		
	
//		==================================================================	

//		addUserTest_JsonSimple();
//		addUserTest_Codehaus();
		
//		updateUserGetTest(); //JsonSimple
//		updateUserPostTest(); //Codehaus
		
//		listUserPostTest(); //Codehaus
//		listUserGetTest();
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
	public static void getUserTest_JsonSimple() throws Exception{
		HttpConnection.HttpGETConnection("/user/json/getUser/admin");
	}
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void getUserTest_Codehaus() throws Exception{
		
		JSONObject jsonobj = HttpConnection.HttpGETConnection("/user/json/getUser/admin");
	
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void LoginTest_JsonSimple() throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("json", json);
		
		HttpConnection.HttpPOSTConnection("/user/json/login", map);	
	}
	
	
	//2.2 Http Protocol POST ��� Request : FromData���� 
	//==> JsonSimple + codehaus 3rd party lib ���
	public static void LoginTest_Codehaus() throws Exception{
		
//		//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ ��� 2 : JSONObject ���]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ ��� 3 : codehaus ���]
		User user =  new User();
		user.setUserId("user12");
		user.setPassword("1212");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", user);
		
		JSONObject jsonobj = HttpConnection.HttpPOSTConnection("/user/json/login", map);

		ObjectMapper objectMapper = new ObjectMapper();
		User user01 = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user01);
	}	
	
	
	//========================================================================
	//211019-211027 �߰��κ� (User)
	
	public static void addUserTest_JsonSimple() throws Exception {
		
		JSONObject json = new JSONObject();
		json.put("userId", "hjy");
		json.put("password", "1234");
		json.put("userName", "SCOTT");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("json", json);

		HttpConnection.HttpPOSTConnection("/user/json/addUser", map);
	}
	
	public static void addUserTest_Codehaus() throws Exception {
		
		User user =  new User();
		user.setUserId("hjy");
		user.setPassword("1234");
		user.setUserName("SCOTT");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", user);
		
		JSONObject jsonobj = HttpConnection.HttpPOSTConnection("/user/json/addUser", map);
		
		ObjectMapper objectMapper = new ObjectMapper();
		User user01 = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user01);
	}
	
	public static void updateUserGetTest() throws Exception {		
		HttpConnection.HttpGETConnection("/user/json/updateUser/user12");
	}
	
	public static void updateUserPostTest() throws Exception {
		
		JSONObject json = new JSONObject();
		json.put("userId", "user12");
		json.put("userName", "SCOTT");
		json.put("phone", "000-0000-0000");
		json.put("addr", "������Ʈķ��");
		json.put("email", "hhh@jjj.com");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("json", json);
		JSONObject jsonobj = HttpConnection.HttpPOSTConnection("/user/json/updateUser", map);
		
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.get("user").toString(), User.class);
		System.out.println(user);
	}
	
	public static void listUserPostTest() throws Exception {
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		//search.setSearchCondition("0");
		//search.setSearchKeyword("user12");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("domain", search);
		
		JSONObject jsonobj = HttpConnection.HttpPOSTConnection("/user/json/listUser", map);
		
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<User> list = objectMapper.readValue(jsonobj.get("list").toString(), ArrayList.class);
		System.out.println(list);
	}
	
	public static void listUserGetTest() throws Exception {
//		Search search = new Search();
//		search.setCurrentPage(1);
//		search.setPageSize(3);
//		//search.setSearchCondition("1");
//		//search.setSearchKeyword("user12");
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("domain", search);
		
		HttpConnection.HttpGETConnection("/user/json/listUser");
	}
}




