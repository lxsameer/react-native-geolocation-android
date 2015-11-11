package com.lxsameer.geolocation;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

public class LocationModule extends ReactContextBaseJavaModule {

    public LocationModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "geoLocation";
    }


    @ReactMethod
    public void get(Integer interval, Float distance) {

        MyLocationListener mLocationListener = new MyLocationListener(this.getReactApplicationContext());

        LocationManager mLocationManager = this.locationManager();

        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                                                interval,
                                                distance,
                                                mLocationListener);
    }


    private LocationManager locationManager() {
        return (LocationManager) this.getReactApplicationContext().getSystemService(this.getReactApplicationContext().LOCATION_SERVICE);
    }
}
