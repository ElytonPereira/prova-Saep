package br.com.senai.saep;

import java.awt.EventQueue;

import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import br.com.senai.saep.view.ViewPrincipal;

@SpringBootApplication
public class SaepApplication {

	public static void main(String[] args) {
	//	SpringApplication.run(SaepApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(SaepApplication.class);
		
		builder.headless(false);
		builder.run(args);
		
	}
	
	@Autowired
	private ViewPrincipal viewPrincipal;
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				EventQueue.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						viewPrincipal.setVisible(true);
						
					}
				});
				
			}
		};
		
		
	}

}
