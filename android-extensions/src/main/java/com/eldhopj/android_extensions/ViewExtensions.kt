package com.eldhopj.android_extensions

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.NonNull
import androidx.annotation.StringRes
import com.eldhopj.android_extensions.utils.SafeClickListener
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

/**
 * Safe click listener to restrict multiple consecutive click events for the view.
 *
 * @param defaultInterval Interval to wait until click is enabled. Default interval is 2000 Millis
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
 * Show a Snackbar with message
 *
 * @param message Snackbar message
 * @param length Snackbar duration. Default is Long
 */
fun View.snackBar(
    @NonNull message: String,
    @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_LONG
) = snackBarAction(message, length) {}

/**
 * Show a Snackbar with message res
 *
 * @param messageRes Snackbar message res
 * @param length Snackbar duration. Default is Long
 */
fun View.snackBar(
    @StringRes messageRes: Int,
    @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_LONG
) = snackBarAction(messageRes, length) {}


/**
 * Show a Snackbar with message and the action execute immediately after the message shown
 *
 * Sample code :
button.setOnSafeClickListener {
it?.snackBarAction("Snackbar text") {
// ... Snackbar action
}
}
 * @param message Snackbar message
 * @param length Snackbar duration. Default is Long
 * @receiver
 */
inline fun View.snackBarAction(
    @NonNull message: String,
    @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_LONG,
    action: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(this, message, length)
    snack.action()
    snack.show()
}

/**
 * Show a Snackbar with message and the action execute immediately after the message shown
 *
 * Sample code :
button.setOnSafeClickListener {
it?.snackBarAction("Snackbar text") {
// ... Snackbar action
}
}
 * @param messageRes Snackbar message res
 * @param length Snackbar duration. Default is Long
 * @receiver
 */
inline fun View.snackBarAction(
    @StringRes messageRes: Int,
    @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_LONG,
    action: Snackbar.() -> Unit
) {
    val snack = Snackbar.make(this, messageRes, length)
    snack.action()
    snack.show()
}

/**
 * Show a Snackbar action with [actionRes] button, execute the action on tapping on action button
 *
 * Sample code :
button.setOnSafeClickListener {
it?.snackBarAction("Snackbar text") {
action(R.string.snackbar_done_button) {
// ... Snackbar action
}
}
}
 *
 * @param actionRes Action button text res
 * @param color Color res of action text
 * */
fun Snackbar.action(
    @StringRes actionRes: Int,
    @ColorRes color: Int? = null,
    listener: (View) -> Unit
) {
    setAction(actionRes, listener)
    color?.let { setActionTextColor(context.getColorCompat(color)) }
}

/**
 * Show a Snackbar action with [action] button, execute the action on tapping on action button
 *
 * Sample code :
button.setOnSafeClickListener {
it?.snackBarAction("Snackbar text") {
action("Done") {
// ... Snackbar action
}
}
}
 * @param action Action button text
 * @param color Color res of action text
 * */
fun Snackbar.action(action: String, @ColorRes color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(context.getColorCompat(color)) }
}
