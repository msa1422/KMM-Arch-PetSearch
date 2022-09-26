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
    static let background = SwiftUI.Color(SharedR.colors.shared.background.color.toUIColor())
    static let surface = SwiftUI.Color(SharedR.colors.shared.surface.color.toUIColor())
    static let onSurface = SwiftUI.Color(SharedR.colors.shared.onSurface.color.toUIColor())
}
