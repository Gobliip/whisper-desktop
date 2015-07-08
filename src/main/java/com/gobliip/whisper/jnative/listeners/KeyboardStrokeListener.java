package com.gobliip.whisper.jnative.listeners;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gobliip.whisper.jnative.GlobalScreenFacade;
import com.gobliip.whisper.service.KeyboardStrokesRegistry;

@Component
public class KeyboardStrokeListener implements NativeKeyListener, InitializingBean{
	
	private static final Logger logger = LoggerFactory.getLogger(KeyboardStrokeListener.class);

	@Autowired
	private KeyboardStrokesRegistry resgistry;
	
	@Autowired
	private GlobalScreenFacade globalScreen;
	
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		logger.debug("Key pressed: {}", e.getKeyCode());
		resgistry.register(e.getKeyCode());
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("Registering KeyStrokeListener...");
		globalScreen.addNativeKeyListener(this);
	}

}
