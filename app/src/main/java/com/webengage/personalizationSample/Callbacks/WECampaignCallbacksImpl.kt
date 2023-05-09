package com.webengage.personalizationSample.Callbacks

import android.util.Log
import com.webengage.personalization.data.WECampaignData
import com.webengage.personalizationSample.Utils.Constants

class WECampaignCallbacksImpl {

    val TAG = Constants.TAG

    /**
     * This is triggered when data for the qualified campaign
     * is retrieved from the WebEngage servers. You can also stop rendering the campaign by calling
     * stopRendering() method of WECampaignData (generally this can come handy if you want to just
     * retrieve some data and render on your own for layout.
     */
     fun onCampaignPrepared(data: WECampaignData): WECampaignData? {
        Log.d(TAG, "WECampaignCallbacksImpl onCampaignPrepared : ${data.campaignId} for ${data.targetViewId}")
        // Call data.stopRendering() and render custom views
        if(data.targetViewId.equals(Constants.SCREEN2_CUSTOM1,true)){
            Log.d(TAG, "Stopping rendering")
            data.stopRendering()
        }
        Log.d(TAG, "WECampaignCallbacksImpl onCampaignPrepared : ${data.toJSONString()}")
        return data
    }


    /**
     * When the SDK displays the Campaign on the screen, this callback is triggered.
     * This will only be triggered for Banner and Text templates and not for Custom templates.
     * This callback will also not be triggered in cases where you have called stopRendering
     */
     fun onCampaignShown(data: WECampaignData) {
        //For Custom Views, call data.trackImpression()
        if(data.targetViewId.equals(Constants.SCREEN2_CUSTOM1,true)){
            data.trackImpression()
        }
        Log.d(TAG, "WECampaignCallbacksImpl onCampaignShown : ${data.campaignId} in ${data.targetViewId} ")
    }


    /**
     * This callback will get triggered when clicked on the campaign view rendered by the SDK.
     * This will only be triggered for Banner and Text templates and not Custom templates
     * returning false results in automatic redirection because the SDK will handle and
     * attempt to open the link, whereas when returning true instructs the SDK not to perform
     * any redirection because the application will handle the same.
     */
     fun onCampaignClicked(
        actionId: String,
        deepLink: String,
        data: WECampaignData
    ): Boolean {
        // Returning true would not redirect automatically to action Url. Write your redirection code here and return false.
        // Returning false would open Action Url in Browser
        //For Custom Views, call data.trackClick()
        Log.d(TAG, "WECampaignCallbacksImpl onCampaignClicked : actionId: $actionId \ndeeplink : $deepLink ")
        if(data.targetViewId.equals(Constants.SCREEN2_CUSTOM1,true)){
            data.trackClick()
        }
        return false
    }

    /**
     *  This callback will get triggered when the campaign retrieval/showing will get failed.
     */
     fun onCampaignException(campaignId: String?, targetViewId: String, error: Exception) {
        Log.e(TAG, "WECampaignCallbacksImpl onCampaignException : Exception while rendering $campaignId in $targetViewId")

    }



}