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
                    .maxSizePercent(MEMORY_CACHE_MAX_SIZE)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("image_cache"))
                    .maxSizePercent(DISK_CACHE_MAX_SIZE)
                    .build()
            }
            .build()
    }
}

private const val MEMORY_CACHE_MAX_SIZE = 0.25
private const val DISK_CACHE_MAX_SIZE = 0.25
