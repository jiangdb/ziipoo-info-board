package com.vehicle.network.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.vehicle.forecast.R;

import java.io.IOException;
import java.lang.ref.WeakReference;

public class ImageRequest {
    private static final String TAG = ImageRequest.class.getSimpleName();
    private WeakReference<ImageView> imageViewRef;
    private WeakReference<View> progressViewRef;
    private WeakReference<Context> contextRef;
    private String imageUrl;
    private ImageTransform transformStyle;

    public enum ImageTransform{
        NONE,
        ROUND_CORNER
    }

    public interface ImageRequestListener {
        void onLoadedSuccess(Bitmap bitmap);

        void onLoadedError();
    }

    private ImageRequest(@NonNull Context context, @NonNull ImageView imageView, @NonNull String imageUrl, View progressView, ImageTransform transformStyle) {
        this.imageViewRef = new WeakReference(imageView);
        this.imageUrl = imageUrl;
        this.contextRef = new WeakReference(context);
        this.progressViewRef = new WeakReference(progressView);
        this.transformStyle = transformStyle;
        startRequest();
    }

    private ImageRequest(@NonNull Context context, String imageUrl, @NonNull final ImageRequestListener listener) {
        this.imageUrl = imageUrl;
        this.contextRef = new WeakReference(context);
        downloadImage(listener);
    }


    private void downloadImage(@NonNull final ImageRequestListener listener) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Target downloadTarget = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    listener.onLoadedSuccess(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    listener.onLoadedError();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };
            Picasso.with(contextRef.get())
                    .load(imageUrl)
                    .into(downloadTarget);
        } else {
            try {
                Bitmap bitmap = Picasso.with(contextRef.get())
                        .load(imageUrl).get();
                listener.onLoadedSuccess(bitmap);
            } catch (IOException e) {
                listener.onLoadedError();
            }
        }
    }

    private void startRequest() {
        showProgressView();
        Callback callback = new Callback() {
            @Override
            public void onSuccess() {
                hideProgressView();

            }

            @Override
            public void onError() {
                hideProgressView();
            }
        };
        if(transformStyle==ImageTransform.ROUND_CORNER){
            Picasso.with(contextRef.get())
                    .load(imageUrl)
                    .placeholder(R.mipmap.placeholder)
                    .transform(new RoundCornerTransform())
                    .into(imageViewRef.get(), callback);
        }else{
            Picasso.with(contextRef.get()).load(imageUrl)
                    .placeholder(R.mipmap.placeholder)
                    .into(imageViewRef.get(), callback);
        }

    }

    private void hideProgressView() {
        if (progressViewRef.get() != null) {
            progressViewRef.get().setVisibility(View.GONE);
        }
    }

    private void showProgressView() {
        if (progressViewRef.get() != null) {
            progressViewRef.get().setVisibility(View.VISIBLE);
        }
    }

    public static void load(@NonNull Context context, @NonNull ImageView imageView, @NonNull String imageUrl, View progressView) {
        new ImageRequest(context, imageView, imageUrl, progressView, ImageTransform.NONE);
    }

    public static void load(@NonNull Context context, @NonNull ImageView imageView, @NonNull String imageUrl, View progressView, ImageTransform transformStyle) {
        new ImageRequest(context, imageView, imageUrl, progressView, transformStyle);
    }

    public static void fetch(@NonNull Context context, @NonNull String imageUrl){
        Picasso.with(context).load(imageUrl).fetch();
    }
}