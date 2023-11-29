package br.com.senai.saep;

import java.awt.EventQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.saep.view.ViewLogin;

@SpringBootApplication
public class SaepApplication {

	public static void main(String[] args) {
	//	SpringApplication.run(SaepApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SaepApplication.class);
		
		builder.headless(false);
		builder.run(args);
		
	}
	
	@Autowired
	private ViewLogin viewLogin;
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				EventQueue.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						viewLogin.setVisible(true);
						
					}
				});
				
			}
		};
		
		
	}

}
