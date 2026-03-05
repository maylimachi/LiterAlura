package com.LiterAlura;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class LecturaJSON {

    public static void main(String[] args) {
        try {
            String url = "https://gutendex.com/books/?search=don%20quijote";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            var json = mapper.readTree(response.body());
            var results = json.get("results");

            List<Libro> libros = mapper.readValue(
                    results.toString(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Libro.class)
            );

            libros.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}