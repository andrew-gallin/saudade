package com.saudade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.norberg.automatter.AutoMatter;

import java.util.List;

@AutoMatter
public interface ChatGPTRequest {

    @JsonProperty("model")
    String model();

    @JsonProperty("messages")
    List<ChatGptMessage> messages();

    @JsonProperty("max_tokens")
    int maxTokens();

    @JsonProperty("temperature")
    double temperature();

    @JsonProperty("top_p")
    int topP();

    @JsonProperty("frequency_penalty")
    int frequencyPenalty();

    @JsonProperty("presence_penalty")
    int presencePenalty();

    static ChatGPTRequestBuilder builder(){
            return new ChatGPTRequestBuilder();
    }

}
