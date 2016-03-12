package com.weidukeji.agriculture.engine;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.weidukeji.agriculture.base.WeDoApp;
import com.weidukeji.agriculture.network.CacheKey;
import com.weidukeji.agriculture.network.WdCache;
import com.weidukeji.agriculture.utils.AndLogger;

/**
 * Created by zhsheng on 2015/1/3.
 */
public class GdMapLocationListener implements AMapLocationListener {
    private WdCache wdCache;
    public String location;
    private GdMapLocationListener() {
        wdCache = WdCache.get(WeDoApp.getContext());
    }

    private static GdMapLocationListener gdMapLocationListener = new GdMapLocationListener();

    public static GdMapLocationListener getWBaidDuLocationListener() {
        return gdMapLocationListener;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        String address =aMapLocation.getAddress();
        AndLogger.zhshengLog().d("高德地图"+aMapLocation.getProvider()+"定位地址:" + address + " 街道区域:" + aMapLocation.getDistrict());
        if(!TextUtils.isEmpty(address)){
            location = wdCache.getAsString(CacheKey.LOCATION);
            if(TextUtils.isEmpty(location)||!address.equalsIgnoreCase(location)){
                wdCache.put(CacheKey.LOCATION,address);
            }
            if(wdLocationListener!=null){
                wdLocationListener.getLocation(aMapLocation);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public WdLocationListener wdLocationListener;

    public interface WdLocationListener {
        void getLocation(AMapLocation aMapLocation);
    }

    public void registerWdLocationListener(WdLocationListener wdLocationListener) {
        this.wdLocationListener = wdLocationListener;
    }
}
