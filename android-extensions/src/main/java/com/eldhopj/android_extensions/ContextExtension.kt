package com.eldhopj.android_extensions

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.AnyRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresPermission
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference
import org.jetbrains.annotations.NotNull

/**
 * Get Color from res
 *
 * @param colorId color resource id
 * @return [Int] A single color value
 */
fun Context.getColorCompat(@ColorRes colorId: Int): Int = ContextCompat.getColor(this, colorId)

/**
 * Get drawable from res
 *
 * @param id drawable resource id
 * @return [Drawable] Drawable An object that can be used to draw this resource.
 */
fun Context.getDrawableCompat(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(this, id)

/**
 * Shows Toast message
 *
 * @param stringRes String res to display
 * @param duration Toast duration. Default is short
 */
fun Context?.toast(@StringRes stringRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    if (this == null) return
    Toast.makeText(this, stringRes, duration).show()
}

/**
 * Shows Toast message
 *
 * @param message Message to display
 * @param duration Toast duration. Default is short
 */
fun Context?.toast(@NotNull message: String, duration: Int = Toast.LENGTH_SHORT) {
    if (this == null) return
    Toast.makeText(this, message, duration).show()
}


/**
 * Open an url in browser
 *
 * @param url web url
 * @param newTask
 */
fun Context.browse(url: String?, newTask: Boolean = false) {
    if (url == null) return
    try {
        val intent = Intent(ACTION_VIEW, Uri.parse(url))
        if (newTask) intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
        this.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        // no op
    }
}

/**
 * Hides the keyboard from the user
 * @param view
 */
fun Context?.hideKeyboard(view: View) {
    val imm = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * Displays the keyboard to the user
 * @param view view
 */
fun Context?.showKeyboard(view: View) {
    val imm = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

/**
 * Checks whether device is connected to network or not
 *
 * @return @return[Boolean] if connected returns true
 */
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
@Suppress("DEPRECATION")
fun Context?.isOnline(): Boolean = this?.let {
    val netWorkInfo: NetworkInfo? = connectivityManager()?.activeNetworkInfo
    netWorkInfo != null && netWorkInfo.isConnected
} ?: false

/**
 * Checks whether the device is connected to Wi-Fi or not
 *
 * @return[Boolean] if connected returns true
 */
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
fun Context?.isConnectedToWifiNetwork(): Boolean = this?.let {
    val cm = connectivityManager()
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val activeNetwork = cm?.activeNetwork
        val capabilities = cm?.getNetworkCapabilities(activeNetwork)
        capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    } else {
        cm?.activeNetworkInfo?.type == ConnectivityManager.TYPE_WIFI
    }
} ?: false


/**
 * Checks whether the device is connected to Mobile-data or not
 *
 * @return[Boolean] if connected returns true
 */
@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
fun Context?.isConnectedToCellularNetwork(): Boolean {
    this?.run {
        val cm = connectivityManager()
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = cm?.activeNetwork
            val capabilities = cm?.getNetworkCapabilities(activeNetwork)
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } else {
            cm?.activeNetworkInfo?.type == ConnectivityManager.TYPE_MOBILE
        }
    }
    return false
}

/**
 * Returns the unique device ID of the Android Mobile
 *
 * @return [String] Device id
 */
@SuppressLint("HardwareIds")
fun Context?.getDeviceId(): String =
    Settings.Secure.getString(this?.contentResolver, Settings.Secure.ANDROID_ID)

/**
 * Opens the App in play store.
 */
fun Context?.openAppInPlayStore() {
    if (this == null) return
    try {
        startActivity(Intent(ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
    } catch (ignore: ActivityNotFoundException) {
        startActivity(
            Intent(
                ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            )
        )
    }
}


/**
 * Get app version code
 *
 * @return [Long] Returns the app version code
 */
fun Context?.getAppVersionCode(): Long {
    if (this == null) return 0L
    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageManager.getPackageInfo(packageName, 0).longVersionCode
        } else {
            packageManager.getPackageInfo(packageName, 0).versionCode.toLong()
        }
    } catch (ignore: Exception) {
        0L
    }
}

/**
 * Get app version name
 *
 * @return [String] Returns the app version name
 */
fun Context?.getAppVersionName(): String {
    if (this == null) return ""
    return packageManager.getPackageInfo(packageName, 0).versionName
}

/**
 * Returns the resource in Uri format
 *
 * @param resId Resource id.
 * @return [Uri] path of the resource.
 */
fun Context?.resourceToUri(@AnyRes resId: Int): Uri? {
    if (this == null) return null
    return Uri.parse(
        "${ContentResolver.SCHEME_ANDROID_RESOURCE}://${resources.getResourcePackageName(resId)}/${
            resources.getResourceTypeName(resId)
        }/${resources.getResourceEntryName(resId)}"
    )
}

/**
 * Returns the App user-agent which can be used to pass in the API headers or params.
 */
fun Context?.getAppUserAgent(): String {
    if (this == null) return ""
    val versionName = try {
        val packageName = packageName
        val info = packageManager.getPackageInfo(packageName, 0)
        info.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        "?"
    }
    return "${getAppName()}/$versionName" +
            " (${Build.MANUFACTURER} ${Build.MODEL};Android ${Build.VERSION.RELEASE})"
}

/**
 * Get app name
 *
 * @return
 */
fun Context.getAppName(): String = applicationInfo.loadLabel(packageManager).toString()

/**
 * Returns the weak reference of the context.
 * @return [WeakReference] instance.
 */
fun Context?.weakReference() = WeakReference(this)


private fun Context?.connectivityManager(): ConnectivityManager? {
    if (this == null) return null
    return ContextCompat.getSystemService(this, ConnectivityManager::class.java)
}
