package com.example.afif.mirrawtest.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.afif.mirrawtest.Constants;
import com.example.afif.mirrawtest.R;
import com.example.afif.mirrawtest.bean.PostsBean;
import com.example.afif.mirrawtest.ui.activity.PostCommentsActivity;

import java.util.ArrayList;

/**
 * Created by afif on 5/12/15.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ProductHolder> {

    private ArrayList<PostsBean> mDataList;
    private Context mContext;
    private LayoutInflater mInflater;

    public PostsAdapter(ArrayList<PostsBean> dataList, Context context) {
        this.mDataList = dataList;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_list_products,parent, false);
        ProductHolder holder = new ProductHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        PostsBean postsBean = mDataList.get(position);
        final String postId = postsBean.getPostsId();
        final String postTitle = postsBean.getPostTitle();
        final String postDescription = postsBean.getPostBody();
        holder.postsTitle.setText(postTitle);
        holder.postsDescription.setText(postDescription);
        holder.postsMainRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(mContext, PostCommentsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.BundleKeys.POST_DESCRIPTION,postDescription);
                bundle.putString(Constants.BundleKeys.POST_TITLE,postTitle);
                bundle.putInt(Constants.BundleKeys.POST_ID,Integer.parseInt(postId));
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private TextView postsTitle;
        private TextView postsDescription;
        private RelativeLayout postsMainRL;
        public ProductHolder(View itemView) {
            super(itemView);
            postsTitle = (TextView) itemView.findViewById(R.id.posts_name);
            postsDescription = (TextView) itemView.findViewById(R.id.posts_description);
            postsMainRL = (RelativeLayout) itemView.findViewById(R.id.postsRL);
        }
    }

}
