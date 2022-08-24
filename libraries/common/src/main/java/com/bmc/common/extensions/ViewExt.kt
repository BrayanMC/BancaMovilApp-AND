package com.bmc.common.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard(activity: Activity, clearFocus: Boolean = true) {
    val imm = (activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
    imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    if (clearFocus) activity.currentFocus?.clearFocus()
}

fun View.showKeyboard(activity: Activity) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(activity.currentFocus, InputMethodManager.SHOW_IMPLICIT)
}