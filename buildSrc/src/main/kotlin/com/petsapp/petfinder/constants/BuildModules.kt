package com.petsapp.petfinder.constants

object AndroidModule {

    const val MODULE = ":android"

    object App {
        const val PACKAGE = "com.petsapp.petfinder"
        const val MODULE = "${AndroidModule.MODULE}:app"
    }

    object Common {

        const val MODULE = "${AndroidModule.MODULE}:common"

        object Compose {
            const val PACKAGE = "com.petsapp.petfinder.commoncompose"
            const val MODULE = "${Common.MODULE}:compose"
        }

        object Resources {
            const val PACKAGE = "com.petsapp.petfinder.commonres"
            const val MODULE = "${Common.MODULE}:resources"
        }

    }

    object Features {

        const val MODULE = "${AndroidModule.MODULE}:features"

        object Splash {
            const val PACKAGE = "com.petsapp.petfinder.splash"
            const val MODULE = "${Features.MODULE}:splash"
        }

        object Home {
            const val PACKAGE = "com.petsapp.petfinder.home"
            const val MODULE = "${Features.MODULE}:home"
        }

        object Search {
            const val PACKAGE = "com.petsapp.petfinder.search"
            const val MODULE = "${Features.MODULE}:search"
        }

        object PetDetail {
            const val PACKAGE = "com.petsapp.petfinder.petdetail"
            const val MODULE = "${Features.MODULE}:pet-detail"
        }

    }

    object Activity {
        const val PACKAGE = "com.petsapp.petfinder.activity"
        const val MODULE = "${AndroidModule.MODULE}:activity"
    }

}

object SharedModule {

    const val PACKAGE = "com.petsapp.petfinder.shared"
    const val MODULE = ":shared"

    object DomainCore {

        const val MODULE = "${SharedModule.MODULE}:domain-core"

        object Util {
            const val PACKAGE = "com.petsapp.petfinder.shared.coreutil"
            const val MODULE = "${DomainCore.MODULE}:util"
        }

        object Entity {
            const val PACKAGE = "com.petsapp.petfinder.shared.coreentity"
            const val MODULE = "${DomainCore.MODULE}:entity"
        }

        object Resources {
            const val PACKAGE = "com.petsapp.petfinder.shared.coreres"
            const val MODULE = "${DomainCore.MODULE}:resources"
        }

    }

    object Domain {

        const val MODULE = "${SharedModule.MODULE}:domain"

        object Splash {

            const val MODULE = "${Domain.MODULE}:splash"

            object DataSource {
                const val PACKAGE = "com.petsapp.petfinder.shared.domain.splashdatasource"
                const val MODULE = "${Splash.MODULE}:data-source"
            }

            object UiContract {
                const val PACKAGE = "com.petsapp.petfinder.shared.domain.splashuicontract"
                const val MODULE = "${Splash.MODULE}:ui-contract"
            }

        }

        object Home {

            const val MODULE = "${Domain.MODULE}:home"

            object DataSource {
                const val PACKAGE = "com.petsapp.petfinder.shared.domain.homedatasource"
                const val MODULE = "${Home.MODULE}:data-source"
            }

            object UiContract {
                const val PACKAGE = "com.petsapp.petfinder.shared.domain.homeuicontract"
                const val MODULE = "${Home.MODULE}:ui-contract"
            }

        }

        object Search {

            const val MODULE = "${Domain.MODULE}:search"

            object DataSource {
                const val PACKAGE = "com.petsapp.petfinder.shared.domain.searchdatasource"
                const val MODULE = "${Search.MODULE}:data-source"
            }

            object UiContract {
                const val PACKAGE = "com.petsapp.petfinder.shared.domain.searchuicontract"
                const val MODULE = "${Search.MODULE}:ui-contract"
            }

        }

        object PetDetail {

            const val MODULE = "${Domain.MODULE}:pet-detail"

            object DataSource {
                const val PACKAGE = "com.petsapp.petfinder.shared.domain.petdetaildatasource"
                const val MODULE = "${PetDetail.MODULE}:data-source"
            }

            object UiContract {
                const val PACKAGE = "com.petsapp.petfinder.shared.domain.petdetailuicontract"
                const val MODULE = "${PetDetail.MODULE}:ui-contract"
            }

        }
        
    }

    object Data {

        const val MODULE = "${SharedModule.MODULE}:data"

        object Repository {

            const val MODULE = "${Data.MODULE}:repository"

            object Splash {
                const val PACKAGE = "com.petsapp.petfinder.shared.repository.splash"
                const val MODULE = "${Repository.MODULE}:splash"
            }

            object Home {
                const val PACKAGE = "com.petsapp.petfinder.shared.repository.home"
                const val MODULE = "${Repository.MODULE}:home"
            }

            object Search {
                const val PACKAGE = "com.petsapp.petfinder.shared.repository.search"
                const val MODULE = "${Repository.MODULE}:search"
            }

            object PetDetail {
                const val PACKAGE = "com.petsapp.petfinder.shared.repository.petdetail"
                const val MODULE = "${Repository.MODULE}:pet-detail"
            }

        }

        object Infrastructure {

            const val MODULE = "${Data.MODULE}:infrastructure"

            object Network {
                const val PACKAGE = "com.petsapp.petfinder.shared.datainfrastructurenetwork"
                const val MODULE = "${Infrastructure.MODULE}:network"
            }

            object Cache {
                const val PACKAGE = "com.petsapp.petfinder.shared.datainfrastructurecache"
                const val MODULE = "${Infrastructure.MODULE}:cache"
            }

            object Preferences {
                const val PACKAGE = "com.petsapp.petfinder.shared.datainfrastructurepreferences"
                const val MODULE = "${Infrastructure.MODULE}:preferences"
            }

        }
        
    }
    
}