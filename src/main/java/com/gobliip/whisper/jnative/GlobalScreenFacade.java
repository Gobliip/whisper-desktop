package com.gobliip.whisper.jnative;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseListener;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class GlobalScreenFacade implements DisposableBean, InitializingBean{

	public void addNativeKeyListener(NativeKeyListener listener){
		GlobalScreen.addNativeKeyListener(listener);
		
	}

	public void addNativeMouseListener(NativeMouseListener listener){
		GlobalScreen.addNativeMouseListener(listener);
	}

	public void removeNativeKeyListener(NativeKeyListener listener) {
		GlobalScreen.removeNativeKeyListener(listener);
	}

	public void removeNativeMouseListener(NativeMouseListener listener) {
		GlobalScreen.removeNativeMouseListener(listener);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		GlobalScreen.registerNativeHook();
	}

	@Override
	public void destroy() throws Exception {
		GlobalScreen.unregisterNativeHook();
	}

}
