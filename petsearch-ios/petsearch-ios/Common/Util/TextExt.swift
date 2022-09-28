//
//  TextExt.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 28/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

public extension Text {
    func style(_ style: TextStyle, withSize size: CGFloat = CGFloat.nan) -> some View {
        return self
            .font(Font(style.font.uiFont(withSize: !size.isNaN ? size : style.size)))
            .tracking(style.tracking)
            .lineSpacing(style.lineSpacing)
    }
}
