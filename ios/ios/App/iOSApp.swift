//
//  iOSApp.swift
//  ios
//
//  Created by Mohammed Sané on 09/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        KoinKt.doInitKoin(modules: nil)
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

