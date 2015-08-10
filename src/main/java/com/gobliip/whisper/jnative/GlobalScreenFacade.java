package com.gobliip.whisper.jnative;

import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseListener;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class GlobalScreenFacade implements DisposableBean, InitializingBean {

    public void addNativeKeyListener(NativeKeyListener listener) {
        GlobalScreen.addNativeKeyListener(listener);
    }

    public void addNativeMouseListener(NativeMouseListener listener) {
        GlobalScreen.addNativeMouseListener(listener);
    }

    public void removeNativeKeyListener(NativeKeyListener listener) {
        GlobalScreen.removeNativeKeyListener(listener);
    }

    public void removeNativeMouseListener(NativeMouseListener listener) {
        GlobalScreen.removeNativeMouseListener(listener);
    }

    public byte[] takeScreenShot() throws AWTException, IOException {
        Robot robot = new Robot();
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(screenShot, "jpg", baos);
            baos.flush();
            return baos.toByteArray();
        }
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
