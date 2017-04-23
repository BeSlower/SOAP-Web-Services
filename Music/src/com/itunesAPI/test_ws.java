package com.itunesAPI;

public class test_ws {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		music_web_service ws = new music_web_service();
		String singername = "Avril";
		String data = ws.requestData(singername);
		System.out.println(data);
	}
}
