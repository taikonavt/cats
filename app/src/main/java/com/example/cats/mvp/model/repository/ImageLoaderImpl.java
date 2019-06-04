package com.example.cats.mvp.model.repository;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.example.cats.ui.customviews.CustomView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLoaderImpl implements IImageLoader {
    private AsyncTask asyncTask;

    // получить изображения через сетевые запросы в runtime
    @Override
    public void loadInto(String pictureUrl, View view, int imageWidth) {
        try {
            URL url = new URL(pictureUrl);
            DownloadImageTask task = new DownloadImageTask();
            if (asyncTask != null){
                asyncTask.cancel(true);
            }
            asyncTask = task.execute(url, view, imageWidth);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private class DownloadImageTask extends AsyncTask<Object, Void, Bitmap>{

        private WeakReference<View> viewWeakReference;
        private int imageWidth;

        @Override
        protected Bitmap doInBackground(Object... objects) {
            URL url = (URL) objects[0];
            viewWeakReference = new WeakReference<View>((View) objects[1]);
            imageWidth = (int) objects[2];
            Bitmap bitmap = null;
            try {
                InputStream in = (InputStream) url.getContent();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap resizedBitmap = null;
            if (bitmap != null) {
                resizedBitmap = resizeBitmap(bitmap);
            }
            return resizedBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            View view = viewWeakReference.get();

            if (view != null && bitmap != null){
                if (view instanceof ImageView){
                    ((ImageView) view).setImageBitmap(bitmap);
                } else if (view instanceof CustomView){
                    ((CustomView) view).setImageBitmap(bitmap);
                }
            }
        }

        private Bitmap resizeBitmap(Bitmap bitmap){
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if(width > imageWidth) {
                int ratio = width / imageWidth;
                int imageHeight = height / ratio;
                return Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, true);
            } else {
                return bitmap;
            }
        }
    }
}
