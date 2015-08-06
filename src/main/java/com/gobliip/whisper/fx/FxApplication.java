package com.gobliip.whisper.fx;

import com.gobliip.auth.client.AuthenticationClient;
import com.gobliip.auth.client.config.AuthenticationClientConfiguration;
import com.gobliip.auth.client.model.AuthenticationResponse;
import com.gobliip.whisper.fx.controllers.LoginController;
import com.gobliip.whisper.fx.support.SpringFxmlLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by lsamayoa on 7/10/15.
 */
@Configuration
@Import({AuthenticationClientConfiguration.class, PropertyPlaceholderAutoConfiguration.class})
@ComponentScan(basePackages = "com.gobliip.whisper.fx.controllers")
public class FxApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) throws Exception {
        SpringFxmlLoader loader = new SpringFxmlLoader(context);
        Parent root = (Parent) loader.load("login_form.fxml");

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
        AuthenticationResponse authResponse = context.getBean(AuthenticationClient.class).getTokenWithPasswordGrant("admin", "r00t");

        authResponse.getAccessToken();
    }

    @Override
    public void init() throws Exception {
        context = new SpringApplicationBuilder(FxApplication.class)
                .headless(false)
                .run((String[]) getParameters().getRaw().toArray(new String[]{}));
    }

    @Override
    public void stop() throws Exception {
        context.stop();
    }

    public static void main(String[] args){
        Application.launch(FxApplication.class, args);
    }
}
