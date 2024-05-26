package com.conversor.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServicoDeRequisicao {

    private static final String BASE_API_URL = "https://api.exchangerate-api.com/v4/latest/";

    private final HttpClient client;
    private final Gson gson;

    public ServicoDeRequisicao() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException, InterruptedException {
        String apiUrl = BASE_API_URL + baseCurrency;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            String responseBody = response.body();
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            JsonObject rates = jsonObject.getAsJsonObject("rates");

            if (rates.has(targetCurrency)) {
                return rates.get(targetCurrency).getAsDouble();
            } else {
                throw new IllegalArgumentException("Taxa de câmbio para " + targetCurrency + " não encontrada.");
            }
        } else {
            throw new IOException("Erro na solicitação: " + response.statusCode());
        }
    }
}
