package com.msa.petsearch.shared.domain.homeuicontract

import app.cash.turbine.test
import com.msa.petsearch.shared.coreutil.di.DomainCoreUtilModule
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeAction
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeState
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeData
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeHomeUiContractModule
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.flow.first
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@Suppress("UNUSED")
internal class HomeViewModelTest : FunSpec(), KoinTest {
    private lateinit var koinApplication: KoinApplication
    private lateinit var state: HomeState
    private val viewModel by inject<HomeViewModel>()

    init {
        beforeTest {
            if (!this::koinApplication.isInitialized) {
                koinApplication = startKoin {
                    modules(FakeHomeUiContractModule, DomainCoreUtilModule)
                }
            }

            state = HomeState(petPagingData = FakeData.fakePagingData)
        }

        test("GetPetTypes should update HomeState with expected response") {
            // Call action
            viewModel.action(HomeAction.GetPetTypes)

            // Now observe state
            viewModel.observeState().test {
                // First emit would be null as HomeState starts empty
                awaitItem().petTypesResponse shouldBe null

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
            viewModel.observeState().test {
                // Implies that Paging Data has been updated with a new one
                awaitItem().petPagingData.first() shouldNotBe FakeData.fakePagingData

                // cancel remaining emits and events
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}
