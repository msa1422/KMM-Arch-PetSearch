//
//  HomeViewModelObservable.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 13/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import KMPNativeCoroutinesAsync
import Shared

@MainActor
class HomeViewModelDelegate : ObservableObject {
    
    @LazyKoin
    private var delegate: HomeViewModel
        
    @Published
    var petTypes = [PetType]()
    
    @Published
    var pagingData = [PetInfo]()
    
    init() {
        streamPetTypes()
        streamPagingData()
    }
    
    func dispatch(action: HomeAction) {
        delegate.dispatch(action: action)
    }
    
    func streamPagingData() {
        Task {
            do {
                let stream = asyncStream(for: delegate.pagingDataNative)
                for try await data in stream {
                    pagingData = data
                }
            }
        }
    }
    
    func streamPetTypes() {
        Task {
            do {
                let stream = asyncStream(for: delegate.petTypesNative)
                for try await data in stream {
                    petTypes = data
                }
            }
        }
    }
}
