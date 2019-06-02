package com.example.cats.mvp.model.repository;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLoaderImpl implements IImageLoader {

    @Override
    public void loadInto(String pictureUrl, ImageView image, int imageWidth) {
        try {
            URL url = new URL(pictureUrl);
            DownloadImageTask task = new DownloadImageTask();
            task.execute(url, image, imageWidth);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private class DownloadImageTask extends AsyncTask<Object, Void, Bitmap>{

        private WeakReference<ImageView> imageViewWeakReference;
        private int imageWidth;

        @Override
        protected Bitmap doInBackground(Object... objects) {
            URL url = (URL) objects[0];
            imageViewWeakReference = new WeakReference<ImageView>((ImageView) objects[1]);
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
            ImageView imageView = imageViewWeakReference.get();
            if (imageView != null && bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
        }

        private Bitmap resizeBitmap(Bitmap bitmap){
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int ratio = width / imageWidth;
            int imageHeight = height / ratio;
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, true);
            return resizedBitmap;
        }
    }
}
