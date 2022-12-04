package com.msa.petsearch.shared.domain.homeuicontract.contract

import com.kuuurt.paging.multiplatform.PagingData
import com.msa.petsearch.shared.coreentity.petinfo.PetInfo
import com.msa.petsearch.shared.domain.homedatasource.HomeDataSource
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeAction
import com.msa.petsearch.shared.domain.homeuicontract.contract.store.HomeSideEffect
import com.msa.petsearch.shared.domain.homeuicontract.interactor.LoadPetTypesUseCase
import com.msa.petsearch.shared.domain.homeuicontract.interactor.LoadPetsUseCase
import com.msa.petsearch.shared.domain.homeuicontract.interactor.UseCaseWrapper
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeData
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeErrorHomeDataSource
import com.msa.petsearch.shared.domain.homeuicontract.testfake.FakeHomeDataSource
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.shouldBeInstanceOf

@Suppress("UNUSED")
internal class HomeProcessorTest : FunSpec({
    lateinit var processor: HomeProcessor
    lateinit var useCases: UseCaseWrapper
    lateinit var dataSource: HomeDataSource
    val loadPetsFromNetworkSideEffect = HomeSideEffect.LoadPetsFromNetwork(
        type = "Dog", page = 1, params = FakeData.searchParams
    )

    beforeTest {
        dataSource = when (it.name.testName) {
            "LoadPetTypesFromNetwork should handle exceptions" -> FakeErrorHomeDataSource()
            else -> FakeHomeDataSource()
        }

        useCases = UseCaseWrapper(
            getPetTypes = LoadPetTypesUseCase(dataSource),
            getPets = LoadPetsUseCase(dataSource)
        )
        processor = HomeProcessor(useCases)
    }

    test("LoadPetTypesFromNetwork should return expected Action") {
        processor.dispatchSideEffect(HomeSideEffect.LoadPetTypesFromNetwork)
            .shouldBeInstanceOf<HomeAction.UpdatePetTypesInState>()
            .petTypesResponse shouldBe FakeData.petTypesResponse
    }

    test("LoadPetTypesFromNetwork should handle exceptions") {
        processor.dispatchSideEffect(HomeSideEffect.LoadPetTypesFromNetwork)
            .shouldBeInstanceOf<HomeAction.Error>()
            .message?.text shouldBe FakeData.petTypeResponseErrorMessage
    }

    test("LoadPetsFromNetwork should return expected Action") {
        processor.dispatchSideEffect(loadPetsFromNetworkSideEffect)
            .shouldBeInstanceOf<HomeAction.UpdatePetResponseInState>()
            .petPagingData
            .shouldBeInstanceOf<CommonFlow<PagingData<PetInfo>>>()
    }

    test("LoadPetListNextPageFromNetwork should throw exception if called before LoadPetsFromNetwork") {
        val exception = shouldThrow<RuntimeException> {
            processor.dispatchSideEffect(HomeSideEffect.LoadPetListNextPageFromNetwork)
        }

        exception.message shouldContain "lateinit property petListPager has not been initialized"
    }

    test("LoadPetListNextPageFromNetwork should return expected Action") {
        // This call will initialize the Pager components inside the HomeProcessor
        processor.dispatchSideEffect(loadPetsFromNetworkSideEffect)

        // Now check with the Actual SideEffect
        processor.dispatchSideEffect(HomeSideEffect.LoadPetListNextPageFromNetwork)
            .shouldBeInstanceOf<HomeAction.OnLoadPetListNextPageActionComplete>()
    }
})
