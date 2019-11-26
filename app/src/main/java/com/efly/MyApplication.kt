package com.efly


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import com.efly.base.BaseRepoistory
import com.efly.di.component.ViewModelComponent
import timber.log.Timber
import timber.log.Timber.DebugTree
import com.efly.di.component.DaggerViewModelComponent
import com.efly.di.modules.ContextModule
import com.efly.repositories.LocalRepository
import com.efly.utils.NotificationReceivedHandler
import com.efly.utils.SHRP_FCMTOKEN
import com.onesignal.OneSignal



class MyApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var appComponent: ViewModelComponent
    }

    override fun onCreate() {
        super.onCreate()
        context = this
//        startKoin {
//            androidLogger()
//            // Android context
//            androidContext(this@MyApplication)
//            // modules
//            modules(appComponent)
//        }


        appComponent = DaggerViewModelComponent.builder().contextModule(ContextModule(context))
            .build()


        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }


        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .autoPromptLocation(true)
            //                .setNotificationOpenedHandler(new NotificationReceivedHandler())
            .setNotificationReceivedHandler(NotificationReceivedHandler())
            .init()

        context = applicationContext

    }

    fun getFCMToken(repoistory: BaseRepoistory): String? {

        var token = LocalRepository.getFromSharedPref(SHRP_FCMTOKEN)
        if (token == null || token.isEmpty()) {

            val status = OneSignal.getPermissionSubscriptionState()

            token = status.subscriptionStatus.userId
            if (token != null) {
                LocalRepository.setIntoSharedPref(
                    getString(R.string.fcmToken), token
                )
                Log.e("FCM TOKEN", token)
                // we should send it here to the server
            }

        }

        return token
    }


}