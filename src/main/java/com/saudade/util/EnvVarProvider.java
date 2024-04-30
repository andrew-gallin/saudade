package com.saudade.util;

import dagger.Module;
import dagger.Provides;
import io.github.cdimascio.dotenv.Dotenv;

@Module
public class EnvVarProvider {

    @Provides
    String provideChatGptApiKey() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("CHAT_GPT_API_KEY");
    }
}
