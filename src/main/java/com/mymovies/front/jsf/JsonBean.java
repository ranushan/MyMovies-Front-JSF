package com.mymovies.front.jsf;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@ManagedBean(name = "json")
@ViewScoped
public class JsonBean {
	
	private DiscoverBean discoverBean;
	
	public DiscoverBean getDiscoverBean() {
		return discoverBean;
	}

	public void setDiscoverBean(DiscoverBean discoverBean) {
		this.discoverBean = discoverBean;
	}
	
	@PostConstruct
	public void init() {
	
		String schema = "http://localhost:8080/discover";
		
		try {
			Document data = Jsoup.connect(schema).ignoreContentType(true).get();
			String json = data.select("body").text();
			discoverBean = new Gson().fromJson(json, new TypeToken<DiscoverBean>(){}.getType());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	
	

}
