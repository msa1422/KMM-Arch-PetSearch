//
//  iOSApp.swift
//  ios
//
//  Created by Mohammed Sané on 09/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Shared
import SDWebImageSwiftUI
import SDWebImageSVGCoder

@main
struct iOSApp: App {
    
    init() {
        KoinApplication.start()
        SDImageCodersManager.shared.addCoder(SDImageSVGCoder.shared)
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
