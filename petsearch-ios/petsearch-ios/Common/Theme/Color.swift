//
//  Color.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 26/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

public extension SwiftUI.Color {
    static func background(_ colorScheme: ColorScheme) -> Self {
        return SwiftUI.Color(
            colorScheme == .dark
            ? SharedR.colors.shared.background_dark.color.toUIColor()
            : SharedR.colors.shared.background_light.color.toUIColor()
        )
    }
    
    static func surface(_ colorScheme: ColorScheme) -> Self {
        return SwiftUI.Color(
            colorScheme == .dark
            ? SharedR.colors.shared.surface_dark.color.toUIColor()
            : SharedR.colors.shared.surface_light.color.toUIColor()
        )
    }
    
    static func onSurface(_ colorScheme: ColorScheme) -> Self {
        return SwiftUI.Color(
            colorScheme == .dark
            ? SharedR.colors.shared.onSurface_dark.color.toUIColor()
            : SharedR.colors.shared.onSurface_light.color.toUIColor()
        )
    }
}
