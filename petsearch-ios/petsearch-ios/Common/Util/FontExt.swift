//
//  FontExt.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 23/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import UIKit
import Shared

public extension Font {
    init(uiFont: UIFont) {
        self = Font(uiFont as CTFont)
    }
        
    static let displayLarge = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 57))
    static let displayMedium = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 45))
    static let displaySmall = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 36))
    
    static let headlineLarge = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 32))
    static let headlineMedium = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 28))
    static let headlineSmall = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 24))
    
    static let titleLarge = Font(SharedR.fontsInter.shared.black.uiFont(withSize: 22))
    static let titleMedium = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 16))
    static let titleSmall = Font(SharedR.fontsInter.shared.black.uiFont(withSize: 12))
    
    static let labelLarge = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 16))
    static let labelMedium = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 14))
    static let labelSmall = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 11))
    
    static let bodyLarge = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 16))
    static let bodyMedium = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 14))
    static let bodySmall = Font(SharedR.fontsInter.shared.regular.uiFont(withSize: 12))
    
    static let tabSelected = Font(SharedR.fontsInter.shared.black.uiFont(withSize: 16))
    static let tabUnselected = Font(SharedR.fontsInter.shared.semiBold.uiFont(withSize: 14))
}
