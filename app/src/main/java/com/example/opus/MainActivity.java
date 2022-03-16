package com.example.opus;

// Media =  audio  + video + imagees
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.opus.R;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button play;
    private Button pause;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // use media player class
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        seekBar = findViewById(R.id.seekBar);
        /* Media player using local source */
//        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.blankspace);

        /* Media player using remote source like internet or web */

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://ashleecadell.com/xyzstorelibrary/Linkin%20Park/Meteora/13%20-%20Numb.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Toast.makeText(MainActivity.this, "Play", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        if (b) {
                            mediaPlayer.seekTo(i);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });

        mediaPlayer.prepareAsync();
        //      mediaPlayer.start();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Playing the song", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Song paused", Toast.LENGTH_SHORT).show();
                mediaPlayer.pause();
            }
        });
    }
}