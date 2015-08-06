package com.gobliip.whisper.fx.support;

import javafx.fxml.FXMLLoader;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class SpringFxmlLoader {
    private ApplicationContext context;

    public SpringFxmlLoader(ApplicationContext context) {
        this.context = context;
    }

    public Object load(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = ClassLoader.getSystemResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(context::getBean);
            return loader.load(fxmlStream);
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }
}
