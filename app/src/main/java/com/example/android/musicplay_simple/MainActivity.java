package com.example.android.musicplay_simple;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_screen);

        TextView now=(TextView)findViewById(R.id.now);

        now.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent nowIntent = new Intent(MainActivity.this,nowplaying.class);
                startActivity(nowIntent);
            }
        });
        TextView playlist=(TextView)findViewById(R.id.list);

        playlist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent playlistIntent = new Intent(MainActivity.this,playlist.class);
                startActivity(playlistIntent);
            }
        });
        TextView search=(TextView)findViewById(R.id.search);

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent searchIntent = new Intent(MainActivity.this,search.class);
                startActivity(searchIntent);
            }
        });
        TextView today=(TextView)findViewById(R.id.today);

        today.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent todayIntent = new Intent(MainActivity.this,todays.class);
                startActivity(todayIntent);
            }
        });
    }

}
