package com.saudade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableUsage.class)
public interface Usage {

    @JsonProperty("prompt_tokens")
    int promptTokens();

    @JsonProperty("completion_tokens")
    int completionTokens();

    @JsonProperty("total_tokens")
    int totalTokens();

}
