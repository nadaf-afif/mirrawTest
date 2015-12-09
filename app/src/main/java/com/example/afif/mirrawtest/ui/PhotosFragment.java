package com.example.afif.mirrawtest.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.afif.mirrawtest.Constants;
import com.example.afif.mirrawtest.R;
import com.example.afif.mirrawtest.adapters.PhotosAdapter;
import com.example.afif.mirrawtest.apis.PhotosApi;
import com.example.afif.mirrawtest.bean.PhotoBean;
import com.example.afif.mirrawtest.dataloader.DataLoaders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by afif on 8/12/15.
 */
public class PhotosFragment extends Fragment implements DataLoaders {

    public static final String TAG = PhotosFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ArrayList<PhotoBean> mDataList = new ArrayList<>();
    private Context mContext;
    private ProgressBar mProgressBar;
    private TextView mEmptyTextView;
    private PhotosApi mPhotosApi;
    private PhotosAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_products,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mEmptyTextView = (TextView) view.findViewById(R.id.emptyTextView);
        mContext = getContext();
        mPhotosApi = new PhotosApi(this);
        getDataFromApi();

    }

    @Override
    public void getDataFromApi() {
        mPhotosApi.fetchPhotos(Constants.URLS.PHOTOS_API_PATH);
    }

    @Override
    public void setDataInAdapter(String response) {
        parseData(response);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new PhotosAdapter(mContext,this,mDataList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void parseData(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i= 0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PhotoBean photoBean = new PhotoBean();
                photoBean.setAlbumId(jsonObject.getString(Constants.URLS_PARAMS.ALBUM_ID));
                photoBean.setPhotoId(jsonObject.getString(Constants.URLS_PARAMS.ID));
                photoBean.setThumbUrl(jsonObject.getString(Constants.URLS_PARAMS.THUMB_URL));
                photoBean.setTitle(jsonObject.getString(Constants.URLS_PARAMS.TITLE));
                photoBean.setUrl(jsonObject.getString(Constants.URLS_PARAMS.URL));
                mDataList.add(photoBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNoInternet() {
        mRecyclerView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mEmptyTextView.setVisibility(View.VISIBLE);

    }
}
