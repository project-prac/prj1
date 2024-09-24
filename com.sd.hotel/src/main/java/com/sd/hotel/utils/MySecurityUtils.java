package com.sd.hotel.utils;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class MySecurityUtils {

	// original 문자열을 SHA-256해시로 반환하는 메소드
	public static String getSha256(String original) {
		StringBuilder builder = new StringBuilder();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// MessageDigest클래스 : 암호학적 해싱 함수를 구현함
			// getInstance("SHA-256") SHA256 해시 알고리즘을 사용하는 MessageDigest객체를 반환
			digest.update(original.getBytes());
			//original을 바이트 배열로 변환후 MessageDigest객체에 전달함
			byte[] bytes = digest.digest();
			//해싱작업을 수행하고 결과를 바이트 배열로 반환함
			for(int i = 0; i < bytes.length; i++) {
				builder.append(String.format("%02X", bytes[i]));
			}
			//String.format("%02X", bytes[i]) : 바이트값을2자리 16진수로 포맷함
			// 02는 2자리, X는 대문자 16진수
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	//공격방지위한 간단한 문자열 변환 메소드
	// original이라는 문자열을 받아 XSS공격을 방지할 수 있는 형태로 변환뒤 결과 반환
	public static String getPrvetnXss(String original) {
		return original.replace("<script>", "&lt;script&gt;").replace("</script>", "&lt;/script&gt;");
	}
	/*
	  문자열 내의 모든 <script>를 HTML 이스케이프 문자열 &lt;script&gt;로 변환함
	 마찬가지로, </script>를 &lt;/script&gt;로 변환함
	*/
	
	
	/* 
	 랜덤한 문자열을 생성해서 변환함
	 count: 생성할 문자열의 길이 
	 letter: 알파벳 문자를 포함할지 여부 
	 number: 숫자를 포함할지 여부
	*/
	public static String getRandomString(int count, boolean letter, boolean number) {
		StringBuilder builder = new StringBuilder();
		List<String> list = new ArrayList<String>();
		if(letter) {
			for(char ch = 'A'; ch <= 'Z'; ch++) {
				list.add(ch + "");
			}
			for(char ch = 'a'; ch <= 'z'; ch++) {
				list.add(ch+"");
			}
		}
		if(number) {
			for(int n = 0; n <= 9; n++) {
				list.add(n + "");
			}
		}
		SecureRandom secureRandom = new SecureRandom();
		// SecureRandom 안전한 난수를 생성하는 클래스
		if(letter || number) {
			while(count > 0) {
				builder.append(list.get(secureRandom.nextInt(list.size())));
				count--;
			}
		}
		return builder.toString();
	}
}
