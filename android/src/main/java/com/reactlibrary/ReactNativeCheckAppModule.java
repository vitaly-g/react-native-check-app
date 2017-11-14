
package com.reactlibrary;

import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;

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
    private void getApps(Promise promise) {
        try {
            List<PackageInfo> packages = this.reactContext.getPackageManager().getInstalledPackages(0);
            String ret = "";
            for (final PackageInfo p: packages) {
                if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    //ret.add(p.packageName);
                    ret += p.packageName;
                }
            }

            promise.resolve(ret);
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    } 
    
    // @Override
    // public @Nullable Map<String, Object> getConstants() {
    //     Map<String, Object> constants = new HashMap<>();
    //     constants.put("getApps", getApps());
    //     return constants;
    // }
}
