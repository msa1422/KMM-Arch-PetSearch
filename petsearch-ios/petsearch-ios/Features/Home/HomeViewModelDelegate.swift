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

class HomeViewModelDelegate : LifecycleAwareObservableObject {
    
    @LazyKoin private var delegate: HomeViewModel
    @Published var petTypes = [PetType]()
    @Published var pagingData = [PetInfo]()
    @Published var paginationState = PaginationState.loading
    
    private var pagingDataStream: Task<(), Error>? = nil
    private var petTypeStream: Task<(), Error>? = nil
    
    func dispatch(action: HomeAction) {
        delegate.dispatch(action: action)
    }
    
    func onAppear() {
        resumePetTypeStream()
        resumePagingDataStream()
    }
    
    func onDisappear() {
        petTypeStream?.cancel()
        pagingDataStream?.cancel()
    }
    
    private func resumePagingDataStream() {
        pagingDataStream = Task {
            for try await data in asyncStream(for: delegate.pagingDataNative) {
                paginationState = .idle
                pagingData = data.uniqued()
            }
        }
    }
    
    private func resumePetTypeStream() {
        petTypeStream = Task {
            for try await data in asyncStream(for: delegate.petTypesNative) {
                petTypes = data
            }
        }
    }
}
