package com.gobliip.whisper;

import com.gobliip.auth.client.config.AuthenticationClientConfiguration;
import com.gobliip.chronos.client.config.TimeTrackerClientConfig;
import com.gobliip.retrofit.cloud.oauth2.jwt.InMemoryJWTTokenStore;
import com.gobliip.retrofit.cloud.oauth2.jwt.JWTTokenStore;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@Configuration
@ComponentScan
@EnableScheduling
@Import({
        PropertyPlaceholderAutoConfiguration.class,
        JmxAutoConfiguration.class,
        AuthenticationClientConfiguration.class,
        TimeTrackerClientConfig.class,
        ServerPropertiesAutoConfiguration.class,
        EmbeddedServletContainerAutoConfiguration.class,
        DispatcherServletAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        HttpEncodingAutoConfiguration.class,
        WebSocketAutoConfiguration.class,
        WebMvcAutoConfiguration.class,
})
@EnableWebSocket
@EnableWebMvc
public class WhisperDesktopApplication {

    @Bean
    public JWTTokenStore tokenStore() {
        return new InMemoryJWTTokenStore();
    }


    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(WhisperDesktopApplication.class)
                .headless(false);
        ConfigurableApplicationContext ctx = builder.run(args);
    }
}
