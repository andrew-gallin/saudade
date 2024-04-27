package com.saudade.util;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import okhttp3.OkHttpClient;


@Module
public class HttpClientProvider {

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient(){
        return new OkHttpClient();
    }
}
