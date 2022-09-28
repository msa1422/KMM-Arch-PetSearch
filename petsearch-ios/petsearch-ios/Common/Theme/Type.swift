//
//  Type.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 26/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

//public extension Font {
//
//    static let displayLarge = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 57))
//    static let displayMedium = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 45))
//    static let displaySmall = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 36))
//
//    static let headlineLarge = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 32))
//    static let headlineMedium = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 28))
//    static let headlineSmall = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 24))
//
//    static let titleLarge = Font(SharedR.fontsInter.shared.black.uiFont(withSize: 22))
//    static let titleMedium = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 16))
//    static let titleSmall = Font(SharedR.fontsInter.shared.black.uiFont(withSize: 12))
//
//    static let labelLarge = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 16))
//    static let labelMedium = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 14))
//    static let labelSmall = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 11))
//
//    static let bodyLarge = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 16))
//    static let bodyMedium = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 14))
//    static let bodySmall = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 12))
//
//    static let tabSelected = Font(SharedR.fontsInter.shared.black.uiFont(withSize: 16))
//    static let tabUnselected = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 14))
//}

public struct TextStyle {
    let font: FontResource
    let tracking: CGFloat
    let lineSpacing: CGFloat
    let size: CGFloat
    
    fileprivate init(_ font: FontResource, _ tracking: CGFloat, _ lineSpacing: CGFloat, _ size: CGFloat) {
        self.font = font
        self.tracking = tracking
        self.lineSpacing = lineSpacing
        self.size = size
    }
}

public extension TextStyle {
    
    static let displayLarge = TextStyle(SharedR.fontsInter.shared.regular, -2.5, 0, 57)
    static let displayMedium = TextStyle(SharedR.fontsInter.shared.regular, -2, 0, 45)
    static let displaySmall = TextStyle(SharedR.fontsInter.shared.regular, -1.5, 0, 36)
    
    static let headlineLarge = TextStyle(SharedR.fontsInter.shared.regular, -1, 0, 32)
    static let headlineMedium = TextStyle(SharedR.fontsInter.shared.regular, -0.5, 0, 28)
    static let headlineSmall = TextStyle(SharedR.fontsInter.shared.regular, -0.75, 0, 24)
    
    static let titleLarge = TextStyle(SharedR.fontsInter.shared.black, -0.1, 0, 22)
    static let titleMedium = TextStyle(SharedR.fontsInter.shared.semiBold, 0, 0, 16)
    static let titleSmall = TextStyle(SharedR.fontsInter.shared.black, 0.1, 0, 14)
    
    static let labelLarge = TextStyle(SharedR.fontsInter.shared.semiBold, 0.1, 0, 16)
    static let labelMedium = TextStyle(SharedR.fontsInter.shared.semiBold, 0.5, 0, 14)
    static let labelSmall = TextStyle(SharedR.fontsInter.shared.semiBold, 0.5, 0, 11)
    
    static let bodyLarge = TextStyle(SharedR.fontsInter.shared.semiBold, 0.5, 4, 16)
    static let bodyMedium = TextStyle(SharedR.fontsInter.shared.regular, 0.25, 3, 14)
    static let bodySmall = TextStyle(SharedR.fontsInter.shared.regular, 0.4, 2, 12)
    
    static let tabSelected = TextStyle(SharedR.fontsInter.shared.black, 0, 0, 16)
    static let tabUnselected = TextStyle(SharedR.fontsInter.shared.semiBold, 0.5, 0, 14)
}
