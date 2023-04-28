//
//  PetDetailViewModelObservable.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 13/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import KMPNativeCoroutinesAsync
import Shared

class PetDetailViewModelDelegate : LifecycleAwareObservableObject {
    
    @LazyKoin private var delegate: PetDetailViewModel
    @Published var petInfo: PetInfo? = nil
    
    private var petInfoStream: Task<(), Error>? = nil
    
    func onAppear() { resumePetInfoStream() }
    
    func onDisappear() { petInfoStream?.cancel() }
    
    private func resumePetInfoStream() {
        petInfoStream = Task {
            for try await data in asyncSequence(for: delegate.petInfo) {
                petInfo = data
            }
        }
    }
}
