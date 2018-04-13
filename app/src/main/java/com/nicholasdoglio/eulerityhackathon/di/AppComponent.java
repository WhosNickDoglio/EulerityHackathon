package com.nicholasdoglio.eulerityhackathon.di;

import android.app.Application;

import com.nicholasdoglio.eulerityhackathon.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Nicholas Doglio
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, MainActivityBindingModule.class})
public interface AppComponent {

    void inject(App application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
