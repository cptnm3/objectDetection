package org.tensorflow.lite.examples.detection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class introductoryActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    ImageView img;
    //TextView textView;
    Thread timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_introductory);

        lottieAnimationView = findViewById(R.id.lottie);
        img = findViewById(R.id.imageView);
        //textView = findViewById(R.id.textView);
        lottieAnimationView.animate().translationY(-2500).setDuration(1250).setStartDelay(1500);
        //img.animate().translationY(2500).setDuration(1200).setStartDelay(1500);
        //textView.animate().translationY(2500).setDuration(1250).setStartDelay(1500);

        timer = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this){
                        wait(4050);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(introductoryActivity.this,DetectorActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();

    }
}