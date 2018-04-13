package com.nicholasdoglio.eulerityhackathon.di;

import com.nicholasdoglio.eulerityhackathon.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Nicholas Doglio
 */
@Module
public abstract class MainActivityBindingModule {

    @ContributesAndroidInjector(modules = FragmentsBindingModule.class)
    public abstract MainActivity contributesMainActivity();

}
