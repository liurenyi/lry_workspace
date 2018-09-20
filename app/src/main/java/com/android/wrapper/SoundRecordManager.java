package com.android.wrapper;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.SystemClock;
import android.util.Log;

import com.android.util.Method;
import com.android.util.SRParamet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoundRecordManager {

    public Context mContext;

    public String TAG = "SoundRecordManager";

    public MediaRecorder mediaRecorder;

    private File mAudioFile;

    private long startTime;

    public SoundRecordManager(Context mContext, MediaRecorder mediaRecorder) {
        this.mContext = mContext;
        this.mediaRecorder = mediaRecorder;
    }

    /**
     * 开始录制音频文件
     */
    public void startRecording() {
        if (mediaRecorder == null) {
            Log.e(TAG, "mediaRecorder is null ! ");
            return;
        }

        String fileName = getFileName();

        mAudioFile = new File(fileName);

        if (!mAudioFile.getParentFile().exists()) {
            mAudioFile.getParentFile().mkdirs();
        }

        try {
            mAudioFile.createNewFile();
            //从麦克风采集声音数据
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            //设置保存文件格式为MP4
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            //设置采样频率,44100是所有安卓设备都支持的频率,频率越高，音质越好，当然文件越大
            mediaRecorder.setAudioSamplingRate(44100);
            //设置声音数据编码格式,音频通用格式是AAC
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            //设置编码频率
            mediaRecorder.setAudioEncodingBitRate(96000);
            //设置录音保存的文件
            mediaRecorder.setOutputFile(mAudioFile.getAbsolutePath());
            //开始录音
            mediaRecorder.prepare();
            mediaRecorder.start();
            //记录开始录音时间
            startTime = System.currentTimeMillis();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 停止录音
     */
    public void stopRecord() {

        if (mediaRecorder == null) {
            Log.e(TAG, "mediaRecorder is null ! ");
            return;
        }

        mediaRecorder.stop();

    }

    /**
     * 获取文件路径
     *
     * @return
     */
    private String getFileName() {
        return SRParamet.filePath + Method.getDate() + SRParamet.fileFormat;
    }


}
