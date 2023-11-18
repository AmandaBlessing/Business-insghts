package org.insight;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AiAnalyst {

    String apiKey = "";

    // Set the API endpoint
    String endpoint = "https://api.openai.com/v1/chat/completions";

    // Set the request payload

    public AiAnalyst() {
    }


    public String sendRequest(String systemMessage, String userMessage){
        String response = "";
        String requestBody = "{" +
                "\"model\": \"gpt-3.5-turbo\"," +
                "\"messages\": [" +
                "  {" +
                "    \"role\": \"system\"," +
                "    \"content\": \"" + systemMessage + "\"" +
                "  }," +
                "  {" +
                "    \"role\": \"user\"," +
                "    \"content\": \"" + userMessage + "\"" +
                "  }" +
                "]" +
                "}";


        // Create an instance of HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Create an HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            // Send the request and retrieve the response
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();

            // Print the response status code and body

            System.out.println("Response Body: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


    public static void main(String[] args) {
        AiAnalyst ai = new AiAnalyst();
        ai.sendRequest("You are a maths teacher", "Explain probability");
    }
}
