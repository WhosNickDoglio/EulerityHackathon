package com.nicholasdoglio.eulerityhackathon.data.remote;

import com.nicholasdoglio.eulerityhackathon.data.model.Image;
import com.nicholasdoglio.eulerityhackathon.data.model.Upload;

import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * @author Nicholas Doglio
 * <p>
 * HTTP client for https://eulerity-hackathon.appspot.com/
 */
public interface ImageService {

    /**
     * Network request to https://eulerity-hackathon.appspot.com/image
     *
     * @return: List of images
     */
    @GET("image")
    Single<List<Image>> getImageList();

    /**
     * Network request to https://eulerity-hackathon.appspot.com/upload
     *
     * @return: URL for image uploading
     */
    @GET("upload")
    Single<Upload> getUploadImageUrl();

    /**
     * @param url:         Retreived Upload Url from getUploadImageUrl
     * @param appid:       unique ID for app
     * @param originalUrl: URL of the original unedited image
     * @param file:        New editied image
     * @return
     */
    @Multipart
    @POST("{uploadLink}")
    Single<Boolean> uploadImage(@Path(value = "uploadLink", encoded = true) String url,
                                @Part("appid") RequestBody appid,
                                @Part("original") RequestBody originalUrl,
                                @Part MultipartBody.Part file);
}