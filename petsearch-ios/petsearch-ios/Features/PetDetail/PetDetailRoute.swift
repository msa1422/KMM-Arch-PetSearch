//
//  PetDetailRoute.swift
//  ios
//
//  Created by Mohammed Sané on 03/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Shared
import SwiftUI

struct PetDetailRoute: NavRoute {
    var destination: NavigationScreen {
        return NavigationScreen.petdetailnavscreen
    }
    
    var content: some View {
        return PetDetailScreen(viewModel: viewModel)
    }
    
    @KoinInject
    var viewModel: PetDetailViewModel
}
