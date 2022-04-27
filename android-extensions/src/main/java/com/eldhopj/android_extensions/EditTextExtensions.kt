package com.eldhopj.android_extensions

import android.widget.EditText

/**
 * Get EditText value
 *
 * @return [String] returns edittext value
 */
val EditText.value
    get() = text?.toString() ?: ""
