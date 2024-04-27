package com.saudade;

import com.saudade.util.HttpClientProvider;
import com.saudade.util.JsonMapperProvider;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        HttpClientProvider.class,
        JsonMapperProvider.class
})
public interface AppComponent {
    ChatGptRepository getChatGPT();
}
