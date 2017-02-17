package com.example.android.musicplay_simple;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 재은 on 2017-02-15.
 */

public class nowplaying extends AppCompatActivity {

    //노래,비디오 재생 가능한 플레이어 변수
    private MediaPlayer mediaPlayer;

    //중지될때 재생위치
    private int playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼가져오기
        Button start = (Button)findViewById(R.id.playBtn);
        Button pause = (Button)findViewById(R.id.pauseBtn);
        Button restart = (Button)findViewById(R.id.replayBtn);

        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                    mediaPlayer=null;
                }
                playAudio();
                Toast.makeText(nowplaying.this,"Music Play",Toast.LENGTH_LONG).show();
            }
        });

        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null){
                    //현재 재생위치 저장
                    playbackPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                    Toast.makeText(nowplaying.this, "Pause", Toast.LENGTH_SHORT).show();
                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null && !mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    mediaPlayer.seekTo(playbackPosition);
                    Toast.makeText(nowplaying.this, "Replay", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //미디어 재생 사용자 정의 메소드
    public void playAudio(){
        mediaPlayer = MediaPlayer.create(this,R.raw.song);
        mediaPlayer.start();
    }

    //액티비티가 화면에서 제거될떄?

    @Override
    //액티비티가 화면에서 제거될 때 호출되는 메서드
    protected void onDestroy(){
        killMediaPlayer();
        super.onDestroy();
    }

    /*@Override
    protected void onResume(){

        super.onResume();

        mediaPlayer = MediaPlayer.create(this,R.raw.song);
        if(mediaPlayer !=null && !mediaPlayer.isPlaying()){
            mediaPlayer.start();
            mediaPlayer.seekTo(playbackPosition);
            Toast.makeText(MainActivity.this,"Restart",Toast.LENGTH_LONG).show();
        }
    }*/

    private void killMediaPlayer(){
        if(mediaPlayer !=null && !mediaPlayer.isPlaying()){
            try{
                mediaPlayer.release();
            }catch(Exception e){
                Log.e("error",e.getMessage());
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.id.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


}
