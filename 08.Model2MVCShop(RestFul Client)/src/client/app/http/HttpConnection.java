package client.app.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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


//================== 공통 모듈 (GET, POST Connection) =========================
public class HttpConnection {

	public static JSONObject HttpGETConnection(String uri) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8070" + uri;
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
				
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
//			System.out.println("[ Server 에서 받은 Data 확인 ] ");
//			String serverData = br.readLine();
//			System.out.println(serverData);
	
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("::::"  + jsonobj);
		
		return jsonobj;
	}
	
	
	public static JSONObject HttpPOSTConnection(String uri, Map<String, Object> map) throws Exception {
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8070" + uri;
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");

		System.out.println(url);
		HttpEntity httpEntity01 = null;
		
		if (map.get("json") != null) {
			httpEntity01 = new StringEntity(map.get("json").toString(),"utf-8");
		} else {
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(map.get("domain"));
			httpEntity01 = new StringEntity(jsonValue,"utf-8");
		}
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		//JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("::::"  + jsonobj);
		return jsonobj;
	}
}
