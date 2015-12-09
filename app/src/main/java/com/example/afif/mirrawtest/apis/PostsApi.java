package com.example.afif.mirrawtest.apis;

import android.nfc.Tag;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.afif.mirrawtest.dataloader.DataLoaders;
import com.example.afif.mirrawtest.volley.VolleyController;

/**
 * Created by afif on 7/12/15.
 */
public class PostsApi {

    public static final String TAG = PostsApi.class.getSimpleName();
    public DataLoaders mDataLoaders;


    public PostsApi(DataLoaders dataLoaders) {
        this.mDataLoaders = dataLoaders;
    }

    public void fetchPosts(String url){

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
