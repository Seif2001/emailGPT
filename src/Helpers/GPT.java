package Helpers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GPT {
    private static final String API_KEY = "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";
    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";


    public static String getOpenAIResponse(String prompt) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_ENDPOINT))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString("{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"system\", \"content\": \"You are a helpful assistant.\"}, {\"role\": \"user\", \"content\": \"" + prompt + "\"}]}"))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            int start = response.body().indexOf("content") + 11;

            int end = response.body().indexOf("\"", start);

            return response.body().substring(start, end);
        }
        else{

            return response.body();
        }
    }
}
