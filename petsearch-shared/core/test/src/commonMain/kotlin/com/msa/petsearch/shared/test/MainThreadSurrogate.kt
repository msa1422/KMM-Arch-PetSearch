package com.msa.petsearch.shared.test

import io.kotest.core.listeners.AfterTestListener
import io.kotest.core.listeners.BeforeTestListener
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import kotlinx.coroutines.CloseableCoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

class MainThreadSurrogate : BeforeTestListener, AfterTestListener {
    private lateinit var mainDispatcher: CloseableCoroutineDispatcher

    @DelicateCoroutinesApi
    override suspend fun beforeTest(testCase: TestCase) {
        mainDispatcher = newSingleThreadContext("UI thread")
        Dispatchers.setMain(mainDispatcher)
        super.beforeTest(testCase)
    }

    override suspend fun afterTest(testCase: TestCase, result: TestResult) {
        Dispatchers.resetMain()
        mainDispatcher.close()
        super.afterTest(testCase, result)
    }
}
