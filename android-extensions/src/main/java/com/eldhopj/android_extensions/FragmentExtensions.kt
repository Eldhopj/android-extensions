package com.eldhopj.android_extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment


/**
 * Shows Toast message
 *
 * @param message Message to display
 * @param duration Toast duration. Default is short
 */
fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().toast(message, duration)
}

/**
 * Shows Toast message
 *
 * @param stringRes String res to display
 * @param duration Toast duration. Default is short
 */
fun Fragment.toast(@StringRes stringRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().toast(stringRes, duration)
}
