package com.mbs.tp_and_iot.gateway;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FeedParameters {
  
    public enum Period { 
        /**
         * 10 minutes.
         */
        T10m(10), 
        
        /**
         * 15 minutes.
         */
        T15m(15), 
        
        /**
         * 20 minutes.
         */
        T20m(20),
        
        /** 
         * 30 minutes.
         */
        T30m(30),
        
        /**
         * 1 hour / 60 minutes.
         */
        T1h(60), 
        
        /**
         * 4 hours / 240 minutes.
         */
        T4h(240), 
        
        /**
         * 12 hours / 720 minutes.
         */
        T12h(720),
        
        /**
         * 24 hours / 1440 minutes.
         */
        T24h(1440);
        
        private final Integer minutes;       
        
        private Period(Integer minutes) {
            this.minutes = minutes;
        }
        
        Integer minutes() {
            return this.minutes;
        }
        
    }
    
    
    HashMap<String, Object> fields = new HashMap<>();
    
   
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    public void results(Integer results) {
        if (results > 8000) {
            throw new IllegalArgumentException("Feed cannot return more than 8000 results.");
        }
        fields.put("results", results);
    }
          
    public void days(Integer days) {
        fields.put("days", days);
    }
    
    
    public void start(Date date) {
        fields.put("start", formatter.format(date));
    }
   
    public void end(Date date) {
        fields.put("end", formatter.format(date));
    }
    
    
    public void offset(Integer hours) {
        fields.put("offset", hours);
    }
    
  
    public void status(Boolean include) {
        fields.put("status", include);
    }
   
    public void location(Boolean include) {
        fields.put("location", include);
    }
    
   
    public void min(Double value) {
        fields.put("min", value);
    }
    
    
    public void max(Double value) {
        fields.put("max", value);
    }
    
    
    public void round(Integer places) {
        fields.put("round", places);
    }
    
   
    public void timescale(Period t) {
        fields.put("timescale", t.minutes());
    }
    
    
    public void sum(Period t) {
        fields.put("sum", t.minutes());
    }
    
    
    public void average(Period t) {
        fields.put("average", t.minutes());        
    }
    
    public void median(Period t) {
        fields.put("median", t.minutes());
    }
    
}
