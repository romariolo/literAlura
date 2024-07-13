package LiterAlura.principal;

import LiterAlura.dto.LivroDTO;
import LiterAlura.entity.Autor;
import LiterAlura.entity.Livro;
import LiterAlura.repository.AutorRepository;
import LiterAlura.repository.LivroRepository;
import LiterAlura.servico.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu(){
        var opcao = -1;
        while ((opcao != 0)){
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Buscar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair
                       """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroTitulo();
                    break;
                case 2:
                    buscarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosAno();
                    break;
                case 5:
                    listarLivrosIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void buscarLivroTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = leitura.nextLine();
        List<Livro> livros = livroRepository.findByTituloContaining(titulo);
        livros.forEach(System.out::println);
    }

    private void buscarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosAno() {
        System.out.print("Digite o ano: ");
        int ano = leitura.nextInt();
        leitura.nextLine();
        List<Autor> autores = autorRepository.findByAnoDeMorteIsNull();
        autores.stream().filter(autor -> autor.getAniversario().getYear() <= ano).forEach(System.out::println);
    }

    private void listarLivrosIdioma() {
        System.out.print("Digite o idioma: ");
        String idioma = leitura.nextLine();
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        livros.forEach(System.out::println);
    }
}
