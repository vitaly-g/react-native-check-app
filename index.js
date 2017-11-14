var installedApps = require('react-native').NativeModules.ReactNativeCheckApp;

module.exports = {
    getApps: function() {
        return installedApps.getApps;
    },
    getNonSystemApps: function(){
        return installedApps.getNonSystemApps;
    }
};
