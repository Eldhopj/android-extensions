package com.eldhopj.android_extensions.utils

import android.os.SystemClock
import android.view.View

/**
 * Safe click listener to restrict multiple click events for the view.
 *
 * @param defaultInterval Interval to wait until click is enabled. Default interval is 2000 Millis
 * @param listener Callback to invoke when the event triggered.
 *
 */
internal class SafeClickListener(
    private var defaultInterval: Long = DEFAULT_INTERVAL,
    private val listener: (View?) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        listener.invoke(v)
    }

    internal companion object {

        /**
         * Default delay between click events is 800ms.
         */
        internal const val DEFAULT_INTERVAL = 800L
    }

}
