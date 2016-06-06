package com.example.api.naver;

import java.util.List;

public class RcvGson {
	//private NaverMsg message;
	private messageSet message;
	
	public messageSet getMessage() {
		return message;
	}

	public void setMessage(messageSet message) {
		this.message = message;
	}

	public String toString()
	{
		return message.getResult().getTranslatedText();
	}
	
	public static class messageSet
	{
		private String type;
		private String service;
		private String version;
		private NaverResult result;	
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getService() {
			return service;
		}
		public void setService(String service) {
			this.service = service;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		
		public NaverResult getResult() {
			return result;
		}

		public void setResult(NaverResult result) {
			this.result = result;
		}

		
		public static class NaverResult
		{
			private String translatedText;
			private String wsd;
			private String money;
			public String getTranslatedText() {
				return translatedText;
			}
			public void setTranslatedText(String translatedText) {
				this.translatedText = translatedText;
			}
			public String getWsd() {
				return wsd;
			}
			public void setWsd(String wsd) {
				this.wsd = wsd;
			}
			public String getMoney() {
				return money;
			}
			public void setMoney(String money) {
				this.money = money;
			}
			
			public String toString()
			{
				return translatedText;
			}
			
		}
		
		public String toString()
		{
			return type+ " : " + service+ " : " + version ;
		}
	}
	
	
}
