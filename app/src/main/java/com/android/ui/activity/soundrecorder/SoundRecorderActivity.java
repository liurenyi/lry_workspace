package com.android.ui.activity.soundrecorder;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.R;
import com.android.util.Method;
import com.android.wrapper.SoundRecordManager;

import java.util.ArrayList;
import java.util.List;

public class SoundRecorderActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext = SoundRecorderActivity.this;

    private static final String TAG = "SoundRecorder";

    private TextView textShowTme;

    private Button buttonFileList, buttonRecording, buttonStop;

    private MediaRecorder mediaRecorder;

    private int timer;

    private boolean isRecording = false;

    private boolean isPause = false;

    private List<String> mAudioPath = new ArrayList<>();

    private SoundRecordManager soundRecordManager;

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isRecording) {
                timer = timer + 1;
                handler.postDelayed(runnable, 1000);
                showTimer(timer);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_recorder);

        mediaRecorder = new MediaRecorder();

        initView();
    }

    /**
     * 初始化控件ID
     */
    private void initView() {
        textShowTme = (TextView) this.findViewById(R.id.show_time);
        buttonFileList = (Button) this.findViewById(R.id.recode_file_list);
        buttonRecording = (Button) this.findViewById(R.id.recode_start_and_pause);
        buttonStop = (Button) this.findViewById(R.id.recode_ok);

        buttonFileList.setOnClickListener(this);
        buttonRecording.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recode_start_and_pause:
                if (!isRecording) {
                    soundRecordManager = new SoundRecordManager(mContext, mediaRecorder);
                    soundRecordManager.startRecording();
                    isRecording = true;
                    handler.postDelayed(runnable, 1000);
                }
                break;
            case R.id.recode_file_list:

                break;
            case R.id.recode_ok:
                handler.removeCallbacks(runnable);
                if (isRecording) {
                    soundRecordManager = new SoundRecordManager(mContext, mediaRecorder);
                    soundRecordManager.stopRecord();
                    Toast.makeText(mContext, "文件已保存在mnt/sdcard/Audio/目录", Toast.LENGTH_LONG).show();
                    textShowTme.setText("00:00");
                    isRecording = false;
                } else {
                    Toast.makeText(mContext, "还未开始录制", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 显示录音的时长
     *
     * @param timer
     */
    private void showTimer(int timer) {
        String formatTimer = Method.formatTimer(timer);
        textShowTme.setText(formatTimer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaRecorder != null) {
            mediaRecorder.release();
        }

    }
}
