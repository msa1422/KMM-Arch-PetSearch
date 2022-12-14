package com.msa.petsearch.shared.ui.home

import app.cash.turbine.test
import com.msa.petsearch.shared.core.test.MainThreadSurrogate
import com.msa.petsearch.shared.core.util.di.CoreUtilModule
import com.msa.petsearch.shared.ui.home.contract.store.HomeAction
import com.msa.petsearch.shared.ui.home.testfake.FakeData
import com.msa.petsearch.shared.ui.home.testfake.FakeSharedUiHomeModule
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.flow.first
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@Suppress("UNUSED")
internal class HomeViewModelTest : FunSpec(), KoinTest {
    private val viewModel by inject<HomeViewModel>()

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
            // Call action
            viewModel.action(HomeAction.GetPetTypes)

            // Now observe state
            viewModel.state.test {
                // First emit would be null as HomeState starts empty
                awaitItem()

                // Second emit should contain the expected PetTypesResponse
                awaitItem().petTypesResponse shouldBe FakeData.petTypesResponse

                // cancel remaining emits and events
                cancelAndIgnoreRemainingEvents()
            }
        }

        test("OnPetTypeTabSelected should update the HomeState with expected response") {
            // Call action
            viewModel.action(HomeAction.OnPetTypeTabSelected("Dog"))

            // Now observe state
            viewModel.state.test {
                awaitItem()

                // Implies that Paging Data has been updated with a new one
                awaitItem().petPagingData.first() shouldNotBe FakeData.fakePagingData

                // cancel remaining emits and events
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
