package com.efly.utils;

import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import timber.log.Timber;

public class NotificationReceivedHandler implements OneSignal.NotificationReceivedHandler, OneSignal.NotificationOpenedHandler {
    // This fires when a notification is opened by tapping on it.
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String customKey;
        Timber.d("notificationnotification" + "notificationOpened");

        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Timber.d("OneSignalExample" + "customkey set with value: " + customKey);
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Timber.d("OneSignalExample" + "Button pressed with id: " + result.action.actionID);

        // The following can be used to open an Activity of your choice.
        // Replace - getApplicationContext() - with any Android Context.
//         Intent intent = new Intent(conte, MainActivityO.class);
//         intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//         startActivity(intent);

        // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
        //   if you are calling startActivity above.
     /*
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
    }

    @Override
    public void notificationReceived(OSNotification notification) {
//        Log.i("notification", "notificationReceived");
//        Log.i("notification1", notification.payload.title);
//        Log.i("notification2", notification.payload.body);
//        Log.i("notification", notification.toJSONObject().toString());
//        Log.i("notification3", notification.payload.additionalData.toString());

        JSONObject data = notification.payload.additionalData;
        String customKey;
        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Timber.d("OneSignalExample" + "customkey set with value: " + customKey);
        }
    }
}
