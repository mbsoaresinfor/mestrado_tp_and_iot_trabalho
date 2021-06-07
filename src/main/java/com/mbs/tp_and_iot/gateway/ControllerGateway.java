package com.mbs.tp_and_iot.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Rest para atualizacao dos dados referentes aos sensores Nodes.
 * @author marcelo.soares
 *
 */

@RestController
public class ControllerGateway {

	Channel channel = new Channel();
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)	 
	  public void update(@RequestParam(name = "field") String field, 
			  @RequestParam(name = "value") String value) {
		
		try {
			processUpdate(field,value);
		} catch (Exception e) {
			System.err.println("Error update " + e.getMessage());
			e.printStackTrace();
		} 
		 
	 }
	
	private void processUpdate(String field, String value) throws Exception{
		System.out.println("\nPackage: field=" + field + " value=" + value);
		Entry entry = new Entry();
		entry.setField(Integer.parseInt(field), value);
		channel.update(entry);
	}
}
