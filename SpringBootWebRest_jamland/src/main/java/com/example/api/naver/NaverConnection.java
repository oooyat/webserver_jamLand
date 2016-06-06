package com.example.api.naver;
//2013105083 implement : naver api : ko -> en translator
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class NaverConnection 
{
	public String koToEn(String keywordKo) 
	{
		String urlStr = "https://openapi.naver.com/v1/language/translate";
		String naverAPI_ID = "Mh5C27jxxtb3IE4qyzI9";
		String naverAPI_PW = "QBwNi_XA8h";//Mh5C27jxxtb3IE4qyzI9//3IEaiSAK3o
		URL url = null;
		HttpURLConnection connection = null;
		String en = "";
		
		//String keywordKo = "음악대장";
		
		try 
		{
			url = new URL(urlStr);
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);connection.setUseCaches(false);
			connection.setRequestProperty("X-Naver-Client-Id", naverAPI_ID); // ID 
			connection.setRequestProperty("X-Naver-Client-Secret", naverAPI_PW); // PW 
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//connection.setRequestProperty("Host", "openapi.naver.com");
			String param = "source=ko&target=en&text=";
			param = param + URLEncoder.encode(keywordKo, "UTF-8");
			
			//json으로 입력 받음.
			OutputStreamWriter out = null;
			
			out = new OutputStreamWriter(connection.getOutputStream());
			out.write(param);
			out.flush();
			
			RcvGson myGson;						
			InputStreamReader rd = new InputStreamReader(connection.getInputStream(), "UTF-8");
			myGson = new Gson().fromJson(rd, RcvGson.class);
			
			System.out.print(myGson);
			en = myGson.getMessage().getResult().getTranslatedText();
			
		
		} catch (MalformedURLException e) //올바르지 않는 url
		{
			e.printStackTrace();
		} catch (IOException e)//web에 접속 실패.
		{
			e.printStackTrace();
		}
		finally
		{
			return en;
		}
	}
}


/* text로 응답 받아야 할 경우
BufferedReader rd = null;
try 
{
	rd = new BufferedReader( new InputStreamReader(connection.getInputStream()));
} 
catch (IOException e) 
{
	e.printStackTrace();
}

received = new Gson().fromJson(rd, RcvGson.class);

StringBuffer result = new StringBuffer();
String line = "";
try 
{
	while ((line = rd.readLine()) != null) 
	System.out.println(line);
	
	rd.close();
} 
catch (IOException e) 
{
	e.printStackTrace();
}*/