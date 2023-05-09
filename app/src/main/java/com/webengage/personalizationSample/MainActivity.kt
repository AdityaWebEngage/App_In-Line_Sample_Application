package com.webengage.personalizationSample

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.webengage.personalizationSample.Screens.Screen1_Activity
import com.webengage.sdk.android.WebEngage


class MainActivity : AppCompatActivity() {

    lateinit var mCuidEditText : EditText
    lateinit var mloginButton : Button
    lateinit var mnavigateButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportActionBar?.title = "WE Personalization"

        initViews()
    }

    private fun initViews() {

        mCuidEditText = findViewById<EditText>(R.id.edit_text_cuid)
        mloginButton = findViewById<Button>(R.id.button_login)
        mnavigateButton = findViewById<Button>(R.id.button_navigate)

        mnavigateButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,Screen1_Activity::class.java)
            startActivity(intent)
        })

        if(!TextUtils.isEmpty(WebEngage.get().cuid))
        {
           loggedIn(WebEngage.get().cuid)
        }
        else{
            loggedOut()
        }
    }

    fun loggedIn(cuid : String){
        mCuidEditText.isEnabled = false
        mCuidEditText.setText(cuid)
        mloginButton.text = "logout"
        mloginButton.setOnClickListener(View.OnClickListener {
            WebEngage.get().user().logout()
            loggedOut()
        })
    }

    fun loggedOut(){
        mCuidEditText.setText("")
        mCuidEditText.isEnabled = true
        mloginButton.text = "login"
        mloginButton.setOnClickListener(View.OnClickListener {
            if(!TextUtils.isEmpty(mCuidEditText.text.toString())){
                val cuid = mCuidEditText.text.toString()
                WebEngage.get().user().login(cuid)
                loggedIn(cuid)
            }
        })
    }
}
