package com.gobliip.whisper.jnative.listeners;

import com.gobliip.whisper.jnative.GlobalScreenFacade;
import com.gobliip.whisper.model.MouseAction;
import com.gobliip.whisper.service.MouseActionsRegistry;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Created by lsamayoa on 7/8/15.
 */
@Component
public class MouseActionListener implements NativeMouseListener, InitializingBean, DisposableBean{
    @Autowired
    private GlobalScreenFacade globalScreen;

    @Autowired
    private MouseActionsRegistry mouseActionsRegistry;

    @Override
    public void destroy() throws Exception {
        globalScreen.removeNativeMouseListener(this);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        globalScreen.addNativeMouseListener(this);
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        mouseActionsRegistry.add(new MouseAction(Instant.now(), nativeMouseEvent.getX(), nativeMouseEvent.getY()));
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {

    }
}
