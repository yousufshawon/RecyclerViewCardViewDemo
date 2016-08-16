package com.shawon.yousuf.recyclerviewcardviewdemo.model;

import com.shawon.yousuf.recyclerviewcardviewdemo.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 8/16/2016.
 */
public class Song {

    private String title;
    private String artist;
    private String imageUrl;
    private String artistId;


    public Song(JSONObject jsonObject) {

        try {

            if (jsonObject.has(Constants.TAG_TITLE)) {
                this.title = jsonObject.getString(Constants.TAG_TITLE);
            }
            if (jsonObject.has(Constants.TAG_ARTIST)) {
                this.artist = jsonObject.getString(Constants.TAG_ARTIST);
            }
            if (jsonObject.has(Constants.TAG_IMAGE_URL)) {
                this.imageUrl = jsonObject.getString(Constants.TAG_IMAGE_URL);
            }
            if (jsonObject.has(Constants.TAG_ARTIST_ID)) {
                this.artistId = jsonObject.getString(Constants.TAG_ARTIST_ID);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getArtistId() {
        return artistId;
    }
}
