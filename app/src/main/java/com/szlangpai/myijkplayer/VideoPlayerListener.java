package com.szlangpai.myijkplayer;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/******************************************************************************
 * Copyright (C), 2006-2020, SZLangpai Co., Ltd.
 * ****************************************************************************
 * Version       : Initial Draft
 * Author        : FengQ
 * Created       : 2018/12/13
 * Description   :
 *****************************************************************************/

public abstract class VideoPlayerListener implements IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnPreparedListener,
        IMediaPlayer.OnInfoListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnSeekCompleteListener {

}
