package com.gobliip.whisper.service;

import com.gobliip.auth.client.AuthenticationClient;
import com.gobliip.auth.client.model.AuthenticationResponse;
import com.gobliip.retrofit.cloud.oauth2.jwt.JWTTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

/**
 * Created by lsamayoa on 9/08/15.
 */
@Service
@ManagedResource
public class AuthenticationService {

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private JWTTokenStore tokenStore;

    @ManagedOperation
    public boolean login(
            @ManagedOperationParameter(name = "username", description = "") String username,
            @ManagedOperationParameter(name = "password", description = "")  String password) {
        final AuthenticationResponse response = authenticationClient.getTokenWithPasswordGrant(username, password);
        tokenStore.setToken(response.getAccessToken());
        return true;
    }

    @ManagedOperation
    public void logout() {
        tokenStore.setToken(null);
    }
}
