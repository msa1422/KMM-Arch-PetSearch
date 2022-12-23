package com.msa.petsearch.shared.ui.home

import app.cash.turbine.test
import com.msa.petsearch.shared.core.test.MainThreadSurrogate
import com.msa.petsearch.shared.core.util.di.CoreUtilModule
import com.msa.petsearch.shared.ui.home.contract.store.GetInitialData
import com.msa.petsearch.shared.ui.home.contract.store.OnPetTypeTabChanged
import com.msa.petsearch.shared.ui.home.testfake.FakeData
import com.msa.petsearch.shared.ui.home.testfake.FakeSharedUiHomeModule
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.first
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@OptIn(DelicateCoroutinesApi::class)
@Suppress("UNUSED")
internal class HomeViewModelTest : FunSpec(), KoinTest {
    init {
        extension(MainThreadSurrogate())

        beforeTest {
            startKoin {
                modules(FakeSharedUiHomeModule, CoreUtilModule)
            }
        }

        afterTest {
            stopKoin()
        }

        test("GetPetTypes should update HomeState with expected response") {
            val viewModel by inject<HomeViewModel>()

            // Now observe state
            viewModel.state.test {
                // Call action
                viewModel.action(GetInitialData)

                // First emit would be null as HomeState starts empty
                awaitItem()

                // Second emit should contain the expected PetTypesResponse
                awaitItem().petTypes shouldBe FakeData.petTypesResponse

                // cancel remaining emits and events
                cancelAndIgnoreRemainingEvents()
            }
        }

        test("OnPetTypeTabSelected should update the HomeState with expected response") {
            val viewModel by inject<HomeViewModel>()

            // Now observe state
            viewModel.state.test {
                // Call action
                viewModel.action(OnPetTypeTabChanged("Dog"))

                awaitItem()

                // Implies that Paging Data has been updated with a new one
                awaitItem().petPagingData.first() shouldNotBe FakeData.fakePagingData

                // cancel remaining emits and events
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
