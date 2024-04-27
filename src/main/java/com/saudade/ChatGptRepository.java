package com.saudade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChatGptRepository {

    private OkHttpClient httpClient;
    private ObjectMapper mapper;

    @Inject
    public ChatGptRepository(OkHttpClient httpClient, ObjectMapper objectMapper){
        this.httpClient = httpClient;
        this.mapper = objectMapper;
    }

    public String sendMessage(String text)  {
        String url = "https://api.openai.com/v1/completions";

        System.out.println(text);

        ChatGPTRequest chatGptrequest = ChatGPTRequest.builder()
                .model("text-davinci-003")
                .prompt(text)
                .maxTokens(4000)
                .temperature(1.0)
                .build();

        String jsonBody = null;
        try {
            jsonBody = mapper.writeValueAsString(chatGptrequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        RequestBody body = RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                //TODO: grab API key from env
                .addHeader("Authorization", "Bearer YOUR_API_KEY")
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Assuming the response is in JSON format and contains a field called 'choices' with text content
            if (response.body() != null) {
                return mapper.readTree(response.body().string())
                        .get("choices").get(0).get("text").asText();
            }
            else {
                //TODO: This shit should be injected
                Logger.getAnonymousLogger().log(Level.INFO, "no response");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return "you";
    }

}
