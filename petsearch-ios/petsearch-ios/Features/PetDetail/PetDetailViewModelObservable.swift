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

@MainActor
class PetDetailViewModelObservable : ObservableObject {
    
    @LazyKoin
    private var delegate: PetDetailViewModel2
    
    @Published
    var petInfo: PetInfo? = nil
    
    init() {
        streamPetInfo()
    }
    
    func streamPetInfo() {
        Task {
            do {
                let stream = asyncStream(for: delegate.petInfoNative)
                for try await data in stream {
                    petInfo = data
                }
            }
        }
    }
}
