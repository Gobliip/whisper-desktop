package com.gobliip.whisper;

import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
@Import({JmxAutoConfiguration.class})
public class WhisperDesktopApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(WhisperDesktopApplication.class)
                .headless(false);
                //.web(false);
		ConfigurableApplicationContext ctx = builder.run(args);
		//ctx.close();
	}
}
