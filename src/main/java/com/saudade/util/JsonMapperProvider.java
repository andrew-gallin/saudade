package com.saudade.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class JsonMapperProvider {

    @Provides
    @Singleton
    public ObjectMapper provideObjectMapper(){
        return new ObjectMapper();
    }
}
