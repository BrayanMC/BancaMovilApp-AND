package com.bmc.navigation

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

object FeatureNavigationHelper {

    fun navigate(context: Context?, featureModule: FeatureModule) {
        navigate(
            context,
            featureModule,
            null
        )
    }

    fun navigate(
        context: Context?,
        featureModule: FeatureModule,
        bundle: Bundle?,
        flags: Int? = null,
        options: ActivityOptions? = null
    ) {
        context?.let {
            val intent = Intent()
            intent.setClassName(it, featureModule.activityName)
            bundle?.let { extras -> intent.putExtras(extras) }
            flags?.let { intent.flags = flags }
            it.startActivity(intent, options?.toBundle())
        }
    }

    fun navigateWithResult(
        activity: Activity,
        featureModule: FeatureModule,
        bundle: Bundle?,
        requestCode: Int,
        options: ActivityOptions? = null
    ) {
        val intent = Intent()
        intent.setClassName(activity, featureModule.activityName)
        bundle?.let { extras -> intent.putExtras(extras) }
        activity.startActivityForResult(intent, requestCode, options?.toBundle())
    }

    fun navigateWithResult(
        fragment: Fragment,
        featureModule: FeatureModule,
        bundle: Bundle?,
        requestCode: Int,
        options: ActivityOptions? = null
    ) {
        val intent = Intent()
        intent.setClassName(fragment.requireContext(), featureModule.activityName)
        bundle?.let { extras -> intent.putExtras(extras) }
        fragment.startActivityForResult(intent, requestCode, options?.toBundle())
    }
}
