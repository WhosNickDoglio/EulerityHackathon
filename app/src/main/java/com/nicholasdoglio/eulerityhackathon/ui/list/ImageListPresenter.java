package com.nicholasdoglio.eulerityhackathon.ui.list;

import com.nicholasdoglio.eulerityhackathon.data.model.Image;
import com.nicholasdoglio.eulerityhackathon.data.repo.ImageRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @author Nicholas Doglio
 */
public class ImageListPresenter implements ImageListContract.Presenter {
    private ImageRepository imageRepository;
    private ImageListContract.View imageListView;
    private CompositeDisposable imageListDisposable;

    @Inject
    public ImageListPresenter(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        imageListDisposable = new CompositeDisposable();
    }

    @Override
    public void attach(ImageListContract.View view) {
        this.imageListView = view;
    }

    @Override
    public void detach() {
        imageListView = null;
    }

    @Override
    public void clearDisposables() {
        if (!imageListDisposable.isDisposed()) {
            imageListDisposable.dispose();
        }
    }

    @Override
    public void loadNewImages() {
        imageListView.showLoadingBar();
        imageListDisposable.add(imageRepository.getFreshImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> Timber.d("Fresh data call made"))
                .subscribeWith(new DisposableSingleObserver<List<Image>>() {

                    @Override
                    public void onSuccess(List<Image> images) {
                        imageListView.hideLoadingBar();
                        imageListView.setList(images);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Timber.d("Unable to load");
                        imageListView.hideLoadingBar();
                        imageListView.showNetworkIssue();
                        error.printStackTrace();
                    }
                }));
    }

    @Override
    public void getImages() {
        imageListView.showLoadingBar();
        imageListDisposable.add(imageRepository.getImageList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> Timber.d("Load images called"))
                .subscribeWith(new DisposableSingleObserver<List<Image>>() {
                    @Override
                    public void onSuccess(List<Image> images) {
                        imageListView.hideLoadingBar();
                        imageListView.setList(images);
                    }

                    @Override
                    public void onError(Throwable error) {
                        imageListView.hideLoadingBar();
                        imageListView.showNetworkIssue();
                        error.printStackTrace();
                    }
                }));
    }
}