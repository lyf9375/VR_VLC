package com.yufeng.VR_VLC;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.opengl.GLSurfaceView;
import android.view.SurfaceView;
import android.view.View;

import com.asha.vrlib.MD360Director;
import com.asha.vrlib.MD360DirectorFactory;
import com.asha.vrlib.MDVRLibrary;
import com.asha.vrlib.model.MDPinchConfig;

public class VRPlayerActivity extends VLCVideoActivity{

    private MDVRLibrary mVRLibrary;
    private View glView;

    public static void start(Context context, Uri uri){
        Intent intent = new Intent(context, VRPlayerActivity.class);
        intent.setData(uri);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vrdemo);
        glView = new View(this);
        glView = findViewById(R.id.gl_view);
        glView.setScaleX(-1);
        init();
    }


    protected void init() {
        mVRLibrary = MDVRLibrary.with(this)
                .displayMode(MDVRLibrary.DISPLAY_MODE_GLASS)
                .interactiveMode(MDVRLibrary.INTERACTIVE_MODE_CARDBORAD_MOTION)
                .projectionMode(MDVRLibrary.PROJECTION_MODE_DOME230_UPPER)
                .pinchConfig(new MDPinchConfig().setDefaultValue(0.7f).setMin(0.5f))
                .pinchEnabled(false)
                .directorFactory(new MD360DirectorFactory() {
                    @Override
                    public MD360Director createDirector(int index) {
                        return MD360Director.builder().setPitch(0).build();
                    }
                })
                .asVideo(new MDVRLibrary.IOnSurfaceReadyCallback() {
                    @Override
                    public void onSurfaceReady(Surface surface) {
                        vlcSetSurface(surface);
                    }
                })
                .build(glView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVRLibrary.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVRLibrary.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVRLibrary.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mVRLibrary.onOrientationChanged(this);
    }
}
