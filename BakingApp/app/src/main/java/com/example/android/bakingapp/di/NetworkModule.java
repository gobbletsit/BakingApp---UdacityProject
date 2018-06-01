package com.example.android.bakingapp.di;

import com.example.android.bakingapp.network.BakingNetworkService;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

    private static final int CONNECT_TIMEOUT_IN_MILLIS = 30000;

    @Provides
    OkHttpClient providesHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        // provjeri sta ovo tocno znaci sve
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().connectTimeout(CONNECT_TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS)
                .addInterceptor(httpLoggingInterceptor).build();

    }

    @Provides
    Retrofit providesRetrofit (OkHttpClient okHttpClient){
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(okHttpClient).build();
    }

    @Provides
    BakingNetworkService providesBakingNetworkService(Retrofit retrofit){
        return retrofit.create(BakingNetworkService.class);
    }

}
