package com.algaworks.cobranca;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class CobrancaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CobrancaApplication.class, args);
	}

	/*Configurar para toda aplicação o uso das definições do Brasil para data, número com vírgula e etc. 
	 Agora vou na MODEL e faço as configurações de cada campo para exibição: data, hora, valor*/
	@Bean
	public FixedLocaleResolver localResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

}
