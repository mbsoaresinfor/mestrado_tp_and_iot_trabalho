

package com.mbs.tp_and_iot.gateway;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

import java.util.Date;


public class Channel extends AbstractChannel{

    
    private long lastUpdate;
    private long intervalUpdate = 20 * 1000;  
     
    public Channel() {}

   
    public Channel(Integer channelId, String writeKey, String readKey) {
        super.channelId = channelId;
        super.readAPIKey = readKey;
        super.writeAPIKey = writeKey;
        super.isPublic = false;
    }

   
    private String thingRequest(String url) throws UnirestException, ThingSpeakException {
        GetRequest request = Unirest.get(url);
        if (!this.isPublic) {
            request.field("key", this.readAPIKey);
        }
        HttpResponse<JsonNode> response = request.asJson();
        if (response.getCode() != 200) {
            throw new ThingSpeakException("Request failed with code " + response.getCode());
        }
        return response.getBody().toString();
    }

    
    private String thingRequest(String url, FeedParameters options) throws UnirestException, ThingSpeakException {
        GetRequest request = Unirest.get(url);
        if (!this.isPublic) {
            request.field("key", this.readAPIKey);
        }
        request.fields(options.fields);
        HttpResponse<JsonNode> response = request.asJson();

        if (response.getCode() != 200) {
            throw new ThingSpeakException("Request failed with code " + response.getCode());
        }
        return response.getBody().toString();
    }

   
    public void setUrl(String url) {
        this.APIURL = url;
    }

    public Integer update(Entry entry) throws UnirestException, ThingSpeakException {
        
    	long dateNow = new Date().getTime();
    	boolean canUpdate = dateNow  > (lastUpdate + intervalUpdate);
    	if(canUpdate) {
	    	HttpResponse<String> response = Unirest.post(APIURL + "/update")
	                .header(APIHEADER, this.writeAPIKey)
	                .header("Connection", "close")
	                .fields(entry.getUpdateMap())
	                .asString();
	    	lastUpdate = dateNow; 
	        if (response.getCode() != 200) {
	            throw new ThingSpeakException("Request failed with code " + response.getCode());
	        } else if (response.getBody().equals("0")) {
	            throw new ThingSpeakException("Update failed.");
	        }
	        System.out.println("OK Update, values: " + entry.getUpdateMap());
	        return Integer.parseInt(response.getBody());
    	}else {
    		System.out.println("NO update, because dateNow < lastUpdate");
    		return -1;
    	}
    }

    
    public Feed getChannelFeed() throws UnirestException, ThingSpeakException {
        String url = APIURL + "/channels/" + this.channelId + "/feed.json";
        return gson.fromJson(thingRequest(url), Feed.class);
    }

   
    public Feed getChannelFeed(FeedParameters options) throws UnirestException, ThingSpeakException {
        String url = APIURL + "/channels/" + this.channelId + "/feed.json";
        return gson.fromJson(thingRequest(url, options), Feed.class);
    }

    
    public Entry getLastChannelEntry() throws UnirestException, ThingSpeakException {
        String url = APIURL + "/channels/" + this.channelId + "/feed/last.json";
        return gson.fromJson(thingRequest(url), Entry.class);
    }

   
    public Entry getLastChannelEntry(FeedParameters options) throws UnirestException, ThingSpeakException {
        String url = APIURL + "/channels/" + this.channelId + "/feed/last.json";
        return gson.fromJson(thingRequest(url, options), Entry.class);
    }

    
    public Feed getFieldFeed(Integer fieldId) throws UnirestException, ThingSpeakException {
        String url = APIURL + "/channels/" + this.channelId + "/field/" + fieldId + ".json";
        return gson.fromJson(thingRequest(url), Feed.class);
    }

    
    public Feed getFieldFeed(Integer fieldId, FeedParameters options) throws UnirestException, ThingSpeakException {
        String url = APIURL + "/channels/" + this.channelId + "/field/" + fieldId + ".json";
        return gson.fromJson(thingRequest(url, options), Feed.class);
    }

    
    public Entry getLastFieldEntry(Integer fieldId) throws UnirestException, ThingSpeakException {
        String url = APIURL + "/channels/" + this.channelId + "/field/" + fieldId + "/last.json";
        return gson.fromJson(thingRequest(url), Entry.class);
    }

    
    public Entry getLastFieldEntry(Integer fieldId, FeedParameters options) throws UnirestException, ThingSpeakException {
        String url = APIURL + "/channels/" + this.channelId + "/field/" + fieldId + "/last.json";
        return gson.fromJson(thingRequest(url, options), Entry.class);
    }

    
    public Feed getStatusFeed() throws UnirestException, ThingSpeakException {
        String url = APIURL + "/channels/" + this.channelId + "/status.json";
        return gson.fromJson(thingRequest(url), Feed.class);
    }

   
    public Feed getStatusFeed(FeedParameters options) throws UnirestException, ThingSpeakException {
        String url = APIURL + "/channels/" + this.channelId + "/status.json";
        return gson.fromJson(thingRequest(url, options), Feed.class);
    }

  
    public boolean isAvailable() {
        String url = APIURL + "/channels/" + this.channelId + "/feed.json" + "?key=" + this.readAPIKey + "&results=0";
        try {
            thingRequest(url);
        } catch (UnirestException | ThingSpeakException e) {
            return false;
        }
        return true;
    }
}
