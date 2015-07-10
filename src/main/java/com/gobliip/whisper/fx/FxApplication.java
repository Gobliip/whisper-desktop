package com.gobliip.whisper.fx;

import com.gobliip.whisper.fx.controllers.LoginController;
import com.gobliip.whisper.fx.support.SpringFxmlLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lsamayoa on 7/10/15.
 */
@Configuration
@ComponentScan(basePackages = "com.gobliip.whisper.fx.controllers")
public class FxApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) throws Exception {
        SpringFxmlLoader loader = new SpringFxmlLoader(context);
        Parent root = (Parent) loader.load("login_form.fxml", LoginController.class);

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
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
