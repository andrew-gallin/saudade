package com.saudade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableChatGptMessage.class)
public interface ChatGptMessage {

    Role role();

    String content();

    enum Role {

        @JsonProperty("user")
        USER,

        @JsonProperty("chatbot")
        CHATBOT,

        @JsonProperty("assistant")
        ASSISTANT
    }
}

