package com.petsapp.petfinder.shared.data_infrastructure_cache

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}