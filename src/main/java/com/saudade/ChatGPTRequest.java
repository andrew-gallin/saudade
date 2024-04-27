package com.saudade;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ChatGPTRequest {
    String model();
    String prompt();
    int maxTokens();
    double temperature();

    static ChatGPTRequestBuilder builder(){
            return new ChatGPTRequestBuilder();
    }
}
