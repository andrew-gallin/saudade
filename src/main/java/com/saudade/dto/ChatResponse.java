package com.saudade.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;



@Value.Immutable
@JsonDeserialize(as = ImmutableChatResponse.class)
public interface ChatResponse {

    @JsonProperty("id")
    String id();

    @JsonProperty("object")
    String object();

    @JsonProperty("created")
    Long created();

    @JsonProperty("model")
    @Nullable
    String model();

    @JsonProperty("choices")
    @Nullable
    List<Choice> choices();

    @JsonProperty("usage")
    @Nullable
    Usage usage();

    @JsonProperty("system_fingerprint")
    @Nullable
    String systemFingerprint();

}
