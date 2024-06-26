//
//  HomeScreenRoute.swift
//  ios
//
//  Created by Mohammed Sané on 03/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Shared
import SwiftUI

struct HomeRoute: NavRoute {
    var destination: NavigationScreen {
        return NavigationScreen.homeNavScreen
    }
    
    var content: some View {
        return HomeScreen(viewModel: viewModel)
    }
    
    @KoinInject
    var viewModel: HomeViewModel
}
