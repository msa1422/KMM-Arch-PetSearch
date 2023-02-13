//
//  PetDetailRoute.swift
//  ios
//
//  Created by Mohammed Sané on 03/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

struct PetDetailRoute: NavRoute {

    typealias T = PetDetailViewModel2
    
    var route: String {
        return NavigationScreen.PetDetailNavScreen.shared.route
    }
    
    var content: some View {
        return PetDetailScreen()
    }
    
    var viewModel: PetDetailViewModel2 {
        @LazyKoin var delegate: PetDetailViewModel2
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [NavigationScreenKt.ARG_PET_INFO]
    }
}
