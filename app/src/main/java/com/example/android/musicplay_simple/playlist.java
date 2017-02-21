package com.example.android.musicplay_simple;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by 재은 on 2017-02-15.
 */

public class playlist extends AppCompatActivity{

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    // Now that the sound file has finished playing, release the media player resources.
                    releaseMediaPlayer();
                }
            };

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                        // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                        // our app is allowed to continue playing sound but at a lower volume. We'll treat
                        // both cases the same way because our app is playing short sound files.

                        // Pause playback and reset player to the start of the file. That way, we can
                        // play the word from the beginning when we resume playback.
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                        // Stop playback and clean up resources
                        releaseMediaPlayer();
                    }
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_list);

        // Create and setup the {@link AudioManager} to request audio focus
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<Music> musics = new ArrayList<Music>();
        musics.add(new Music("Make It Work", "Blended Babies","HipHop", R.drawable.albumcover, R.raw.song));
        musics.add(new Music("My Fucking Valentine", "Gerry Mulligan","Jazz", R.drawable.albumcover, R.raw.song));
        musics.add(new Music("Lil Bit", "Dej Loaf","HipHop", R.drawable.albumcover, R.raw.song));
        musics.add(new Music("No Diggity", "Black Street","R&B", R.drawable.albumcover, R.raw.song));
        musics.add(new Music("Young Wild & Free(feat.Bruno Mars)", "Snoop Dogg","HipHop", R.drawable.albumcover, R.raw.song));
        musics.add(new Music("Let me Love you", "Mario","R&B", R.drawable.albumcover, R.raw.song));
        musics.add(new Music("All day", "Kanye West","HipHop", R.drawable.albumcover, R.raw.song));
        musics.add(new Music("L-O-V-E", "Nat King Cole","Jazz", R.drawable.albumcover, R.raw.song));
        musics.add(new Music("I Will Rise", "Chris Tomlin","Christian", R.drawable.albumcover, R.raw.song));
        musics.add(new Music("Beachin'", "Jake Owen","Country", R.drawable.albumcover, R.raw.song));
        musics.add(new Music("Fat Cat", "Nick Vila","Jazz", R.drawable.albumcover, R.raw.song));

        MusicAdapter adapter = new MusicAdapter(this,musics,R.color.playlist);

        ListView listView = (ListView) findViewById(R.id.list);

         listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();

                Music music = musics.get(position);

                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(playlist.this,music.getAudioResourceId());


                    mediaPlayer.start();

                      mediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

}
