package com.nicholasdoglio.eulerityhackathon.di;

import com.nicholasdoglio.eulerityhackathon.ui.edit.EditContract;
import com.nicholasdoglio.eulerityhackathon.ui.edit.EditFragment;
import com.nicholasdoglio.eulerityhackathon.ui.edit.EditPresenter;
import com.nicholasdoglio.eulerityhackathon.ui.list.ImageListContract;
import com.nicholasdoglio.eulerityhackathon.ui.list.ImageListFragment;
import com.nicholasdoglio.eulerityhackathon.ui.list.ImageListPresenter;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Nicholas Doglio
 */
@Module
public abstract class FragmentsBindingModule {

    @ContributesAndroidInjector
    public abstract ImageListFragment contributesImageListFragment();

    @Binds
    public abstract ImageListContract.Presenter imageListPresenter(ImageListPresenter imageListPresenter);

    @ContributesAndroidInjector
    public abstract EditFragment contributesEditFragment();

    @Binds
    public abstract EditContract.Presenter editPresenter(EditPresenter editPresenter);
}
