package com.msa.petsearch.shared.resources

import dev.icerock.moko.resources.AssetResource

fun AssetResource.toAndroidAssetUri() = "file:///android_asset/$originalPath"
