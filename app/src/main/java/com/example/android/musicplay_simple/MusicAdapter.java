package com.example.android.musicplay_simple;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import android.support.v4.content.ContextCompat;

import java.util.ArrayList;


/**
 * Created by 재은 on 2017-02-15.
 */

public class MusicAdapter extends ArrayAdapter<Music> {

    private int mColorResourceId;

    public MusicAdapter(Context context, ArrayList<Music> musics, int colorResourceId) {
        super(context, 0, musics);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.music_row, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Music currentMusic = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        titleTextView.setText(currentMusic.getmtitle());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView artistTextView = (TextView) listItemView.findViewById(R.id.artist);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        artistTextView.setText(currentMusic.getmartist());
        TextView genreTextView = (TextView) listItemView.findViewById(R.id.genre);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        genreTextView.setText(currentMusic.getMgenre());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        // Check if an image is provided for this word or not
        if (currentMusic.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentMusic.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }
        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.playlists);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

}