package com.example.afif.mirrawtest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.afif.mirrawtest.R;
import com.example.afif.mirrawtest.bean.PostsCommentsBean;
import com.example.afif.mirrawtest.dataloader.DataLoaders;

import java.util.ArrayList;

/**
 * Created by afif on 8/12/15.
 */
public class PostCommentsAdapter extends RecyclerView.Adapter<PostCommentsAdapter.CommentsHolder> {

    private ArrayList<PostsCommentsBean> mDataList;
    private Context mContext;
    private LayoutInflater mInflater;

    public PostCommentsAdapter(ArrayList<PostsCommentsBean> dataList, Context context) {
        this.mDataList = dataList;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public CommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_list_post_comments,parent,false);
        CommentsHolder commentsHolder = new CommentsHolder(view);
        return commentsHolder;
    }

    @Override
    public void onBindViewHolder(CommentsHolder holder, int position) {
        PostsCommentsBean postsCommentsBean = mDataList.get(position);
        holder.commentsBodyTextView.setText(postsCommentsBean.getCommentBody());
        holder.commentsTitleTextView.setText(postsCommentsBean.getCommentTitle());
        holder.emailTextView.setText(postsCommentsBean.getEmail());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class CommentsHolder extends RecyclerView.ViewHolder{

        private TextView commentsTitleTextView;
        private TextView commentsBodyTextView;
        private TextView emailTextView;

        public CommentsHolder(View itemView) {
            super(itemView);
            commentsBodyTextView = (TextView) itemView.findViewById(R.id.commentsBodyTextView);
            commentsTitleTextView = (TextView) itemView.findViewById(R.id.commentsTitleTextView);
            emailTextView = (TextView) itemView.findViewById(R.id.emailTextView);
        }
    }
}
