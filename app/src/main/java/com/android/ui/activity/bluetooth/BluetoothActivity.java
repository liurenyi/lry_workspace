package com.android.ui.activity.bluetooth;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.R;
import com.android.wrapper.BluetoothManagerC;

public class BluetoothActivity extends AppCompatActivity {

    private Context mContext = BluetoothActivity.this;

    private Button testBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        initView();
    }

    private void initView() {
        testBluetooth = this.findViewById(R.id.test_bluetooth);
        boolean bluetoothEnabled = BluetoothManagerC.isBluetoothEnabled();
        boolean bluetoothSupported = BluetoothManagerC.isBluetoothSupported();
        if (bluetoothSupported && bluetoothEnabled) {
            testBluetooth.setText(getString(R.string.close_bluetooth));
        } else if (!bluetoothSupported) {
            testBluetooth.setText(getString(R.string.no_support_bluetooth));
            testBluetooth.setEnabled(false);
        } else {
            testBluetooth.setText(getString(R.string.open_bluetooth));
        }
        testBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBluetooth();
            }
        });
    }

    /**
     * 打开或者关闭蓝牙
     */
    private void openBluetooth() {
        boolean supported = BluetoothManagerC.isBluetoothSupported();
        if (supported) {
            boolean bluetoothEnabled = BluetoothManagerC.isBluetoothEnabled();
            if (bluetoothEnabled) {
                boolean turnOffBluetooth = BluetoothManagerC.turnOffBluetooth();
                if (turnOffBluetooth) {
                    testBluetooth.setText(getString(R.string.open_bluetooth));
                }
            } else {
                boolean turnOnBluetooth = BluetoothManagerC.turnOnBluetooth();
                if (turnOnBluetooth) {
                    testBluetooth.setText(getString(R.string.close_bluetooth));
                }
            }
        } else {
            Toast.makeText(mContext, getString(R.string.no_support_bluetooth), Toast.LENGTH_LONG).show();
        }
    }
}
