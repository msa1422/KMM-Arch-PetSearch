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

    typealias T = HomeViewModel2
    
    var route: String {
        return NavigationScreen.HomeNavScreen.shared.route
    }
    
    var content: some View {
        return HomeScreen()
    }
    
    var viewModel: HomeViewModel2 {
        @LazyKoin var delegate: HomeViewModel2
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
}
