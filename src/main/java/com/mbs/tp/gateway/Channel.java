

package com.mbs.tp.gateway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import java.util.HashMap;


public class Channel {

    private String APIURL = "http://api.thingspeak.com";
    private static final String APIHEADER = "X-THINGSPEAKAPIKEY";
    private Integer channelId = 1350904;
    private String readAPIKey = "C669YRGVI4DF2L03";
    private String writeAPIKey = "QD4A1OL80XP6Y9K0";
    private Boolean isPublic;
    private final HashMap<String, Object> fields = new HashMap<>();
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").create();

  
    public Channel() {}

   
    public Channel(Integer channelId, String writeKey, String readKey) {
        this.channelId = channelId;
        this.readAPIKey = readKey;
        this.writeAPIKey = writeKey;
        this.isPublic = false;
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
        HttpResponse<String> response = Unirest.post(APIURL + "/update")
                .header(APIHEADER, this.writeAPIKey)
                .header("Connection", "close")
                .fields(entry.getUpdateMap())
                .asString();
        if (response.getCode() != 200) {
            throw new ThingSpeakException("Request failed with code " + response.getCode());
        } else if (response.getBody().equals("0")) {
            throw new ThingSpeakException("Update failed.");
        }
        return Integer.parseInt(response.getBody());
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
