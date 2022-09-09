package com.petsapp.petfinder.shared.domain.pet_detail_data_source

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}