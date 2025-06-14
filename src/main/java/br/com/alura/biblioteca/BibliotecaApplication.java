package br.com.alura.biblioteca;

import br.com.alura.biblioteca.principal.Principal;
import br.com.alura.biblioteca.repository.AutorRepository;
import br.com.alura.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository repository;

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private Principal principal;

	@Override
	public void run(String... args) throws Exception {
		principal.exibeMenu();
	}

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

}
