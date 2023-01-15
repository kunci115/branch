package com.example.greencard

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import io.branch.referral.Branch
import io.branch.referral.Branch.sessionBuilder


// branch key key_live_emY7ZuH0QuV6t9zAMJKR4epkrEdSq7un
// branch secret secret_live_aIXtiZcHAkq6OXG8WECHGMzChV7zU29u
//appid 1142677860146762356
class MainActivity : AppCompatActivity() {
    override fun onStart() {
        super.onStart()
        Branch.sessionBuilder(this).withCallback { branchUniversalObject, linkProperties, error ->
            if (error != null) {
                Log.e("BranchSDK_Tester", "branch init failed. Caused by -" + error.message)
            } else {
                Log.e("BranchSDK_Tester", "branch init complete!")
                if (branchUniversalObject != null) {
                    Log.e("BranchSDK_Tester", "title " + branchUniversalObject.title)
                    Log.e("BranchSDK_Tester", "CanonicalIdentifier " + branchUniversalObject.canonicalIdentifier)
                    Log.e("BranchSDK_Tester", "metadata " + branchUniversalObject.contentMetadata.convertToJson())
                }
                if (linkProperties != null) {
                    Log.e("BranchSDK_Tester", "Channel " + linkProperties.channel)
                    Log.e("BranchSDK_Tester", "control params " + linkProperties.controlParams)
                }
            }
        }.withData(this.intent.data).init()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            intent.putExtra("branch_force_new_session", true)
        }
        setIntent(intent)
        sessionBuilder(this).withCallback { referringParams, error ->
            if (error != null) {
                Log.e("BranchSDK_Tester", error.message)
            } else if (referringParams != null) {
                Log.e("BranchSDK_Tester", referringParams.toString())
            }
        }.reInit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Branch logging for debugging
//        Branch.enableTestMode()
        Branch.enableLogging()
        // Branch object initialization
        Branch.getAutoInstance(this)

    }

    fun sendMessage(view: View) {
        // Do something in response to button click
        Toast.makeText(this, "Ini click", Toast.LENGTH_SHORT).show()
    }


    fun deepMessage(view: View) {
        // Do something in response to button click
        Toast.makeText(this, "Ini deep", Toast.LENGTH_SHORT).show()
    }
}

