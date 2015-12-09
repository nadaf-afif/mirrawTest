package com.example.afif.mirrawtest;

/**
 * Created by afif on 7/12/15.
 */
public class Constants {

    public interface URLS{
        String BASE_URL = "http://jsonplaceholder.typicode.com/";
        String POSTS_API_PATH = BASE_URL + "posts";
        String POSTS_COMMENTS_API_PATH = BASE_URL + "posts/";
        String COMMENTS_PATH = "/comments";
        String PHOTOS_API_PATH = BASE_URL + "photos";
    }

    public interface URLS_PARAMS{
        String ID = "id";
        String NAME = "name";
        String TITLE = "title";
        String BODY = "body";
        String USER_ID = "userId";
        String POSTS_ID = "postId";
        String EMAIL = "email";
        String ALBUM_ID = "albumId";
        String URL = "url";
        String THUMB_URL = "thumbnailUrl";
    }


    public interface BundleKeys{
        String POST_ID = "posts_id";
        String POST_TITLE = "posts_title";
        String POST_DESCRIPTION = "posts_description";
        String IMAGE_URL = "image_url";
        String PHOTO_TITLE = "title";
    }
}
