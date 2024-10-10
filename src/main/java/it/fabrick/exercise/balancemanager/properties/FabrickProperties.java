package it.fabrick.exercise.balancemanager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fabrick")
@Data
public class FabrickProperties {
	private String url;
	private String apiKey;
	private String authSchema;
}
