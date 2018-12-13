package com.szlangpai.myijkplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

//    private JzvdStd mJzvdStd;

    private VideoPlayerIJK mVideoPlayerIJK;

    private String mPreviewURL = "rtsp://192.72.1.1/liveRTSP/av4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mJzvdStd = (JzvdStd)findViewById(R.id.videoplayer);
//
//        Jzvd.setMediaInterface(new JZMediaIjkplayer());
//        Jzvd.SAVE_PROGRESS = false;
//
//        mJzvdStd.setUp(mPreviewURL, "rtsp", Jzvd.SCREEN_WINDOW_NORMAL);
//        mJzvdStd.startButton.performClick();
//        mJzvdStd.startVideo();

        mVideoPlayerIJK = (VideoPlayerIJK) findViewById(R.id.videoPlayerIJK);

        startVideo();
    }

    @Override
    public void onBackPressed() {
//        if (Jzvd.backPress()) {
//            return;
//        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Jzvd.releaseAllVideos();
    }

    @Override
    protected void onStop() {
        super.onStop();
        IjkMediaPlayer.native_profileEnd();
    }

    public void startVideo() {
        //加载native库
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }

        mVideoPlayerIJK.setListener(new VideoPlayerListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                Log.d(TAG, "onBufferingUpdate: " + percent);
            }

            @Override
            public void onCompletion(IMediaPlayer mp) {
                Log.d(TAG, "onCompletion: ");
            }

            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                return false;
            }

            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer mp) {
                Log.d(TAG, "onPrepared: ");
                mVideoPlayerIJK.setProgressBarVisible(false);
                mp.start();
            }

            @Override
            public void onSeekComplete(IMediaPlayer mp) {
                Log.d(TAG, "onSeekComplete: ");
            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
                Log.d(TAG, "onVideoSizeChanged: ");
                Log.i(TAG, "width = " + width);
                Log.i(TAG, "height = " + height);
                Log.i(TAG, "sar_num = " + sar_num);
                Log.i(TAG, "sar_den = " + sar_den);
            }
        });

        mVideoPlayerIJK.setVideoPath(mPreviewURL);
    }
}
