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


//================== ���� ��� (GET, POST Connection) =========================
public class HttpConnection {

	public static JSONObject HttpGETConnection(String uri) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8070" + uri;
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
				
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
//			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
//			String serverData = br.readLine();
//			System.out.println(serverData);
	
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("::::"  + jsonobj);
		
		return jsonobj;
	}
	
	
	public static JSONObject HttpPOSTConnection(String uri, Map<String, Object> map) throws Exception {
		// HttpClient : Http Protocol �� client �߻�ȭ 
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
			//Object ==> JSON Value �� ��ȯ
			String jsonValue = objectMapper01.writeValueAsString(map.get("domain"));
			httpEntity01 = new StringEntity(jsonValue,"utf-8");
		}
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		//JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println("::::"  + jsonobj);
		return jsonobj;
	}
}
