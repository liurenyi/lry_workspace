package com.android.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.R;
import com.android.ui.activity.apkname.PackageManagerActivity;
import com.android.ui.activity.bluetooth.BluetoothActivity;
import com.android.ui.activity.lightsensoe.LightSensorActivity;
import com.android.ui.activity.soundrecorder.SoundRecorderActivity;
import com.android.util.ActivityCodeUtil;
import com.android.util.DeviceInfo;

public class FirstFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RadioButton bluetoothButton;
    private RadioButton lightSensorButton;
    private RadioButton packageInfoButton;
    private RadioButton soundRecordButton;
    private Intent intent;

    private TextView textMainTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        bluetoothButton = view.findViewById(R.id.radio_bluetooth);
        bluetoothButton.setOnClickListener(this);

        lightSensorButton = view.findViewById(R.id.radio_light_sensor);
        lightSensorButton.setOnClickListener(this);

        packageInfoButton = view.findViewById(R.id.radio_package_info);
        packageInfoButton.setOnClickListener(this);

        soundRecordButton = view.findViewById(R.id.radio_sound_record);
        soundRecordButton.setOnClickListener(this);

        textMainTitle = view.findViewById(R.id.text_main_title);
        textMainTitle.setText("设备名称：" + DeviceInfo.getSystemModel() + "   " + "分辨率：" + DeviceInfo.getDisplay(getContext()));


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.radio_bluetooth:
                startActivitys(ActivityCodeUtil.BLUETOOTH_ACTIVITY_CODE);
                break;
            case R.id.radio_light_sensor:
                startActivitys(ActivityCodeUtil.SENSOR_ACTIVITY_CODE);
                break;
            case R.id.radio_package_info:
                startActivitys(ActivityCodeUtil.PACKAGE_ACTIVITY_CODE);
                break;
            case R.id.radio_sound_record:
                startActivitys(ActivityCodeUtil.SOUND_RECORD_ACTIVITY_CODE);
                break;
            default:
                break;
        }
    }

    private void startActivitys(int type) {
        switch (type) {
            case 1:
                intent = new Intent(getContext(), BluetoothActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getContext(), LightSensorActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getContext(), PackageManagerActivity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(getContext(), SoundRecorderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
