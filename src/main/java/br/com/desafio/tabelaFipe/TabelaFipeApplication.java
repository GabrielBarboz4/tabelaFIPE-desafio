package br.com.desafio.tabelaFipe;

import br.com.desafio.tabelaFipe.principal.MainMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		MainMenu runMenu = new MainMenu();
		runMenu.exibeMenu();
	}
}
