package com.mbs.tp.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ControllerGateway {

	@RequestMapping(value = "/update", method = RequestMethod.GET)	 
	  public void update(@RequestParam(name = "field") String field, 
			  @RequestParam(name = "value") String value) {
		
		Channel channel = new Channel();
		Entry entry = new Entry();
		entry.setField(Integer.parseInt(field), value);
		try {
			channel.update(entry);
		} catch (Exception e) {
			System.err.println("Error update " + e.getMessage());
			e.printStackTrace();
		} 
		 
	 }
}
