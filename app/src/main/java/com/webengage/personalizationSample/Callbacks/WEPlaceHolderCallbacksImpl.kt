package com.webengage.personalizationSample.Callbacks

import android.util.Log
import com.webengage.personalization.data.WECampaignData
import com.webengage.personalizationSample.Utils.Constants
import java.lang.Exception

class WEPlaceHolderCallbacksImpl {

    val TAG = Constants.TAG
    /**
     * This callback will get triggered when the campaign data will be received for Placeholder View.
     */
     fun onDataReceived(data: WECampaignData) {
        Log.d(TAG,"WEPlaceholderCallback onDataReceived for target view ID: ${data.targetViewId}")
        Log.d(TAG, "WEPlaceholderCallback onDataReceived : ${data.toJSONString()}")

    }


    /**
     * This callback will get triggered when the campaign showing for a Property or Placeholder
     * will get failed.
     * If the property is already visible, use this method to hide it or to display some default UI.
     */
     fun onPlaceholderException(
        campaignId: String?,
        targetViewId: String,
        error: Exception
    ) {
        Log.e(TAG, "WEPlaceholderCallback onPlaceholderException for $campaignId : ${error.stackTrace}")
    }

    /**
     * When the SDK displays the Campaign on the screen (view event is triggered),
     * this callback is triggered. This will only be triggered for Banner and Text templates.
     * This will not be triggered for Custom templates
     * and in cases where you have called stopRendering through WECampaignCallback.
     */
     fun onRendered(data: WECampaignData) {
        Log.d(TAG, "WEPlaceholderCallback onRendered : ${data.campaignId} in ${data.targetViewId}")

    }
}