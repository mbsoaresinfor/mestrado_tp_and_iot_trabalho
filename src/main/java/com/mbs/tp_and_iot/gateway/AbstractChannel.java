package com.mbs.tp_and_iot.gateway;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public abstract class AbstractChannel {

	
	protected String APIURL = "http://api.thingspeak.com";
	protected static final String APIHEADER = "X-THINGSPEAKAPIKEY";
	protected Integer channelId = 1350904;
	protected String readAPIKey = "C669YRGVI4DF2L03";
	protected String writeAPIKey = "QD4A1OL80XP6Y9K0";
	protected Boolean isPublic;
	protected final HashMap<String, Object> fields = new HashMap<>();
	protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();
}
