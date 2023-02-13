package com.msa.petsearch.shared.ui.home.contract

import com.kuuurt.paging.multiplatform.PagingData
import com.msa.petsearch.shared.core.entity.petinfo.PetInfo
import com.msa.petsearch.shared.core.util.commonflow.CommonFlow
import com.msa.petsearch.shared.data.repository.AnimalRepository
import com.msa.petsearch.shared.domain.home.usecase.LoadPetTypesUseCase
import com.msa.petsearch.shared.domain.home.usecase.LoadPetsUseCase
import com.msa.petsearch.shared.ui.home.contract.store.ForwardInitialDataToState
import com.msa.petsearch.shared.ui.home.contract.store.ForwardPetResponseToState
import com.msa.petsearch.shared.ui.home.contract.store.GetInitialData
import com.msa.petsearch.shared.ui.home.contract.store.IdleAction
import com.msa.petsearch.shared.ui.home.HomeUseCaseWrapper
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
    val sideEffect = OnPetTypeTabChanged("Dog")

    beforeTest {
        repository = when (it.name.testName) {
            "GetInitialData should handle exceptions" -> FakeErrorAnimalRepository()
            else -> FakeAnimalRepository()
        }

        useCases = HomeUseCaseWrapper(
            getPetTypes = LoadPetTypesUseCase(repository),
            getPets = LoadPetsUseCase(repository)
        )
        processor = HomeProcessor(useCases, Dispatchers.Main)
    }

    test("GetInitialData should return expected Action") {
        val action = processor.dispatchSideEffect(GetInitialData)

        action.shouldBeInstanceOf<ForwardInitialDataToState>()
        action.petTypes shouldBe FakeData.petTypesResponse
        action.petPagingData.shouldBeInstanceOf<CommonFlow<PagingData<PetInfo>>>()
    }

    test("GetInitialData should handle exceptions") {
        processor.dispatchSideEffect(GetInitialData)
            .shouldBeInstanceOf<Error>()
            .message.text shouldBe FakeData.petTypeResponseErrorMessage
    }

    test("OnPetTypeTabSelected should return expected Action") {
        processor.dispatchSideEffect(sideEffect)
            .shouldBeInstanceOf<ForwardPetResponseToState>()
            .petPagingData
            .shouldBeInstanceOf<CommonFlow<PagingData<PetInfo>>>()
    }

    test("LoadPetListNextPage should throw exception if called before OnPetTypeTabSelected") {
        val exception = shouldThrow<RuntimeException> {
            processor.dispatchSideEffect(LoadPetListNextPage)
        }

        exception.message shouldContain "lateinit property petListPager has not been initialized"
    }

    test("LoadPetListNextPage should return expected Action") {
        // This call will initialize the Pager components inside the HomeProcessor
        processor.dispatchSideEffect(sideEffect)

        // Now check with the Actual SideEffect
        processor.dispatchSideEffect(LoadPetListNextPage).shouldBeInstanceOf<IdleAction>()
    }
})
