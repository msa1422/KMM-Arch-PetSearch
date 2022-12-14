package com.msa.petsearch.shared.resources

import dev.icerock.moko.resources.AssetResource

val AssetResource.uri
    get() = "file:///android_asset/$originalPath"
