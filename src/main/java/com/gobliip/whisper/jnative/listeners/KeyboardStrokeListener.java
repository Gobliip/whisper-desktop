package com.gobliip.whisper.jnative.listeners;

import com.gobliip.whisper.jnative.GlobalScreenFacade;
import com.gobliip.whisper.model.KeyboardStroke;
import com.gobliip.whisper.service.KeyboardStrokesRegistry;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class KeyboardStrokeListener implements NativeKeyListener, InitializingBean, DisposableBean{

	private static final Logger logger = LoggerFactory.getLogger(KeyboardStrokeListener.class);

	@Autowired
	private KeyboardStrokesRegistry resgistry;

	@Autowired
	private GlobalScreenFacade globalScreen;


	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		resgistry.add(new KeyboardStroke(Instant.now(), e.getKeyCode(), e.getKeyChar()));
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

	@Override
	public void destroy() throws Exception {
		logger.debug("Unregistering KeyStrokeListener...");
		globalScreen.removeNativeKeyListener(this);
	}
}
