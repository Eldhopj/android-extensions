package com.eldhopj.android_extensions

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.eldhopj.android_extensions.utils.SafeClickListener
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

/**
 * Safe click listener to restrict multiple consecutive click events for the view.
 *
 * @param defaultInterval Interval to wait until click is enabled. Default interval is 800ms
 * @param listener Callback to invoke when the event triggered.
 *
 */
fun View.setOnSafeClickListener(
    defaultInterval: Long = SafeClickListener.DEFAULT_INTERVAL,
    listener: (View?) -> Unit
) {
    this.setOnClickListener(SafeClickListener(defaultInterval, listener))
}

/**
 * Make View Enable
 *
 * */
fun View?.enable() {
    this ?: return
    apply {
        isEnabled = true
        alpha = 1.0F
        isClickable = true
    }
}

/**
 * Make view Disable
 *
 * */
fun View?.disable() {
    this ?: return
    apply {
        isEnabled = false
        isClickable = false
        alpha = 0.5f
    }
}

/**
 * Make the view Gone
 *
 */
fun View?.gone() {
    this ?: return
    visibility = View.GONE
}

/**
 * Make the view Visible
 *
 */
fun View?.visible() {
    this ?: return
    visibility = View.VISIBLE
}

/**
 * Show a Snackbar
 *
 * ```
 * button.setOnSafeClickListener { view ->
 *      view?.snackbar(
 *          R.string.app_name,
 *          Snackbar.LENGTH_INDEFINITE,
 *          R.string.retry,
 *          R.color.black
 *      ){
 *          toast("action clicked")
 *      }
 * }
 * ```
 *
 * @param messageRes snackbar message string resource
 * @param length snackbar duration.
 * @param actionRes Action button text res. optional needed only if we are setting an action
 * @param actionColor Color resource for action text. optional
 * @param action action happens when we click on snackbar action button. optional
 */
fun View.snackbar(
    @StringRes messageRes: Int,
    @BaseTransientBottomBar.Duration length: Int,
    @StringRes actionRes: Int?,
    @ColorRes actionColor: Int? = null,
    action: ((View) -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, messageRes, length)
    if (actionRes != null && action != null) {
        actionColor?.let { snackbar.setActionTextColor(context.getColorCompat(it)) }
        snackbar.setAction(actionRes) {
            action(this)
        }.show()
    } else {
        snackbar.show()
    }
}
