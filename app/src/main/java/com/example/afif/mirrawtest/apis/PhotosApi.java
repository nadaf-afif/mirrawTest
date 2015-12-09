package com.example.afif.mirrawtest.apis;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.afif.mirrawtest.dataloader.DataLoaders;
import com.example.afif.mirrawtest.volley.VolleyController;

/**
 * Created by afif on 8/12/15.
 */
public class PhotosApi {

    public static final String TAG = PhotosApi.class.getSimpleName();
    public DataLoaders mDataLoaders;


    public PhotosApi(DataLoaders dataLoaders) {
        this.mDataLoaders = dataLoaders;
    }

    public void fetchPhotos(String url){

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mDataLoaders.setDataInAdapter(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError){
                    mDataLoaders.onNoInternet();
                }else {

                }
            }
        });

        VolleyController.getInstance().addToRequestQueue(request,TAG);
    }
}
