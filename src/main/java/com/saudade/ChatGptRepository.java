package com.saudade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import com.saudade.dto.ChatGPTRequest;
import com.saudade.dto.ChatGptMessage;
import com.saudade.dto.ImmutableChatGptMessage;
import com.saudade.dto.ImmutableChatResponse;
import io.vavr.control.Try;
import okhttp3.*;

import javax.inject.Inject;
import java.io.IOException;
import java.util.NoSuchElementException;


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
                        .role(ChatGptMessage.Role.USER)
                        .content(text)
                        .build())
                .maxTokens(4000)
                .temperature(1.0)
                .topP(1)
                .frequencyPenalty(0)
                .presencePenalty(0)
                .build();

        String jsonBody = Try.of(() -> mapper.writeValueAsString(chatGptrequest))
                .getOrElseThrow(e -> new RuntimeException(e));

        RequestBody body = RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();

//        String jsonResponse = "{\"id\":\"chatcmpl-9JUlUZl3AWaDjbHeBDkkaFa02LFjJ\",\"object\":\"chat.completion\",\"created\":1714433520,\"model\":\"gpt-3.5-turbo-0125\",\"choices\":[{\"index\":0,\"message\":{\"role\":\"assistant\",\"content\":\"Hello! How can I assist you today?\"},\"logprobs\":null,\"finish_reason\":\"stop\"}],\"usage\":{\"prompt_tokens\":8,\"completion_tokens\":9,\"total_tokens\":17},\"system_fingerprint\":\"fp_3b956da36b\"}";

//        ImmutableChatResponse chatResponse = null;
//        try {
//             chatResponse = mapper.readValue(jsonResponse, ImmutableChatResponse.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(chatResponse.choices());

        return Try.of(() -> httpClient.newCall(request).execute())
                .filterTry(Response::isSuccessful, ex -> new IOException("Unexpected code " + ex))
                .flatMapTry(response -> Try.of(() -> mapper.readValue(response.body().string(), ImmutableChatResponse.class)))
                .map(chatResponse -> chatResponse.choices().stream()
                        .filter(choice -> choice.message().role() == ChatGptMessage.Role.ASSISTANT)
                        .findFirst()
                        .map(choice -> choice.message().content())
                        .orElseThrow(() -> new NoSuchElementException("No Choice with role ASSISTANT found")))
                .getOrElseThrow(() -> new NoSuchElementException("No Choice with role ASSISTANT found"));
    }
}
