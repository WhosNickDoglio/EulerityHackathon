package com.nicholasdoglio.eulerityhackathon.di;

import com.nicholasdoglio.eulerityhackathon.data.remote.ImageService;
import com.nicholasdoglio.eulerityhackathon.data.repo.ImageRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * @author Nicholas Doglio
 */
@Module
public class AppModule {
    private static final String BASE_URL = "https://eulerity-hackathon.appspot.com/";

    @Singleton
    @Provides
    public ImageService imageService() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ImageService.class);
    }

    @Singleton
    @Provides
    public ImageRepository imageRepository(ImageService imageService) {
        return new ImageRepository(imageService);
    }
}
