package com.example.afif.mirrawtest.dataloader;

/**
 * Created by afif on 7/12/15.
 */
public interface DataLoaders {

    public void getDataFromApi();
    public void setDataInAdapter(String response);
    public void onNoInternet();
}
