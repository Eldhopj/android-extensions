package com.eldhopj.android_extensions

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.text.Layout
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.annotation.StyleRes
import androidx.core.content.res.ResourcesCompat


/**
 * Checks whether the text is ellipsized or not.
 *
 * @return [Boolean] true if ellipsized.
 */
fun TextView?.isEllipsized(): Boolean {
    this ?: return false
    val l: Layout = layout
    val lines: Int = l.lineCount
    return lines > 0 && l.getEllipsisCount(lines - 1) > 0
}

/**
 * Overrides the default typeface with the font resource Provided.
 *
 * @param font Font Resource id
 */
fun TextView.setFontFamily(@FontRes font: Int) {
    this.typeface = ResourcesCompat.getFont(context, font)
}

/**
 * Set Text appearance
 *
 * @param context context
 * @param resId Style res id
 */
fun TextView.textAppearance(context: Context, @StyleRes resId: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        setTextAppearance(context, resId)
    } else {
        setTextAppearance(resId)
    }
}

/**
 * Extension method underLine for TextView.
 */
fun TextView.underLine() {
    paint.flags = paint.flags or Paint.UNDERLINE_TEXT_FLAG
    paint.isAntiAlias = true
}

/**
 * Extension method bold for TextView.
 */
fun TextView.bold() {
    paint.isFakeBoldText = true
    paint.isAntiAlias = true
}
