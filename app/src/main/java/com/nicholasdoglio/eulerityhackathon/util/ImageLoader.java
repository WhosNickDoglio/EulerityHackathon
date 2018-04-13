package com.nicholasdoglio.eulerityhackathon.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

import static com.nicholasdoglio.eulerityhackathon.util.GlideOptions.bitmapTransform;

/**
 * @author Nicholas Doglio
 * Abstraction of image loading layer to remove it from UI code
 */
public class ImageLoader {

    /**
     * Takes a context, url and imageview and loads it with no added effects
     *
     * @param context:   Context from given lifecycle
     * @param url:       Image URL for loading
     * @param imageView: View for image to be loaded into
     */
    public static void loadImageNoEffect(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * Takes a context, url and imageview and loads it with effects
     *
     * @param context:     Context from given lifecycle
     * @param url:         Image URL for loading
     * @param imageView:   View for image to be loaded into
     * @param effectName:  Name of effect to be implemented
     * @param progressBar: Progress Bar to display while image effect is loading
     */
    public static void loadImageWithEffect(Context context, String url, ImageView imageView, String effectName, ContentLoadingProgressBar progressBar) {
        switch (effectName) {
            case "No effect":
                loadImageNoEffect(context, url, imageView);
                break;
            case "CropTop":
                progressBar.show();
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(
                                new CropTransformation(dip2px(context, 300), dip2px(context, 100),
                                        CropTransformation.CropType.TOP)))
                        .into(imageView);
                break;
            case "CropCenter":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(
                                new CropTransformation(dip2px(context, 300), dip2px(context, 100), CropTransformation.CropType.CENTER)))
                        .into(imageView);
                break;
            case "CropBottom":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(
                                new CropTransformation(dip2px(context, 300), dip2px(context, 100),
                                        CropTransformation.CropType.BOTTOM)))
                        .into(imageView);

                break;
            case "CropSquare":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new CropSquareTransformation()))
                        .into(imageView);
                break;
            case "CropCircle":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new CircleCrop()))
                        .into(imageView);
                break;
            case "ColorFilter":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new ColorFilterTransformation(Color.argb(80, 255, 0, 0))))
                        .into(imageView);
                break;
            case "Grayscale":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new GrayscaleTransformation()))
                        .into(imageView);
                break;
            case "RoundedCorners":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new RoundedCornersTransformation(45, 0,
                                RoundedCornersTransformation.CornerType.BOTTOM)))
                        .into(imageView);
                break;
            case "Blur":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new BlurTransformation(25)))
                        .into(imageView);
                break;
            case "Toon":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new ToonFilterTransformation()))
                        .into(imageView);
                break;
            case "Sepia":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new SepiaFilterTransformation()))
                        .into(imageView);
                break;
            case "Contrast":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new ContrastFilterTransformation(2.0f)))
                        .into(imageView);
                break;
            case "Invert":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new InvertFilterTransformation()))
                        .into(imageView);
                break;
            case "Pixel":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new PixelationFilterTransformation(20)))
                        .into(imageView);
                break;
            case "Sketch":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new SketchFilterTransformation()))
                        .into(imageView);
                break;
            case "Swirl":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(
                                new SwirlFilterTransformation(0.5f, 1.0f, new PointF(0.5f, 0.5f))).dontAnimate())
                        .into(imageView);
                break;
            case "Brightness":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new BrightnessFilterTransformation(0.5f)).dontAnimate())
                        .into(imageView);
                break;
            case "Kuawahara":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new KuwaharaFilterTransformation(25)).dontAnimate())
                        .into(imageView);
                break;
            case "Vignette":
                GlideApp.with(context)
                        .load(url)
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                progressBar.hide();
                                return false;
                            }
                        })
                        .apply(bitmapTransform(new VignetteFilterTransformation(new PointF(0.5f, 0.5f),
                                new float[]{0.0f, 0.0f, 0.0f}, 0f, 0.75f)).dontAnimate())
                        .into(imageView);
                break;
        }
    }

    private static int dip2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
