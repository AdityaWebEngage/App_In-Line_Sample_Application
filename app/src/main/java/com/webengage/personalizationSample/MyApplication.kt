package com.webengage.personalizationSample

import android.app.Application
import com.webengage.personalization.WEPersonalization
import com.webengage.personalizationSample.Utils.Constants
import com.webengage.sdk.android.WebEngage
import com.webengage.sdk.android.WebEngageActivityLifeCycleCallbacks
import com.webengage.sdk.android.WebEngageConfig

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //WebEngage Step 2: Initialize WebEngage SDK
        val config = WebEngageConfig.Builder().setDebugMode(true).setWebEngageKey(Constants.LICENSE_CODE).build()
        registerActivityLifecycleCallbacks(WebEngageActivityLifeCycleCallbacks(this,config))

        //WebEngage Step 3: Initialize WEPersonalization SDK
        WEPersonalization.get().init()
    }
}