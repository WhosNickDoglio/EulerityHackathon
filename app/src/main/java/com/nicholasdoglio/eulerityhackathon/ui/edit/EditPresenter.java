package com.nicholasdoglio.eulerityhackathon.ui.edit;

import com.nicholasdoglio.eulerityhackathon.data.model.Upload;
import com.nicholasdoglio.eulerityhackathon.data.repo.ImageRepository;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import timber.log.Timber;

/**
 * @author Nicholas Doglio
 */
public class EditPresenter implements EditContract.Presenter {
    private ImageRepository imageRepository;
    private EditContract.View editView;
    private CompositeDisposable compositeDisposable;
    private String uploadLink;

    @Inject
    public EditPresenter(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void attach(EditContract.View view) {
        this.editView = view;
    }

    @Override
    public void detach() {
        editView = null;
    }

    @Override
    public void clearDisposables() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public void getUploadLink() {
        compositeDisposable.add(imageRepository.getUploadUrl()
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Upload>() {

                    @Override
                    public void onSuccess(Upload upload) {
                        uploadLink = upload.getUrl().substring(39);
                        Timber.d("Upload link: %s", uploadLink);
                    }

                    @Override
                    public void onError(Throwable error) {
                        Timber.d("Unable to retrieve upload link");
                        error.printStackTrace();
                    }
                }));
    }

    @Override
    public void uploadImage(String originalUrl, String root) {

        File file = new File(root + "/Download/Image.jpg");

        RequestBody url = RequestBody.create(MediaType.parse("multipart/form-data"), originalUrl);
        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), reqFile);

        compositeDisposable.add(imageRepository.uploadImage(uploadLink, url, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Boolean>() {

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        editView.uploadComplete();
                        Timber.d("Upload successful");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Image appears to upload even onError
                        editView.uploadComplete();
                        Timber.d("Image uploaded in OnError");
                    }
                }));
    }
}