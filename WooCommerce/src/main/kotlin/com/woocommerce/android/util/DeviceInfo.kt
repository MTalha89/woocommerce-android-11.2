package com.woocommerce.android.util

import android.content.res.Resources
import android.os.Build
import androidx.core.os.ConfigurationCompat

object DeviceInfo {
    val OS: String
        get() = Build.VERSION.RELEASE
    val name: String
        get() {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            return if (model.startsWith(manufacturer)) {
                model
            } else {
                "$manufacturer $model"
            }
        }
    val locale: String?
        get() {
            val locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration)
            return locale[0]?.displayLanguage
        }
}
