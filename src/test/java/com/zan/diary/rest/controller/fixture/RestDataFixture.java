package com.zan.diary.rest.controller.fixture;



public class RestDataFixture {

	public static String standardUserJSON() {
		return "{ \"name\": \"daniel\", \"pass\": \"test\", \"code\": \"8888\", \"loc\": \"15213\"}";
	}
	
	public static String standardUser1JSON() {
		return "{ \"name\": \"melody\", \"pass\": \"test1\", \"code\": \"6666\", \"loc\": \"15213\"}";
	}

	public static String standardUserQueryJSON() {
		return "{\"name\": \"daniel\", \"pass\": \"test\", \"code\": \"8888\", \"loc\": \"15213\"}";
	}
	
	public static String standardDiaryQueryJSON(String id) {
		return "{\"diaryid\":\""+id+"\", \"operon\": \"0\"}";
	}

	public static String standardDiaryJSON() {

		return "{\"owner\": \"daniel\", \"title\": \"fell good\", \"text\": \"text\" , \"subject\":\"subject\",  \"publicDiary\": true}";
	}
}
