
package com.reactlibrary;

import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Nullable;

public class ReactNativeCheckAppModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public ReactNativeCheckAppModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReactNativeCheckApp";
    }

    @ReactMethod
    public void getApps(Promise promise) {
        try {
            List<PackageInfo> packages = this.reactContext.getPackageManager().getInstalledPackages(0);
            String ret = "";
            for (final PackageInfo p: packages) {
                    ret += p.packageName + ";";
            }

            promise.resolve(ret);
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    } 

    @ReactMethod
    public void launchApp(String appId, Promise promise) {
        try {

            Intent LaunchIntent = this.reactContext.getPackageManager().getLaunchIntentForPackage(appId);
            getCurrentActivity().startActivity(LaunchIntent);

            promise.resolve("");
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    } 
    
}
