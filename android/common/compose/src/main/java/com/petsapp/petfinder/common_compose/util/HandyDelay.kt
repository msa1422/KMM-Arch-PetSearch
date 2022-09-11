package com.petsapp.petfinder.common_compose.util

import android.os.Handler
import android.os.Looper

object HandyDelay {
    private val handler by lazy { Handler(Looper.getMainLooper()) }
    fun with(duration: Long, block: () -> Unit) {
        if (duration == 0L) {
            block()
        } else {
            handler
                .postDelayed(
                    {
                        block()
                        handler.removeCallbacksAndMessages(null)
                    },
                    duration
                )
        }
    }
}
