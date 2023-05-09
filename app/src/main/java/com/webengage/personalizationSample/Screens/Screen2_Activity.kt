package com.webengage.personalizationSample.Screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.inline.InlineContentView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.webengage.personalization.WEPersonalization
import com.webengage.personalization.callbacks.WECampaignCallback
import com.webengage.personalization.callbacks.WEPlaceholderCallback
import com.webengage.personalization.data.WECampaignData
import com.webengage.personalizationSample.Callbacks.WECampaignCallbacksImpl
import com.webengage.personalizationSample.Callbacks.WEPlaceHolderCallbacksImpl
import com.webengage.personalizationSample.R
import com.webengage.personalizationSample.Utils.Constants
import com.webengage.sdk.android.WebEngage

class Screen2_Activity : AppCompatActivity(), WECampaignCallback, WEPlaceholderCallback {

    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)
        supportActionBar?.title = "Screen2"

    }

    override fun onStart() {
        super.onStart()
        WebEngage.get().analytics().screenNavigated("Screen2")
        init()
    }
    private fun init() {
        WEPersonalization.get().registerWEPlaceholderCallback(Constants.SCREEN2_CUSTOM1, this)
        WEPersonalization.get().registerWEPlaceholderCallback(Constants.SCREEN2_VIEW2, this)
        WEPersonalization.get().registerWECampaignCallback(this)
    }

    override fun onStop() {
        super.onStop()
        WEPersonalization.get().unregisterWECampaignCallback(this)
        WEPersonalization.get().unregisterWEPlaceholderCallback(Constants.SCREEN2_CUSTOM1)
        WEPersonalization.get().unregisterWEPlaceholderCallback(Constants.SCREEN2_VIEW2)

    }

    fun renderCustomView(data: WECampaignData) {
        findViewById<TextView>(R.id.S2C1_campaignID).text = data.campaignId
        val customData = data.content!!.customData
        val deepLink : String = customData["deeplink"].toString()
        if(data.content != null) {
            findViewById<TextView>(R.id.S2C1_deeplink).text = deepLink
            findViewById<TextView>(R.id.S2C1_campaignData).text = customData.toString()
        }
        findViewById<CardView>(R.id.Screen2View1).visibility = View.VISIBLE
        data.trackImpression()
        findViewById<CardView>(R.id.Screen2View1).setOnClickListener(View.OnClickListener {
            data.trackClick()
            if(deepLink.startsWith("https://")){
                val uri = Uri.parse(deepLink)
                val deeplinkIntent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(deeplinkIntent)
            }
            else{
                Log.d(Constants.TAG,"Clicked on deeplink $deepLink of custom view ${data.targetViewId}")
            }
        })
    }

    override fun onCampaignClicked(
        actionId: String,
        deepLink: String,
        data: WECampaignData
    ): Boolean {
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
        if(data.targetViewId.equals(Constants.SCREEN2_CUSTOM1, true))
        {
            renderCustomView(data)
        }
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