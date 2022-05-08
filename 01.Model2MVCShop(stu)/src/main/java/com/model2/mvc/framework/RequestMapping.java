package com.model2.mvc.framework;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RequestMapping {

	private static RequestMapping requestMapping;
	private Map<String, Action> map;
	private Properties properties;
	
	//생성자가 만들어져 잇으면 디폴트 생성자가 만들어지지 않음 but, 가독성
	private RequestMapping() {}

	//Properties 파일에 있는 내용을 파싱해서  properties에 집어넣어 줌
	private RequestMapping(String resources) {
		map = new HashMap<String, Action>();
		InputStream in = null;
		try {
			//in = new FileInputStream(resources);
			in = getClass().getClassLoader().getResourceAsStream(resources);
			System.out.println(in + "의 값은 뭘까유");
			// key, value 형식으로 된 "파일이름.properties" 파일 또는 xml 파일
			properties = new Properties();
			properties.load(in); // 스트림으로 열린 Properties 파일 객체를 로드함, 파싱해서 집어넣어줌
		} catch (Exception ex) {
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties 파일 로딩 실패 :" + ex);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception ex) {
				}
			}
		}
	}
	
	//Singleton Pattern (생성자가 여러차례 호출되어도 최초 생성자가 생성한 객체만을 return)
	public synchronized static RequestMapping getInstance(String resources) {
		if (requestMapping == null) {
			requestMapping = new RequestMapping(resources);
		}
		return requestMapping;
	}

	//실행하고 있는 페이지가 어떤 액션인지 확인 및 인스턴스 생성 및 반환
	public Action getAction(String path) {
		// 가공한 path => /logon.do ...
		// 처음 실행 시 map.get(path)에 저장된 key에 저장된 값은 없으니 action에 null을 반환
		Action action = map.get(path);
		if (action == null) {
			//resources/actionmapping.properties에 선언한 key = value 값을 매칭
			String className = properties.getProperty(path);
			System.out.println("prop : " + properties); //com.*.*.*....모두 출력됨
			System.out.println("path : " + path); 
			System.out.println("className : " + className);
			className = className.trim(); //앞뒤에 공백이 있다면 제거
			try {
				Class c = Class.forName(className); //className에 해당하는 클래스를 반환해줌 ex)"oracle.jdbc.driver.OracleDriver"
				Object obj = c.newInstance(); //메모리에는 올라오지 않은 c를 인스턴스화 해주어야함 => 반환값이 Object
				System.out.println(obj + "obj의 값 확인");
				//참조변수가 참조하고 있는 인스턴스의 실제 타입을 가져와 비교 
				//~Action.java는 Action class를 상속받음
				if (obj instanceof Action) {
					//같은 동작 실행 시 해당 path로 저장된 action을 반환해주기 위해 put
					map.put(path, (Action) obj);
					action = (Action) obj;
				} else {
					throw new ClassCastException("Class형변환시 오류 발생  ");
				}
			} catch (Exception ex) {
				System.out.println(ex);
				throw new RuntimeException("Action정보를 구하는 도중 오류 발생 : " + ex);
			}
		}
		return action;
	}
}