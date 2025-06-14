package br.com.alura.biblioteca.principal;

import br.com.alura.biblioteca.DTO.LivroDto;
import br.com.alura.biblioteca.DTO.ResultDto;
import br.com.alura.biblioteca.model.Autor;
import br.com.alura.biblioteca.model.Livro;
import br.com.alura.biblioteca.repository.AutorRepository;
import br.com.alura.biblioteca.repository.LivroRepository;
import br.com.alura.biblioteca.service.ConsumoApi;
import br.com.alura.biblioteca.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan("br.com.alura.biblioteca")
@Component
public class Principal {

    private Scanner ler = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;
    private List<Livro> livroList = new ArrayList<>();
    private List<Autor> autoresList = new ArrayList<>();


    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    \n***Opções para escolha***
                    1 - Buscar livros pelo título.
                    2 - Lista de Livros registrados.
                    3 - Lista de autores registrados.
                    4 - Lista de autores registrados vivos em determinado ano.
                    5 - Lista de livros por idioma.
                    
                    0 - Sair.
                    """;

            System.out.println(menu);
            try {
                opcao = ler.nextInt();
                ler.nextLine(); // Consumir a quebra de linha

                switch (opcao) {
                    case 1:
                        buscarLivroWeb();
                        break;
                    case 2:
                        listaDeLivrosRegistrados();
                        break;
                    case 3:
                        listaDeAutoresRegistrados();
                        break;
                    case 4:
                        listaDeAutoresVivosEmDeterminadoAno();
                        break;
                    case 5:
                        listaLivrosEmDeterminadoIdioma();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido.");
                ler.nextLine(); // Limpar o buffer do scanner
                opcao = -1;
            }
        }
    }

    private void buscarLivroWeb() {
        try {
            ResultDto dadosLivros = getDadosLivro();

            if (dadosLivros.getResultados() != null && !dadosLivros.getResultados().isEmpty()) {
                LivroDto livroBuscado = dadosLivros.getResultados().get(0);
                Livro livro = new Livro(livroBuscado);
                System.out.println(livro);
                livroRepository.save(livro);
                System.out.println("Livro salvo com sucesso!");
            } else {
                System.out.println("Nenhum livro encontrado com o título informado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }

    private void listaDeLivrosRegistrados() {
        livroList = livroRepository.findAll();

        if (livroList.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            livroList.forEach(System.out::println);
        }
    }

    private void listaDeAutoresRegistrados() {
        autoresList = autorRepository.findAll();

        if (autoresList.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autoresList.forEach(System.out::println);
        }
    }

    private void listaDeAutoresVivosEmDeterminadoAno() {
        System.out.println("Insira o ano que deseja pesquisar:");
        Integer ano = ler.nextInt();
        autoresList = autorRepository.findAllByAno(ano);

        if (autoresList.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autoresList.forEach(System.out::println);
        }
    }

    private void listaLivrosEmDeterminadoIdioma() {
        System.out.println("""
                Digite o idioma que deseja escolher:
                Pt - Português
                En - Inglês
                Es - Espanhol
                Fr - Francês
                """);

        String idiomaEscolhido = ler.nextLine();
        livroList = livroRepository.findAllByIdioma(idiomaEscolhido);

        if (livroList.isEmpty()) {
            System.out.println("Idioma não encontrado.");
        } else {
            livroList.forEach(System.out::println);
        }
    }

    private ResultDto getDadosLivro() {
        System.out.println("Digite o nome do livro que deseja buscar: ");
        String nomeLivro = ler.nextLine();
        String json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        return conversor.obterDados(json, ResultDto.class);
    }
}
