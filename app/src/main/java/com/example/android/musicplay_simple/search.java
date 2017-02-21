package com.example.android.musicplay_simple;

import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.start;
import static com.example.android.musicplay_simple.R.id.searchbtn;

/**
 * Created by 재은 on 2017-02-15.
 */

public class search extends AppCompatActivity {

    EditText editText;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);

        editText = (EditText) findViewById(R.id.searching);
        imageButton = (ImageButton) findViewById(searchbtn);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inPutText=editText.getText().toString();
                Toast.makeText(search.this, inPutText + " Searching...", Toast.LENGTH_LONG).show();
            }
        });
    }


}
