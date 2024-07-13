package LiterAlura.servico;

import LiterAlura.dto.LivroDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiService {

    public List<LivroDTO> getLivros(String titulo) {
        List<LivroDTO> livros = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            String encodedTitulo = URLEncoder.encode(titulo, StandardCharsets.UTF_8.toString());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://gutendex.com/books/?search=" + encodedTitulo)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            JsonNode results = root.get("results");

            if (results.isArray()) {
                for (JsonNode node : results) {
                    LivroDTO livro = mapper.treeToValue(node, LivroDTO.class);
                    livros.add(livro);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return livros;
    }
}
