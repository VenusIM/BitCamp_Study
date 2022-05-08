package com.model2.mvc.framework;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class RequestMapping {

	private static RequestMapping requestMapping;
	private Map<String, Action> map;
	private Properties properties;
	
	//�����ڰ� ������� ������ ����Ʈ �����ڰ� ��������� ���� but, ������
	private RequestMapping() {}

	//Properties ���Ͽ� �ִ� ������ �Ľ��ؼ�  properties�� ����־� ��
	private RequestMapping(String resources) {
		map = new HashMap<String, Action>();
		InputStream in = null;
		try {
			//in = new FileInputStream(resources);
			in = getClass().getClassLoader().getResourceAsStream(resources);
			System.out.println(in + "�� ���� ������");
			// key, value �������� �� "�����̸�.properties" ���� �Ǵ� xml ����
			properties = new Properties();
			properties.load(in); // ��Ʈ������ ���� Properties ���� ��ü�� �ε���, �Ľ��ؼ� ����־���
		} catch (Exception ex) {
			System.out.println(ex);
			throw new RuntimeException("actionmapping.properties ���� �ε� ���� :" + ex);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception ex) {
				}
			}
		}
	}
	
	//Singleton Pattern (�����ڰ� �������� ȣ��Ǿ ���� �����ڰ� ������ ��ü���� return)
	public synchronized static RequestMapping getInstance(String resources) {
		if (requestMapping == null) {
			requestMapping = new RequestMapping(resources);
		}
		return requestMapping;
	}

	//�����ϰ� �ִ� �������� � �׼����� Ȯ�� �� �ν��Ͻ� ���� �� ��ȯ
	public Action getAction(String path) {
		// ������ path => /logon.do ...
		// ó�� ���� �� map.get(path)�� ����� key�� ����� ���� ������ action�� null�� ��ȯ
		Action action = map.get(path);
		if (action == null) {
			//resources/actionmapping.properties�� ������ key = value ���� ��Ī
			String className = properties.getProperty(path);
			System.out.println("prop : " + properties); //com.*.*.*....��� ��µ�
			System.out.println("path : " + path); 
			System.out.println("className : " + className);
			className = className.trim(); //�յڿ� ������ �ִٸ� ����
			try {
				Class c = Class.forName(className); //className�� �ش��ϴ� Ŭ������ ��ȯ���� ex)"oracle.jdbc.driver.OracleDriver"
				Object obj = c.newInstance(); //�޸𸮿��� �ö���� ���� c�� �ν��Ͻ�ȭ ���־���� => ��ȯ���� Object
				System.out.println(obj + "obj�� �� Ȯ��");
				//���������� �����ϰ� �ִ� �ν��Ͻ��� ���� Ÿ���� ������ �� 
				//~Action.java�� Action class�� ��ӹ���
				if (obj instanceof Action) {
					//���� ���� ���� �� �ش� path�� ����� action�� ��ȯ���ֱ� ���� put
					map.put(path, (Action) obj);
					action = (Action) obj;
				} else {
					throw new ClassCastException("Class����ȯ�� ���� �߻�  ");
				}
			} catch (Exception ex) {
				System.out.println(ex);
				throw new RuntimeException("Action������ ���ϴ� ���� ���� �߻� : " + ex);
			}
		}
		return action;
	}
}