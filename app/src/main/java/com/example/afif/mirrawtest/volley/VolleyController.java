package com.example.afif.mirrawtest.volley;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.afif.mirrawtest.MainApplication;

/**
 * Created by afif on 5/12/15.
 */
public class VolleyController extends Application {


    public static final String TAG = VolleyController.class.getSimpleName();

    private static VolleyController mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private VolleyController() {
        mRequestQueue = mRequestQueue != null ? mRequestQueue : Volley.newRequestQueue(MainApplication.getmContext());
        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache());
    }

    public static synchronized VolleyController getInstance() {
        mInstance = mInstance != null ? mInstance : new VolleyController();
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue != null ? mRequestQueue :  Volley.newRequestQueue(getApplicationContext());
    }

    public ImageLoader getImageLoader() {
        return mImageLoader != null ? mImageLoader : new ImageLoader(mRequestQueue, new LruBitmapCache());
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(tag);
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}



