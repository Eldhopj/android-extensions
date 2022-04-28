package com.eldhopj.android_extensions

import android.os.Bundle

/**
 * Convert the key & values in the bundle to Map entries
 *
 * @return Returns [Map] instance
 */
fun Bundle?.toStringMap(): Map<String, String?> = if (this == null) emptyMap() else {
    val hashMap: MutableMap<String, String?> = HashMap()
    val keySet = keySet()
    val iterator: Iterator<String> = keySet.iterator()
    while (iterator.hasNext()) {
        val key = iterator.next()
        hashMap[key] = getString(key)
    }
    hashMap
}


/**
 * Convert the key & values in the bundle to Map entries
 *
 * @return Returns [Map] instance
 */
fun Bundle?.toMap(): Map<String, Any?> =
    if (this == null) emptyMap() else {
        val hashMap: MutableMap<String, Any?> = HashMap()
        val keySet = keySet()
        val iterator: Iterator<String> = keySet.iterator()
        while (iterator.hasNext()) {
            val key = iterator.next()
            hashMap[key] = get(key)
        }
        hashMap
    }
