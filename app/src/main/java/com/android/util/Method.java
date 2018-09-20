package com.android.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by liurenyi on 2018/5/23.
 */

public class Method {

    private Intent intent;

    public static ApplicationInfo info;

    public static Drawable drawable;

    // 开启activity
    public void startOtherUI(Context mContext, int type) {
        intent = new Intent();
        if (type == 1) {

        } else if (type == 2) {

        } else if (type == 3) {

        } else if (type == 4) {

        }

    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getDate() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);           // 获取年份
        int month = ca.get(Calendar.MONTH);         // 获取月份
        int day = ca.get(Calendar.DATE);            // 获取日
        int minute = ca.get(Calendar.MINUTE);       // 分
        int hour = ca.get(Calendar.HOUR);           // 小时
        int second = ca.get(Calendar.SECOND);       // 秒
        String months = month >= 9 ? (month + 1) + "" : "0" + (month + 1);
        String days = day >= 10 ? day + "" : "0" + day;
        String hours = hour >= 10 ? hour + "" : "0" + hour;
        String minutes = minute >= 10 ? minute + "" : "0" + minute;
        String seconds = second >= 10 ? second + "" : "0" + second;
        String date = "" + year + months + days + hours + minutes + seconds;
        return date;
    }

    /**
     * 获取SD path
     *
     * @return
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
            return sdDir.toString();
        }
        return null;
    }

    // List 集合去除 null 元素
    public static <T> List<T> removeNull(List<? extends T> oldList) {
        // 临时集合
        List<T> listTemp = new ArrayList();
        for (int i = 0; i < oldList.size(); i++) {
            // 保存不为空的元素
            Log.e("liu", "0.0.0" + oldList.get(i));
            if (oldList.get(i).toString().length() > 0) {
                listTemp.add(oldList.get(i));
            }
        }
        return listTemp;
    }

    /**
     * 根据包名，获取对应的小图标
     *
     * @param packageName
     * @return
     */
    public static Drawable getIcon(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            info = manager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
            drawable = manager.getApplicationIcon(info);
            return drawable;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 格式化录音时长的时间格式
     *
     * @param timer
     */
    public static String formatTimer(int timer) {
        if (timer > 0 && timer < 10) {
            return "00:0" + timer;
        } else if (timer >= 10 && timer < 60) {
            return "00:" + timer;
        } else if (timer == 60) {
            return "01:00";
        } else if (timer > 60 && timer < 599) {
            if (timer % 60 > 0 && timer % 60 < 10) {
                return "0" + timer / 60 + ":" + "0" + timer % 60;
            } else {
                return "0" + timer / 60 + ":" + (timer % 60);
            }

        } else if (timer >= 600) {
            if (timer % 60 > 0 && timer % 60 < 10) {
                return timer / 60 + ":" + "0" + timer % 60;
            } else {
                return timer / 60 + ":" + timer % 60;
            }
        } else {
            return "00:00";
        }
    }
}
