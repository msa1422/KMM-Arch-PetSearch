//
//  Koin.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 13/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import KMMViewModelSwiftUI
import Shared

extension KoinApplication {
    static let shared = KoinKt.doInitKoin(eagerModules: nil, lazyModules: nil)
    
    @discardableResult
    static func start() -> KoinApplication { shared }
}

extension KoinApplication {
    private static let keyPaths: [PartialKeyPath<Koin>] = [
        \.HomeViewModel,
        \.PetDetailViewModel
    ]
    
    static func inject<T>() -> T { shared.inject() }
    
    func inject<T>() -> T {
        for partialKeyPath in Self.keyPaths {
            guard let keyPath = partialKeyPath as? KeyPath<Koin, T> else { continue }
            return koin[keyPath: keyPath]
        }
        
        fatalError("\(T.self) is not registered with KoinApplication")
    }
}

@propertyWrapper
struct LazyKoin<T> {
    lazy var wrappedValue: T = { KoinApplication.shared.inject() } ()
    init() { }
}

@propertyWrapper
struct KoinInject<T> {
    var wrappedValue: T = KoinApplication.shared.inject()
    init() { }
}
