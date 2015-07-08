package com.gobliip.whisper;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class WhisperDesktopApplication {
	
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(WhisperDesktopApplication.class).headless(false).web(false);
		ConfigurableApplicationContext ctx = builder.run(args);
		//ctx.close();c
	}
}
