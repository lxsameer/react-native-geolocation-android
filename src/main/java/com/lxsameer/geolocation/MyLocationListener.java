package com.lxsameer.geolocation;

import java.util.HashMap;

import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

public class MyLocationListener implements LocationListener {

    private ReactContext reactContext;
    private static final String TAG = "MyLocationlistener";

    public MyLocationListener(ReactContext reactContext) {
        this.reactContext = reactContext;

    }

    @Override
    public void onLocationChanged(final Location location) {
        Log.v(TAG, "OnLocationChanged");
        WritableMap params = Arguments.createMap();

        params.putDouble("longitude", location.getLongitude());
        params.putDouble("latitude", location.getLatitude());
        params.putDouble("altitude", location.getAltitude());
        params.putDouble("bearing", location.getBearing());
        params.putDouble("speed", location.getSpeed());

        sendEvent("location_changed", params);
    }

    @Override
    public void onProviderDisabled (String provider) {
        Log.v(TAG, "onProviderDisabled");

        WritableMap params = Arguments.createMap();

        params.putString("provider", provider);

        sendEvent("provider_disabled", params);

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.v(TAG, "onProviderEnabled");
        WritableMap params = Arguments.createMap();

        params.putString("provider", provider);

        sendEvent("provider_enabled", params);
    }

    @Override
    public void onStatusChanged (String provider, int status, Bundle extras) {
        Log.v(TAG, "onStatusChanged");
        WritableMap params = Arguments.createMap();

        params.putString("provider", provider);
        params.putInt("status", status);

        sendEvent("provider_status_changed", params);

    }

    private void sendEvent(String eventName, @Nullable WritableMap params) {
        this.reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
            .emit(eventName, params);
    }
}
