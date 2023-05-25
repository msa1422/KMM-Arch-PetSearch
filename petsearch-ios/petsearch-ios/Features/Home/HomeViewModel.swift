//
//  HomeViewModel.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 25/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import KMPNativeCoroutinesAsync
import Shared

@MainActor
class HomeViewModelDelegate : ObservableObject {
    
    @LazyKoin private var delegate: Shared.HomeViewModel
    
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
            for try await data in asyncSequence(for: delegate.pagingData) {
                paginationState = .idle
                pagingData = data.uniqued()
            }
        }
    }
    
    private func resumePetTypeStream() {
        petTypeStream = Task {
            for try await data in asyncSequence(for: delegate.petTypes) {
                petTypes = data
            }
        }
    }
}
