package com.model2.mvc.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommonUtil {
	
	///Field
	
	///Constructor
	
	///Method
	public static String null2str(String org, String converted) { //null check할 1번째 아구먼트, null일 시 대신 넘겨줄 2번째 아구먼트
		if (org == null || org.trim().length() == 0)
			return converted;
		else
			return org.trim();
	}

	public static String null2str(String org) {
		return CommonUtil.null2str(org, "");
	}

	//BigDecimal => 소수점을 다룰 때 사용, double과 float보다 정밀함
	public static String null2str(Object org) {
		if (org != null && org instanceof java.math.BigDecimal) { 
			return CommonUtil.null2str((java.math.BigDecimal) org, "");
		} else {
			return CommonUtil.null2str((String) org, "");
		}
	}

	public static String null2str(java.math.BigDecimal org, String converted) {
		if (org == null)
			return converted;
		else
			return org.toString();
	}

	public static String null2str(java.math.BigDecimal org) {
		return CommonUtil.null2str(org, "");
	}

	//
	public static String toDateStr(String dateStr) {
		if (dateStr == null)
			return "";
		else if (dateStr.length() != 8)
			return dateStr;
		else
			return dateStr.substring(0, 4) + "/" + dateStr.substring(4, 6)
					+ "/" + dateStr.substring(6, 8);
	}

	//Timestamp => 현재 시간 구하기
	public static String toDateStr(Timestamp date) {
		if (date == null)
			return "";
		else {
			//SimpleDateFormat => 원하는 포맷으로 출력
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return sdf.format(new Date(date.getTime())); 
			//new Date(date.getTime()) => 1970년 1월 1일, 0시 0분 0초 GMT (그리니지 표준시)로부터의 밀리 세컨드수를 리턴
			//sdf.format => 주어진 날짜를 날짜/시간 문자열로 포맷하고 결과를 주어진 StringBuffer에 추가
		}
	}

	//주민등록번호 표준화 메소드
	public static String toSsnStr(String ssnStr) {
		if (ssnStr == null)
			return "";
		else if (ssnStr.length() != 13) //-로 구분되어 입력되면 그대로 리턴 
			return ssnStr;
		else
			return ssnStr.substring(0, 6) + "-" + ssnStr.substring(6, 13); //-없이 입력되면 -추가
	}

	//금액 표기법 메소드
	public static String toAmountStr(String amountStr) {
		String returnValue = "";
		if (amountStr == null)
			return returnValue;
		else {
			int strLength = amountStr.length();

			if (strLength <= 3) //금액이 3자리 이하면 그대로 리턴
				return amountStr;
			else {
				String s1 = "";
				String s2 = "";
				for (int i = strLength - 1; i >= 0; i--)
					s1 += amountStr.charAt(i);

				for (int i = strLength - 1; i >= 0; i--) {
					s2 += s1.charAt(i);
					if (i % 3 == 0 && i != 0)
						s2 += ",";
				}

				return s2;
			}
		}
	}

	public static String toAmountStr(java.math.BigDecimal amount) {
		if (amount == null) {
			return "";
		} else {
			return toAmountStr(amount.toString());
		}
	}
}
