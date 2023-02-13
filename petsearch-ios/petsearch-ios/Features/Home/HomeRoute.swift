//
//  HomeScreenRoute.swift
//  ios
//
//  Created by Mohammed Sané on 03/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

struct HomeRoute: NavRoute {

    typealias T = HomeViewModel
    
    var route: String {
        return NavigationScreen.HomeNavScreen.shared.route
    }
    
    var content: some View {
        return HomeScreen()
    }
    
    var viewModel: HomeViewModel {
        @LazyKoin var delegate: HomeViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
}
