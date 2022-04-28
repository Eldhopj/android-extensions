package com.eldhopj.android_extensions

import android.app.Activity
import android.view.WindowManager

/**
 * Blocks the user touch inputs
 * Usually useful when we doing some loading and we have to prevent user inputs on that moment
 *
 * */
fun Activity.blockInput() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

/**
 * Unblocks the user touch inputs
 *
 * */
fun Activity.unblockInput() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}
