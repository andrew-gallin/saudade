package com.saudade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonDeserialize(as = ImmutableChatResponse.class)
public interface ChatResponse {

    @JsonProperty("id")
    String id();

    @JsonProperty("object")
    String object();

    @JsonProperty("created")
    long created();

    @JsonProperty("model")
    String model();

    @JsonProperty("choices")
    List<Choice> choices();

    @JsonProperty("usage")
    Usage usage();

    @JsonProperty("system_fingerprint")
    String systemFingerprint();

}
