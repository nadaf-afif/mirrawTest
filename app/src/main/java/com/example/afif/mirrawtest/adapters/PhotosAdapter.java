package com.example.afif.mirrawtest.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.afif.mirrawtest.Constants;
import com.example.afif.mirrawtest.R;
import com.example.afif.mirrawtest.bean.PhotoBean;
import com.example.afif.mirrawtest.dataloader.DataLoaders;
import com.example.afif.mirrawtest.ui.activity.PhotosActivity;
import com.example.afif.mirrawtest.volley.VolleyController;

import java.util.ArrayList;

/**
 * Created by afif on 8/12/15.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private DataLoaders mDataLoader;
    private ArrayList<PhotoBean> mDataList;
    private ImageLoader mImageLoader;

    public PhotosAdapter(Context context, DataLoaders dataLoader, ArrayList<PhotoBean> data) {
        this.mContext = context;
        this.mDataLoader = dataLoader;
        mDataList = data;
        mInflater = LayoutInflater.from(context);
        mImageLoader = VolleyController.getInstance().getImageLoader();
    }

    @Override
    public PhotosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_grid_layout,parent,false);
        PhotosHolder holder = new PhotosHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PhotosHolder holder, int position) {
        PhotoBean photoBean = mDataList.get(position);
        holder.networkImageView.setImageUrl(photoBean.getThumbUrl(),mImageLoader);
        holder.photoTitle.setText(photoBean.getTitle());

        final String imageUrl = photoBean.getUrl();
        final String photoTitle = photoBean.getTitle();
        holder.networkImageView.setDefaultImageResId(R.drawable.loading_default);
        holder.networkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotosActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.BundleKeys.IMAGE_URL,imageUrl);
                bundle.putString(Constants.BundleKeys.PHOTO_TITLE,photoTitle);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    class PhotosHolder extends RecyclerView.ViewHolder{

        private NetworkImageView networkImageView;
        private TextView photoTitle;
        public PhotosHolder(View itemView) {
            super(itemView);
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.photoImageView);
            photoTitle = (TextView) itemView.findViewById(R.id.photoTitleTextView);
        }
    }
}
