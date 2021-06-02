package jafari.alireza.contacts.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.app.ActivityCompat


object NavigationUtils {


    fun startActivity(
        currentActivity: Activity,
        destinationActivityClass: Class<*>,
        bundle: Bundle?,
        keepCurrentActivity: Boolean = true
    ) {
        val intent = Intent(currentActivity, destinationActivityClass)
        bundle?.let { intent.putExtras(it) }
        ActivityCompat.startActivity(currentActivity, intent, null)
        if (!keepCurrentActivity)
            currentActivity.finish()

    }

    fun startActivityWithIntent(context: Context, action: String, data: Uri) {
        val intent = Intent(action)
        intent.data = data
        context.startActivity(intent)

    }

    fun openCallWithIntent(context: Context, phone: String) {
        startActivityWithIntent(context, Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
    }

    fun openBrowserWithIntent(context: Context, url: String) {
        startActivityWithIntent(context, Intent.ACTION_VIEW, Uri.parse(url))
    }

}

