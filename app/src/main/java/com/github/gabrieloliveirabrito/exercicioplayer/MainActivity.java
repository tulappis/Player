package com.github.gabrieloliveirabrito.exercicioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button playBtn, pauseBtn, stopBtn;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.playBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        stopBtn = findViewById(R.id.stopBtn);
    }

    private void createPlayer () {
        player = MediaPlayer.create(this, R.raw.far_horizons);

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                updateButtons(false);
                Toast.makeText(MainActivity.this, R.string.mediaplayer_end, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateButtons(boolean playing) {
        playBtn.setEnabled(!playing);
        pauseBtn.setEnabled(playing);
        stopBtn.setEnabled(playing);
    }

    public void play(View v) {
        if(player == null || player.getDuration() == 0) //Caso o player tenha sido parado ou terminado
            createPlayer();
        updateButtons(true);
        player.start();
    }

    public void pause(View v) {
        updateButtons(false);
        stopBtn.setEnabled(true);
        player.pause();
    }

    public void stop(View v) {
        player.release();
        player = null;

        updateButtons(false);
    }
}