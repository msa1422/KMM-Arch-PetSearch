package com.petsapp.petfinder

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy

class MainApplication: Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    @Suppress("MagicNumber")
    override fun newImageLoader(): ImageLoader {
        return ImageLoader
            .Builder(this)
            .crossfade(true)
            .components {
                add(SvgDecoder.Factory())
            }
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.025)
                    .build()
            }
            .build()
    }
}
