package com.saudade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import okhttp3.*;

import javax.inject.Inject;


public class ChatGptRepository {

    private final OkHttpClient httpClient;
    private final ObjectMapper mapper;
    private final String apiKey;

    @Inject
    public ChatGptRepository(OkHttpClient httpClient,
                             ObjectMapper objectMapper,
                             String apiKey) {
        this.httpClient = httpClient;
        this.mapper = objectMapper;
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new GuavaModule());
        this.apiKey = apiKey;
    }

    public String sendMessage(String text) {
        String url = "https://api.openai.com/v1/chat/completions";

        ChatGPTRequest chatGptrequest = ChatGPTRequest.builder()
                .model("gpt-3.5-turbo")
                .addMessage(ImmutableChatGptMessage.builder()
                        .role(Role.USER)
                        .content(text)
                        .build())
                .maxTokens(4000)
                .temperature(1.0)
                .topP(1)
                .frequencyPenalty(0)
                .presencePenalty(0)
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
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

        String jsonResponse = "{\"id\":\"chatcmpl-9JUlUZl3AWaDjbHeBDkkaFa02LFjJ\",\"object\":\"chat.completion\",\"created\":1714433520,\"model\":\"gpt-3.5-turbo-0125\",\"choices\":[{\"index\":0,\"message\":{\"role\":\"assistant\",\"content\":\"Hello! How can I assist you today?\"},\"logprobs\":null,\"finish_reason\":\"stop\"}],\"usage\":{\"prompt_tokens\":8,\"completion_tokens\":9,\"total_tokens\":17},\"system_fingerprint\":\"fp_3b956da36b\"}";

        String json = "{\"id\":\"chatcmpl-9JUlUZl3AWaDjbHeBDkkaFa02LFjJ\"}";
        System.out.println(json);

        ImmutableChatResponse chatResponse = null;
        try {
             chatResponse = mapper.readValue(jsonResponse, ImmutableChatResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(chatResponse.choices());
//        try (Response response = httpClient.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//            // Assuming the response is in JSON format and contains a field called 'choices' with text content
//            if (response.body() != null) {
////                JsonNode mapped = mapper.readTree(response.body().string());
//                ChatResponse chatResponse = mapper.readValue(response.body().string(), ChatResponse.class);
//
//                System.out.println(chatResponse.toString());
//                return mapper.readTree(response.body().string())
//                        .get("choices").get(0).get("text").asText();
//            }
//            else {
//                //TODO: This shit should be injected
//                Logger.getAnonymousLogger().log(Level.INFO, "no response");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        return "you";
    }

}
