package com.msa.petsearch.shared.ui.home.contract

import com.kuuurt.paging.multiplatform.PagingData
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.commonflow.CommonFlow
import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.domain.home.HomeUseCaseWrapper
import com.msa.petsearch.shared.domain.home.usecase.LoadPetTypesUseCase
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import com.msa.petsearch.shared.ui.home.contract.store.HomeAction
import com.msa.petsearch.shared.ui.home.contract.store.HomeSideEffect
import com.msa.petsearch.shared.ui.home.testfake.FakeAnimalRepository
import com.msa.petsearch.shared.ui.home.testfake.FakeData
import com.msa.petsearch.shared.ui.home.testfake.FakeErrorAnimalRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.Dispatchers

@Suppress("UNUSED")
internal class HomeProcessorTest : FunSpec({
    lateinit var processor: HomeProcessor
    lateinit var useCases: HomeUseCaseWrapper
    lateinit var repository: AnimalRepository
    val sideEffect = HomeSideEffect.LoadPetsFromNetwork("Dog", 1, FakeData.searchParams)

    beforeTest {
        repository = when (it.name.testName) {
            "LoadPetTypesFromNetwork should handle exceptions" -> FakeErrorAnimalRepository()
            else -> FakeAnimalRepository()
        }

        useCases = HomeUseCaseWrapper(
            getPetTypes = LoadPetTypesUseCase(repository),
            getPets = LoadPetsUseCase(repository)
        )
        processor = HomeProcessor(useCases, Dispatchers.Main)
    }

    test("LoadPetTypesFromNetwork should return expected Action") {
        processor.dispatchSideEffect(HomeSideEffect.LoadPetTypesFromNetwork)
            .shouldBeInstanceOf<HomeAction.UpdatePetTypesInState>()
            .petTypesResponse shouldBe FakeData.petTypesResponse
    }

    test("LoadPetTypesFromNetwork should handle exceptions") {
        processor.dispatchSideEffect(HomeSideEffect.LoadPetTypesFromNetwork)
            .shouldBeInstanceOf<HomeAction.Error>()
            .message.text shouldBe FakeData.petTypeResponseErrorMessage
    }

    test("LoadPetsFromNetwork should return expected Action") {
        processor.dispatchSideEffect(sideEffect)
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
        processor.dispatchSideEffect(sideEffect)

        // Now check with the Actual SideEffect
        processor.dispatchSideEffect(HomeSideEffect.LoadPetListNextPageFromNetwork)
            .shouldBeInstanceOf<HomeAction.OnLoadPetListNextPageActionComplete>()
    }
})
