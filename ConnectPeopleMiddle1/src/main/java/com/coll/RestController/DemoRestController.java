package com.coll.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
 
	@GetMapping(value="/demo")
	public String testRestfull()
	{
		return "Restfull Service Working"; 
	}
}
