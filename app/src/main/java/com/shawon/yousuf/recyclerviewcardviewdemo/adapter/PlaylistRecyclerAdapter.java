package com.shawon.yousuf.recyclerviewcardviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shawon.yousuf.recyclerviewcardviewdemo.R;
import com.shawon.yousuf.recyclerviewcardviewdemo.model.Song;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by user on 8/16/2016.
 */
public class PlaylistRecyclerAdapter extends RecyclerView.Adapter<PlaylistRecyclerAdapter.ViewHolder> {

    private List<Song> mItemList;

    private String TAG = getClass().getSimpleName();

    public PlaylistRecyclerAdapter(List<Song> mItemList) {
        this.mItemList = mItemList;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_card, parent, false);

        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

          // - get element from your dataset at this position
          // - replace the contents of the view with that element

        Log.d(TAG, "" + mItemList.get(position).getImageUrl() );
        Picasso.with(holder.mImageView.getContext()).load(mItemList.get(position).getImageUrl()).into(holder.mImageView);
        holder.mTextViewTitle.setText(mItemList.get(position).getTitle());
        holder.mTextViewArtist.setText(mItemList.get(position).getArtist());

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Views that display information
        @Bind(R.id.imageViewIcon) ImageView mImageView;
        @Bind(R.id.textViewTitle)TextView mTextViewTitle;
        @Bind(R.id.textViewArtist) TextView mTextViewArtist;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
