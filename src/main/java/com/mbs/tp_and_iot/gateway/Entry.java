
package com.mbs.tp_and_iot.gateway;

import java.util.Date;
import java.util.HashMap;

public class Entry {
    
    private Date created_at;
    private Integer entry_id;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private Double latitude;
    private Double longitude;
    private Double elevation;
    private String status;    
    
    private final HashMap<String, Object> updateMap = new HashMap<>();

   
    HashMap<String, Object> getUpdateMap() {        
        return updateMap;
    }
            
  
    public Object getField(Integer field) {
        switch(field) {
            case 1:
                return field1;
            case 2:
                return field2;
            case 3:
                return field3;
            case 4:
                return field4;
            case 5:
                return field5;
            case 6:
                return field6;
            case 7:
                return field7;
            case 8:
                return field8;                
        }
        throw new IllegalArgumentException("Invalid field.");
    }

   
    public void setField(Integer field, String value) {
        switch(field) {
            case 1:
                field1 = value;
                updateMap.put("field1", value);
                return;
            case 2:
                field2 = value;
                updateMap.put("field2", value);
                return;
            case 3:
                field3 = value;
                updateMap.put("field3", value);
                return;
            case 4:
                field4 = value;
                updateMap.put("field4", value);
                return;
            case 5:
                field5 = value;
                updateMap.put("field5", value);
                return;
            case 6:
                field6 = value;
                updateMap.put("field6", value);
                return;
            case 7:
                field7 = value;
                updateMap.put("field7", value);
                return;
            case 8:
                field8 = value;
                updateMap.put("field8", value);
                return;
        }
        throw new IllegalArgumentException("Invalid field.");
    }
    
   
    public Double getLatitude() {
        return latitude;
    }

   
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
        updateMap.put("lat", latitude);
    }

    
    public Double getLongitude() {        
        return longitude;
    }

    public void setLong(Double longitude) {
        this.longitude = longitude;
        updateMap.put("long", longitude);
    }

    
    public Double getElevation() {
        return elevation;
    }

    
    public void setElevation(Double elevation) {
        this.elevation = elevation;
        updateMap.put("elevation", elevation);
    }

    
    public String getStatus() {
        return status;
    }

    
    public void setStatus(String status) {
        this.status = status;
        updateMap.put("status", status);
    }
    
    
    public void setCreated(Date created) {
        this.created_at = created;
        updateMap.put("created_at", created);
    }
	
    
    public Date getCreated() {        
        return created_at;
    }

   
    public Integer getEntryId() {
        return entry_id;
    }                  
}
