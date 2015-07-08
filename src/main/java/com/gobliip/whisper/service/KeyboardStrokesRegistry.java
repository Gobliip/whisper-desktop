package com.gobliip.whisper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class KeyboardStrokesRegistry {
	
	@Autowired
	private ConfigurableApplicationContext context;
	
	public void register(int keyCode) {
		context.close();
	}

}
