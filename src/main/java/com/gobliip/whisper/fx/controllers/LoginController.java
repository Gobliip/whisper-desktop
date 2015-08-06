package com.gobliip.whisper.fx.controllers;

import com.gobliip.auth.client.AuthenticationClient;
import com.gobliip.auth.client.model.AuthenticationResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by lsamayoa on 7/10/15.
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private AuthenticationClient authenticationClient;

    public AuthenticationClient getAuthenticationClient() {
        return authenticationClient;
    }

    @Autowired
    public void setAuthenticationClient(AuthenticationClient authenticationClient) {
        this.authenticationClient = authenticationClient;
    }

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void sigIn(ActionEvent event) {
        AuthenticationResponse response = getAuthenticationClient().getTokenWithPasswordGrant(usernameField.getText(), passwordField.getText());
        logger.info("Got Authentication response: {}", response);
    }

}
