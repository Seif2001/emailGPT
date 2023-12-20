//TAREK KASSAB - 900213491
//Seifeldin Elshabshiri - 900202310
package Helpers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class GPT {
    private static final String API_KEY = "";
    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";


    public static String getOpenAIResponse(String prompt) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");

        JSONArray messagesArray = new JSONArray();

        JSONObject systemMessage = new JSONObject();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a helpful assistant.");

        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);

        messagesArray.put(systemMessage);
        messagesArray.put(userMessage);

        requestBody.put("messages", messagesArray);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_ENDPOINT))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                int start = response.body().indexOf("content") + 11;
                int end = response.body().indexOf("\"", start);
                return response.body().substring(start, end);
            } else {
                return response.body();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();  // Handle the exception appropriately
            return "Error occurred during the API request.";
        }
    }
}
