package com.webengage.personalizationSample.Screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.webengage.personalization.WEPersonalization
import com.webengage.personalization.callbacks.WECampaignCallback
import com.webengage.personalization.callbacks.WEPlaceholderCallback
import com.webengage.personalization.data.WECampaignData
import com.webengage.personalizationSample.Callbacks.WECampaignCallbacksImpl
import com.webengage.personalizationSample.Callbacks.WEPlaceHolderCallbacksImpl
import com.webengage.personalizationSample.R
import com.webengage.personalizationSample.Utils.Constants
import com.webengage.sdk.android.WebEngage

class Screen1_Activity : AppCompatActivity(), WECampaignCallback, WEPlaceholderCallback {

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen1)
        supportActionBar?.title = "Screen1"

    }

    override fun onStart() {
        super.onStart()
        WebEngage.get().analytics().screenNavigated("Screen1")
        init()
    }

    private fun init(){
        WEPersonalization.get().registerWEPlaceholderCallback(Constants.SCREEN1_VIEW1, this)
        WEPersonalization.get().registerWEPlaceholderCallback(Constants.SCREEN1_VIEW2, this)
        WEPersonalization.get().registerWECampaignCallback(this)
    }

    override fun onStop() {
        super.onStop()
        WEPersonalization.get().unregisterWECampaignCallback(this)

        WEPersonalization.get().unregisterWEPlaceholderCallback(Constants.SCREEN1_VIEW1)
        WEPersonalization.get().unregisterWEPlaceholderCallback(Constants.SCREEN1_VIEW2)
    }


    override fun onCampaignClicked(
        actionId: String,
        deepLink: String,
        data: WECampaignData
    ): Boolean {
        if(deepLink.contains("screen2",true)){
            val intent = Intent(this,Screen2_Activity::class.java)
            startActivity(intent)
            return true
        }
        return WECampaignCallbacksImpl().onCampaignClicked(actionId, deepLink, data)
    }

    override fun onCampaignException(campaignId: String?, targetViewId: String, error: Exception) {
        WECampaignCallbacksImpl().onCampaignException(campaignId, targetViewId, error)

    }

    override fun onCampaignPrepared(data: WECampaignData): WECampaignData? {
        return WECampaignCallbacksImpl().onCampaignPrepared(data)
    }

    override fun onCampaignShown(data: WECampaignData) {
        WECampaignCallbacksImpl().onCampaignShown(data)

    }


    override fun onDataReceived(data: WECampaignData) {
        WEPlaceHolderCallbacksImpl().onDataReceived(data)
    }

    override fun onPlaceholderException(
        campaignId: String?,
        targetViewId: String,
        error: java.lang.Exception
    ) {
        WEPlaceHolderCallbacksImpl().onPlaceholderException(campaignId,targetViewId, error)
    }

    override fun onRendered(data: WECampaignData) {
        WEPlaceHolderCallbacksImpl().onRendered(data)
    }
}