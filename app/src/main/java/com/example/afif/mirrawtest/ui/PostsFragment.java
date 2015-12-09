package com.example.afif.mirrawtest.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.afif.mirrawtest.Constants;
import com.example.afif.mirrawtest.R;
import com.example.afif.mirrawtest.adapters.PostsAdapter;
import com.example.afif.mirrawtest.apis.PostsApi;
import com.example.afif.mirrawtest.bean.PostsBean;
import com.example.afif.mirrawtest.dataloader.DataLoaders;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by afif on 5/12/15.
 */
public class PostsFragment extends Fragment implements DataLoaders{

    public static final String TAG = PostsFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private ArrayList<PostsBean> mDataList = new ArrayList<>();
    private PostsAdapter mAdapter;
    private PostsApi mPostsApi;
    private Context mContext;
    private ProgressBar mProgressBar;
    private TextView mEmptyTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mEmptyTextView = (TextView) view.findViewById(R.id.emptyTextView);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mContext = getContext();
        getDataFromApi();

    }


    @Override
    public void getDataFromApi() {
        if (mPostsApi == null)
            mPostsApi = new PostsApi(this);

        mPostsApi.fetchPosts(Constants.URLS.POSTS_API_PATH);
    }

    @Override
    public void setDataInAdapter(String response) {
        if (isAdded()) {
            parseData(response);
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mAdapter = new PostsAdapter(mDataList, mContext);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private void parseData(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                PostsBean postsBean = new PostsBean();
                postsBean.setPostsId(jsonObject.getString(Constants.URLS_PARAMS.ID));
                postsBean.setPostBody(jsonObject.getString(Constants.URLS_PARAMS.BODY));
                postsBean.setPostTitle(jsonObject.getString(Constants.URLS_PARAMS.TITLE));
                postsBean.setUserId(jsonObject.getString(Constants.URLS_PARAMS.USER_ID));
                mDataList.add(postsBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNoInternet() {
        if (isAdded()) {
            mProgressBar.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            mEmptyTextView.setVisibility(View.VISIBLE);
        }
    }
}
