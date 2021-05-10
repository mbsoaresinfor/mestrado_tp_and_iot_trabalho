package com.mbs.tp_and_iot.gateway;

import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {

	public static void main(String[] args) throws Exception {
		Channel channel = new Channel();
		Entry entry = new Entry();
		entry.setField(1, "20");
		channel.update(entry);
		System.out.println("executado");
	}

}
