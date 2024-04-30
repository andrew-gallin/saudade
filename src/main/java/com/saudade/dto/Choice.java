package com.saudade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonDeserialize(as = ImmutableChoice.class)
public interface Choice {

    int index();

    @JsonProperty("message")
    ChatGptMessage message();

    @Nullable
    Object logprobs(); // TODO: Define a proper class if I cane learn structure

    @JsonProperty("finish_reason")
    String finishReason();
}
