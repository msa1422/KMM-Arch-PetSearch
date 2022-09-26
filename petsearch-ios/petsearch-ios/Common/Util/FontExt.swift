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
}
