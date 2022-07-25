Android-Extensions
=====

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/789a027f37c74b558c16c37960b02c6e)](https://www.codacy.com/gh/Eldhopj/android-extensions/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Eldhopj/android-extensions&amp;utm_campaign=Badge_Grade)
[![](https://jitpack.io/v/Eldhopj/android-extensions.svg)](https://jitpack.io/#Eldhopj/android-extensions)

This library contains essential Android extensions which we use in daily projects

How To
--------
 Gradle:

Add it in your `root build.gradle` at the end of repositories:
```gradle
allprojects {
    repositories {
	    ...
	    maven { url 'https://jitpack.io' }
    }
}
```
dependency to your module `build.gradle` file
```gradle
dependencies {
    implementation 'com.github.Eldhopj:android-extensions:0.2'
}
```

For info on using in the maven and others, see the [jitpack][1] docs page.

Usage
-------------------

## **Activity Extensions**

- .**blockInput** -> Blocks the user touch inputs. Usually useful when we doing some loading and we have to prevent user inputs on that moment
- .**unblockInput** -> Unblocks the user touch inputs

Please go thorough the [Activity Extensions][2] code documentation for more information

## **Bundle Extensions**

- .**toStringMap** ->   Convert the key & string values in the bundle to Map entries
- .**toMap** ->   Convert the key & values in the bundle to Map entries

Please go thorough the [Bundle Extension][6] code documentation for more information

 ## **Context Extensions**

- .**getColorCompat** ->  Get Color from res

		Parameters
		1.colorId -> color resource id
- .**getDrawableCompat** ->   Get drawable from res

		Parameters
		1.colorId -> drawable resource id
- .**toast** ->   Shows Toast message

		Parameters
		1.message -> toast message, either in string or string res
		2.duration -> toast duration (not mandatory). Default value is short
- .**browse** ->   Open an url in browser

		Parameters
		1.url -> web url
		2.newTask -> enable FLAG_ACTIVITY_NEW_TASK (not mandatory). Default value is false
- .**hideKeyboard** ->  Hides the keyboard from the user

		Parameters
		1.colorId -> color resource id
- .**showKeyboard** ->  Displays the keyboard to the user

		Parameters
		1.colorId -> color resource id
- .**isOnline** ->   Checks whether device is connected to network or not.
- .**isConnectedToWifiNetwork** ->   Checks whether the device is connected to Wi-Fi or not.
- .**isConnectedToCellularNetwork** ->   Checks whether the device is connected to Mobile-data or not.
- .**openAppInPlayStore** ->   Opens the App in play store. Usually useful for doing review and ratings.
- .**getAppVersionCode** ->   Get app version code
- .**getAppVersionName** ->   Get app version name
- .**resourceToUri** ->   Returns the resource in Uri format
		Parameters
		1.resId -> Resource id.
- .**getAppUserAgent** ->   Returns the App user-agent which can be used to pass in the API headers or params.
- .**getAppName** ->   Get app name
- .**weakReference** ->  Returns the weak reference of the context.

Please go thorough the [Context Extension][3] code documentation

## **EditText Extensions**

- .**value** ->   Get EditText value

Please go thorough the [EditText Extension][4] code documentation for more information

## **Fragment Extensions**

- .**toast** ->   Shows Toast message

		Parameters
		1.message -> toast message, either in string or string res
		2.duration -> toast duration (not mandatory). Default value is short

Please go thorough the [Fragment Extensions][5] code documentation for more information

## **TextView Extensions**

- .**isEllipsized** ->   Returns true if the text is ellipsized
- .**setFontFamily** ->   Overrides the default typeface with the font resource Provided.

		Parameters
		1.fontRes -> Font Resource id
- .**textAppearance** ->   Set Text appearance

		Parameters
		1.context -> context
		2.resId -> Style res id
- .**underLine** ->   Extension method underLine for TextView.
- .**bold** ->   Extension method bold for TextView.

Please go thorough the [TextView Extensions][7] code documentation for more information

## **View Extensions**

- .**setOnSafeClickListener** ->  Restrict multiple consecutive click events for the view.

        Parameters
        1.defaultInterval -> defaultInterval Interval to wait until click is enabled (not mandatory). Default interval is 800ms
- .**enable** ->   Make View Enable
- .**disable** ->   Make view Disable, goes in a disabled color and clicks wont accept
- .**gone** ->   Make the view Gone
- .**visible** ->   Make the view Visible
- .**snackbar** ->   Shows Snackbar

        Parameters
        1.messageRes ->  snackbar message
        2.length -> duration
        3.actionRes -> action button text (optinal). Needed only if there is any action for snackbar
        4.actionColor -> Color of action button (optinal)

Sample Code:

```kotlin
button.setOnSafeClickListener { view ->
	view?.snackbar(
		R.string.app_name,
		Snackbar.LENGTH_INDEFINITE,
		R.string.retry,
		R.color.black
	){
		toast("action clicked")
	}
}
```

Please go thorough the [View Extensions][8] code documentation for more information


[1]: https://jitpack.io/#Eldhopj/android-extensions/Tag
[2]: https://github.com/Eldhopj/android-extensions/blob/master/android-extensions/src/main/java/com/eldhopj/android_extensions/ActivityExtensions.kt "Activity Extenstions"
[3]: https://github.com/Eldhopj/android-extensions/blob/master/android-extensions/src/main/java/com/eldhopj/android_extensions/ContextExtension.kt "Context Extension"
[4]: https://github.com/Eldhopj/android-extensions/blob/master/android-extensions/src/main/java/com/eldhopj/android_extensions/EditTextExtensions.kt "EditText Extension"
[5]: https://github.com/Eldhopj/android-extensions/blob/master/android-extensions/src/main/java/com/eldhopj/android_extensions/FragmentExtensions.kt "Fragment Extenstions "
[6]: https://github.com/Eldhopj/android-extensions/blob/master/android-extensions/src/main/java/com/eldhopj/android_extensions/BundleExtensions.kt "Bundle Extension"
[7]: https://github.com/Eldhopj/android-extensions/blob/master/android-extensions/src/main/java/com/eldhopj/android_extensions/TextViewExtensions.kt "TextView Extenstions"
[8]: https://github.com/Eldhopj/android-extensions/blob/master/android-extensions/src/main/java/com/eldhopj/android_extensions/ViewExtensions.kt "View Extenstions"
