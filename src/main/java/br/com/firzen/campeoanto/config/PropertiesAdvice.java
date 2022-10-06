package br.com.firzen.campeoanto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@PropertySource("classpath:global.properties")
public class PropertiesAdvice {

	@Value("${version}")
	private String version;
	
	@ModelAttribute("version")
	public String version() {
		return version;
	}
}
