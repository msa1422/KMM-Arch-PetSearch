package com.msa.petsearch.shared.ui.home

import app.cash.turbine.test
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.map
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.test.MainThreadSurrogate
import com.msa.petsearch.shared.ui.home.contract.NavigateToPetDetail
import com.msa.petsearch.shared.ui.home.contract.OnPetTypeTabChanged
import com.msa.petsearch.shared.ui.home.di.SharedUiHomeTestModule
import com.msa.petsearch.shared.ui.home.testdoubles.TestFake
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.DelicateCoroutinesApi
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
                modules(SharedUiHomeTestModule)
            }
        }

        afterTest {
            stopKoin()
        }

        test("GivenInitialObserver_ViewModel_ReturnsPetTypesAfterObservingPagingData") {
            val viewModel by inject<HomeViewModel>()

            // Start observing petTypes
            viewModel.petTypes.test {
                // First emit would be empty list as LoadPetTypesUseCase hasn't been invoked yet
                awaitItem() shouldBe emptyList()

                // Attach an observer to the pagingData in viewModel
                viewModel.pagingData.test { }

                // Observing the pagingData will trigger the LoadPetTypesUseCase invocation
                // which will result in second emit with desired data
                awaitItem() shouldBe TestFake.petTypesResponse.types
            }
        }

        test("GivenInitialObserverToPagingData_ViewModel_ReturnsPagingDataWithPetInfoList") {
            val viewModel by inject<HomeViewModel>()

            // Start observing petTypes
            viewModel.pagingData.test {
                awaitItem()
                    .shouldBeInstanceOf<PagingData<PetInfo>>()
                    .map {
                        // Can't think of a better way than this to test
                        // PagingData exposes only a couple of methods.
                        // First element in PetTypes in related to "Dog", so dog result
                        TestFake.petDogSearchResponse.animals!! shouldContain it
                        return@map it
                    }
            }
        }

        test("GivenOnPetTypeTabChanged_ViewModel_UpdatesPagingData") {
            val viewModel by inject<HomeViewModel>()

            // Start observing petTypes
            viewModel.pagingData.test {
                awaitItem()
                    .shouldBeInstanceOf<PagingData<PetInfo>>()
                    .map {
                        // First element in PetTypes in related to "Dog", so dog result
                        TestFake.petDogSearchResponse.animals!! shouldContain it
                        return@map it
                    }

                viewModel.dispatch(OnPetTypeTabChanged("Cat"))
                awaitItem()
                    .shouldBeInstanceOf<PagingData<PetInfo>>()
                    .map {
                        TestFake.petCatSearchResponse.animals!! shouldContain it
                        return@map it
                    }
            }
        }

        test("GivenNavigateToPetDetailAction_ViewModel_EmitsItInNavigationEventSharedFlow") {
            val viewModel by inject<HomeViewModel>()
            val navigation = NavigateToPetDetail(TestFake.petCatSearchResponse.animals!!.first())

            viewModel.navigationEvent.test {
                viewModel.dispatch(navigation)
                awaitItem() shouldBe navigation.event
            }
        }
    }
}
