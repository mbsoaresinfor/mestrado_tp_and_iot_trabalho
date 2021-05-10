
package com.mbs.tp_and_iot.gateway;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Feed {

    private class ChannelInfo {

        private Date created_at;
        private String description;
        private String field1;
        private String field2;
        private String field3;
        private String field4;
        private String field5;
        private String field6;
        private String field7;
        private String field8;
        private Integer id;
        private Integer last_entry_id;
        private String name;
        private Date updated_at;
    }
    private final ChannelInfo channel = new ChannelInfo();
    private final ArrayList<Entry> feeds = new ArrayList<>();

   
    private Feed() {

    }

   
    public Date getChannelCreationDate() {
        return channel.created_at;
    }

    
    public String getChannelDescription() {
        return channel.description;
    }

    public String getFieldName(Integer field) {
        switch (field) {
            case 1:
                return channel.field1;
            case 2:
                return channel.field2;
            case 3:
                return channel.field3;
            case 4:
                return channel.field4;
            case 5:
                return channel.field5;
            case 6:
                return channel.field6;
            case 7:
                return channel.field7;
            case 8:
                return channel.field8;
        }
        throw new IllegalArgumentException("Invalid field.");
    }

    
    public Integer getChannelId() {
        return channel.id;
    }

   
    public Integer getChannelLastEntryId() {
        return channel.last_entry_id;
    }

   
    public String getChannelName() {
        return channel.name;
    }

    
    public Date getChannelUpdateDate() {
        return channel.updated_at;
    }

    
    public ArrayList<Entry> getEntryList() {
        return this.feeds;
    }

    
    public Map<Integer, Entry> getEntryMap() {
        HashMap<Integer, Entry> map = new HashMap<>();
        for (Entry entry : this.feeds) {
            map.put(entry.getEntryId(), entry);
        }
        return map;
    }

    
    public Entry getEntry(Integer id) throws ThingSpeakException {
        for (Entry entry : this.feeds) {
            if (entry.getEntryId().equals(id)) {
                return entry;
            }
        }
        throw new ThingSpeakException("Entry with ID " + id + " not found in feed.");

    }

   
    public Entry getChannelLastEntry() throws ThingSpeakException {
        return getEntry(channel.last_entry_id);
    }

}
