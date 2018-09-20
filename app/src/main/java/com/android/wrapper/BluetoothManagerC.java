package com.android.wrapper;

import android.bluetooth.BluetoothAdapter;

public class BluetoothManagerC {

    /**
     * 判断Android设备是否支持蓝牙
     *
     * @return
     */
    public static boolean isBluetoothSupported() {
        return BluetoothAdapter.getDefaultAdapter() != null ? true : false;
    }

    /**
     * 判断蓝牙是否已经打开
     *
     * @return
     */
    public static boolean isBluetoothEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            return defaultAdapter.isEnabled();
        } else {
            return false;
        }
    }

    /**
     * 强制开启当前 Android 设备的 Bluetooth
     *
     * @return true：强制打开 Bluetooth　成功　false：强制打开 Bluetooth 失败
     */
    public static boolean turnOnBluetooth() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            return defaultAdapter.enable();
        } else {
            return false;
        }
    }

    /**
     * 强制关闭当前 Android 设备的 Bluetooth
     *
     * @return true：强制关闭 Bluetooth　成功　false：强制关闭 Bluetooth 失败
     */
    public static boolean turnOffBluetooth() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            return defaultAdapter.disable();
        } else {
            return false;
        }
    }
}
