package com.nicholasdoglio.eulerityhackathon.ui.list;

import com.nicholasdoglio.eulerityhackathon.data.model.Image;
import com.nicholasdoglio.eulerityhackathon.ui.base.BasePresenter;
import com.nicholasdoglio.eulerityhackathon.ui.base.BaseView;

import java.util.List;

/**
 * @author Nicholas Doglio
 * <p>
 * Contract the illustrates relationship between ImageListPresenter and ImageListView
 */
public interface ImageListContract {

    interface Presenter extends BasePresenter<View> {

        /* Pulls list of images from repository  */
        void getImages();

        /* Makes a fresh network call for new images */
        void loadNewImages();
    }

    interface View extends BaseView<Presenter> {

        /* Takes a given list and sets it to the ImageListAdapter */
        void setList(List<Image> imageList);

        /* Displays toasts when unable to retrieve new images from network */
        void showNetworkIssue();

        /* displays progress bar when requests for image is in progress */
        void showLoadingBar();

        /* Hides progress bar when request ends */
        void hideLoadingBar();

    }
}
