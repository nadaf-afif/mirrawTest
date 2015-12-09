package com.example.afif.mirrawtest.ui.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.afif.mirrawtest.Constants;
import com.example.afif.mirrawtest.R;
import com.example.afif.mirrawtest.adapters.PostCommentsAdapter;
import com.example.afif.mirrawtest.apis.PostsApi;
import com.example.afif.mirrawtest.bean.PostsCommentsBean;
import com.example.afif.mirrawtest.dataloader.DataLoaders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by afif on 8/12/15.
 */
public class PostCommentsActivity extends AppCompatActivity implements DataLoaders{

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mEmptyTextView;
    private TextView mPostTitleTextView;
    private TextView mPostDescriptionTextView;
    private Toolbar mToolBar;
    private String mPostTitle;
    private String mPostDescription;
    private int mPostId;
    private PostsApi mPostsApi;
    private ArrayList<PostsCommentsBean> mDataList = new ArrayList<>();
    private PostCommentsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comments);
        initViews();
        getDataFromApi();
    }

    private void initViews() {

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            mPostTitle = bundle.getString(Constants.BundleKeys.POST_TITLE);
            mPostId = bundle.getInt(Constants.BundleKeys.POST_ID);
            mPostDescription = bundle.getString(Constants.BundleKeys.POST_DESCRIPTION);
        }
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle(getString(R.string.post_comments));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mEmptyTextView = (TextView) findViewById(R.id.emptyTextView);
        mPostDescriptionTextView = (TextView) findViewById(R.id.posts_description);
        mPostTitleTextView = (TextView) findViewById(R.id.posts_name);

        mPostDescriptionTextView.setText(mPostDescription);
        mPostTitleTextView.setText(mPostTitle);
        mPostsApi = new PostsApi(this);
    }

    @Override
    public void getDataFromApi() {

        if (mPostsApi == null)
            mPostsApi = new PostsApi(this);

        mPostsApi.fetchPosts(Constants.URLS.POSTS_COMMENTS_API_PATH+mPostId+Constants.URLS.COMMENTS_PATH);
    }

    @Override
    public void setDataInAdapter(String response) {
        parseData(response);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new PostCommentsAdapter(mDataList,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void parseData(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PostsCommentsBean postsCommentsBean = new PostsCommentsBean();
                postsCommentsBean.setPostsId(jsonObject.getString(Constants.URLS_PARAMS.POSTS_ID));
                postsCommentsBean.setCommentBody(jsonObject.getString(Constants.URLS_PARAMS.BODY));
                postsCommentsBean.setCommentsId(jsonObject.getString(Constants.URLS_PARAMS.ID));
                postsCommentsBean.setCommentTitle(jsonObject.getString(Constants.URLS_PARAMS.NAME));
                postsCommentsBean.setEmail(jsonObject.getString(Constants.URLS_PARAMS.EMAIL));
                mDataList.add(postsCommentsBean);
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
