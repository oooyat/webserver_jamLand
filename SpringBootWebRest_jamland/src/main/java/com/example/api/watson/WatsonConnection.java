package com.example.api.watson;

import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentEmotion;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;

import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentEmotion;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentSentiment;

public class WatsonConnection {
	
	AlchemyLanguage service;
	DocumentEmotion sentiment;
	double anger;
	double disgust;
	double fear;
	double joy;
	double sadness;
	
	public WatsonConnection(String en) 
	{
		service = new AlchemyLanguage();
		service.setApiKey("e68ef5cf7a50ee4782f3ecb63457edc7db2ab6dc");
		Map<String,Object> params = new HashMap<String, Object>();
		params.put(AlchemyLanguage.TEXT, en);
		sentiment = service.getEmotion(params).execute();
		
		anger = sentiment.getEmotion().getAnger();
		disgust = sentiment.getEmotion().getDisgust();
		fear = sentiment.getEmotion().getFear();
		joy = sentiment.getEmotion().getJoy();
		sadness = sentiment.getEmotion().getSadness();
	}
	
	public double mostEmotionScore()
	{
		double[] scores = {anger, disgust, fear, joy, sadness};	
		double most = 0;
		int i;
		
		for(i=0; i<scores.length-1 ; i++)
		{
			if(scores[i] > most)
				most = scores[i];
		}
		
		return most;
	}
	
	public String mostEmotion()
	{
		String[] emotions = {"anger", "disgust", "fear", "joy", "sadness"};
		double[] scores = {anger, disgust, fear, joy, sadness};	
		double most = 0;
		int i, index=0;
		
		for(i=0; i<scores.length-1 ; i++)
		{
			if(scores[i] > most)
			{
				most = scores[i];
				index =i;
			}
				
		}
		
		return emotions[index];
	}

	public AlchemyLanguage getService() {
		return service;
	}

	public void setService(AlchemyLanguage service) {
		this.service = service;
	}

	public DocumentEmotion getSentiment() {
		return sentiment;
	}

	public void setSentiment(DocumentEmotion sentiment) {
		this.sentiment = sentiment;
	}

	public double getAnger() {
		return anger;
	}

	public void setAnger(double anger) {
		this.anger = anger;
	}

	public double getDisgust() {
		return disgust;
	}

	public void setDisgust(double disgust) {
		this.disgust = disgust;
	}

	public double getFear() {
		return fear;
	}

	public void setFear(double fear) {
		this.fear = fear;
	}

	public double getJoy() {
		return joy;
	}

	public void setJoy(double joy) {
		this.joy = joy;
	}

	public double getSadness() {
		return sadness;
	}

	public void setSadness(double sadness) {
		this.sadness = sadness;
	}
	
}