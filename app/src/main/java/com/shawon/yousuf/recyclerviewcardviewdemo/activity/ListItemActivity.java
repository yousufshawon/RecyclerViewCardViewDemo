package com.shawon.yousuf.recyclerviewcardviewdemo.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shawon.yousuf.recyclerviewcardviewdemo.R;
import com.shawon.yousuf.recyclerviewcardviewdemo.adapter.PlaylistRecyclerAdapter;
import com.shawon.yousuf.recyclerviewcardviewdemo.model.Song;
import com.shawon.yousuf.recyclerviewcardviewdemo.util.Constants;
import com.shawon.yousuf.recyclerviewcardviewdemo.util.VerticalSpaceItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListItemActivity extends AppCompatActivity {

    @Bind(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Song> mDataList;

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        ini();

        getDataFromInternet();
    }





    private void ini(){

        mDataList = new ArrayList<>();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(dpToPx(5)));
      //  mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
      //  mRecyclerView.addItemDecoration(new DividerItemDecoration(this, R.drawable.divider));
      //  mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        // specify an adapter (see also next example)
        mAdapter = new PlaylistRecyclerAdapter(mDataList);
        mRecyclerView.setAdapter(mAdapter);

    }


    private void getDataFromInternet(){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.urlPlayList, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "Response: " + response.toString());

                        try {

                            if (response.has(Constants.TAG_PLAYLIST)) {
                                JSONObject jsonObjectPlaylist = response.getJSONObject(Constants.TAG_PLAYLIST);
                                // adding playlist a
                                if (jsonObjectPlaylist.has(Constants.TAG_PLAYLIST_A)) {
                                    JSONArray jsonArrayPlayListA = jsonObjectPlaylist.getJSONArray(Constants.TAG_PLAYLIST_A);

                                    for (int i =0; i<jsonArrayPlayListA.length(); i++){
                                        JSONObject jsonObject = jsonArrayPlayListA.getJSONObject(i);

                                        Song song = new Song(jsonObject);

                                        mDataList.add(song);

                                    }
                                }

                                // adding playlist b
                                if (jsonObjectPlaylist.has(Constants.TAG_PLAYLIST_B)) {
                                    JSONArray jsonArrayPlayListA = jsonObjectPlaylist.getJSONArray(Constants.TAG_PLAYLIST_B);

                                    for (int i =0; i<jsonArrayPlayListA.length(); i++){
                                        JSONObject jsonObject = jsonArrayPlayListA.getJSONObject(i);

                                        Song song = new Song(jsonObject);

                                        mDataList.add(song);

                                    }
                                }


                                mAdapter.notifyDataSetChanged();
                            }

                        }catch (JSONException e){
                            Log.e(TAG, e.toString());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });


        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }



}
