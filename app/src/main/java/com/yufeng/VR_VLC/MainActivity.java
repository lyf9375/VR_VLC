package com.yufeng.VR_VLC;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity{

    private static final String URL_RTSP = "rtsp://192.168.2.1/live1.sdp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRTSPVRLibClicked(View view) {
        VRPlayerActivity.start(MainActivity.this, Uri.parse(URL_RTSP));
    }

}
