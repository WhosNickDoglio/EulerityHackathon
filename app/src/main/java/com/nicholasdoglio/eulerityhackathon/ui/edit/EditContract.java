package com.nicholasdoglio.eulerityhackathon.ui.edit;

import com.nicholasdoglio.eulerityhackathon.ui.base.BasePresenter;
import com.nicholasdoglio.eulerityhackathon.ui.base.BaseView;

/**
 * @author Nicholas Doglio
 * <p>
 * Contract the illustrates relationship between EditPresenter and EditView
 */
public interface EditContract {

    interface Presenter extends BasePresenter<View> {

        /* Retrieves the link for uploading an image */
        void getUploadLink();

        /* Uploads the image to the server */
        void uploadImage(String originalUrl, String root);
    }

    interface View extends BaseView<Presenter> {

        /* Notifies the user the upload has been complete*/
        void uploadComplete();
    }
}