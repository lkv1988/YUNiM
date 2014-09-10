package com.airk.exercise.im.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.airk.exercise.im.app.util.AccountUtil;
import com.airk.exercise.im.app.util.LogWrapper;
import com.hisun.phone.core.voice.CCPCall;
import com.hisun.phone.core.voice.Device;
import com.hisun.phone.core.voice.DeviceListener;
import com.hisun.phone.core.voice.model.setup.UserAgentConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * YunIM Application
 */
public class YunApplication extends Application {

    public static ArrayList<String> interphoneIds = null;
    public static ArrayList<String> chatRoomIds;
    private Device mDevice;

    public interface DeviceStateListener {
        public void OnDeviceConnected();
        public void OnDeviceDisconnected();
    }
    private DeviceStateListener mListener;

    @Override
    public void onCreate() {
        super.onCreate();
        if (interphoneIds == null) {
            interphoneIds = new ArrayList<String>();
        }
        if (chatRoomIds == null) {
            chatRoomIds = new ArrayList<String>();
        }

    }

    /**
     * User-Agent
     *
     * @return user-agent
     */
    public String getUser_Agent() {
        String ua = "Android;"
                + getOSVersion() + ";"
                + com.hisun.phone.core.voice.Build.SDK_VERSION + ";"
                + com.hisun.phone.core.voice.Build.LIBVERSION.FULL_VERSION + ";"
                + getVendor() + "-" + getDevice() + ";";

        ua = ua + getDeviceNO() + ";" + System.currentTimeMillis() + ";";

        return ua;
    }

    public String getDeviceNO() {
        if (!TextUtils.isEmpty(getDeviceId())) {
            return getDeviceId();
        }

        if (!TextUtils.isEmpty(getMacAddress())) {
            return getMacAddress();
        }
        return " ";
    }

    public String getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }

        return null;

    }

    public String getMacAddress() {
        // start get mac address
        WifiManager wifiMan = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        if (wifiMan != null) {
            WifiInfo wifiInf = wifiMan.getConnectionInfo();
            if (wifiInf != null && wifiInf.getMacAddress() != null) {
                // 48位，如FA:34:7C:6D:E4:D7
                return wifiInf.getMacAddress();
            }
        }
        return null;
    }

    /**
     * device model name, e.g: GT-I9100
     *
     * @return the user_Agent
     */
    public String getDevice() {
        return Build.MODEL;
    }

    /**
     * device factory name, e.g: Samsung
     *
     * @return the vENDOR
     */
    public String getVendor() {
        return Build.BRAND;
    }

    /**
     * @return the SDK version
     */
    public int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * @return the OS version
     */
    public String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * Retrieves application's version number from the manifest
     *
     * @return versionName
     */
    public String getVersion() {
        String version = "0.0.0";
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(
                    getPackageName(), 0);
            version = packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

}
