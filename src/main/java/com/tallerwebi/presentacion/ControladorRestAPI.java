package com.tallerwebi.presentacion;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.tallerwebi.dominio.Employee;
import com.tallerwebi.dominio.Joke;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class ControladorRestAPI {

    @GetMapping("/get")
    public String get() throws IOException, InterruptedException {

        String token = "asdfssdfsdsdsdsdf65456465";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.chucknorris.io/jokes/random?category=food"))
                .header("accept", "application/json") // "text/plain"
                //.header("Authorization", getBasicAuthenticationHeader("usuario", "password"))
                .header("Authorization", "Bearer " + token) // Para autenticar el request con un token JWT
                .header("Ger", "man")
                .GET()
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        JsonNode node = mapper.readTree(response.body());

        System.out.println(node);
        List<String> categories =  new ArrayList<>();
        JsonNode categoriesNode = node.get("categories");

        if(categoriesNode.isArray()){
            for (JsonNode category : categoriesNode) {
                categories.add(category.toString());
            }
        }

        Joke joke = new Joke(
                node.get("id").asText(),
                categories,
                node.get("created_at").asText(),
                node.get("icon_url").asText(),
                node.get("updated_at").asText(),
                node.get("url").asText(),
                node.get("value").asText()
        );

        System.out.println(joke);

        return mapper.writeValueAsString(joke);
    }

    @GetMapping("/create")
    public String create() throws IOException, InterruptedException {

        String token = "";

        ObjectMapper mapper = new ObjectMapper();

        Employee empleado = new Employee("Matias", 120000D, 21);

        String empleadoJson = mapper.writeValueAsString(empleado);
        System.out.println(empleadoJson);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dummy.restapiexample.com/api/v1/create"))
                .header("Content-Type","application/json") // POST
                //.header("Authorization", getBasicAuthenticationHeader("usuario", "password"))
                //.header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(empleadoJson))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return response.body();
    }

    private static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

}
