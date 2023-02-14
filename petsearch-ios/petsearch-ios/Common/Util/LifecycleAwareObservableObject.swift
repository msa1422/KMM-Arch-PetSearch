//
//  LifecycleAwareObservableObject.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 14/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

@MainActor
protocol LifecycleAwareObservableObject : ObservableObject {
    func onAppear()
    func onDisappear()
}
