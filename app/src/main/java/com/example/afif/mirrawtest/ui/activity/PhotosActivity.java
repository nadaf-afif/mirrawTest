package com.example.afif.mirrawtest.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.afif.mirrawtest.Constants;
import com.example.afif.mirrawtest.R;
import com.example.afif.mirrawtest.volley.VolleyController;

/**
 * Created by afif on 8/12/15.
 */
public class PhotosActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private NetworkImageView mPhotoImageView;
    private ImageLoader mImageLoader;
    private TextView mPhotoTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        initViews();
    }

    private void initViews() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle(getString(R.string.photos));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPhotoImageView = (NetworkImageView) findViewById(R.id.photoImageView);
        mPhotoTitleTextView = (TextView) findViewById(R.id.photoTitleTextView);
        mImageLoader = VolleyController.getInstance().getImageLoader();
        Bundle bundle = getIntent().getExtras();

        if (bundle!=null && bundle.containsKey(Constants.BundleKeys.IMAGE_URL)){
            String imageUrl = bundle.getString(Constants.BundleKeys.IMAGE_URL);
            String photoTitle = bundle.getString(Constants.BundleKeys.PHOTO_TITLE);
            mPhotoImageView.setImageUrl(imageUrl,mImageLoader);
            mPhotoTitleTextView.setText(photoTitle);
        }

    }

}
