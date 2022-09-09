//
//  BindingExt.swift
//  ios
//
//  Created by Mohammed Sané on 05/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI


// Source
// https://www.hackingwithswift.com/quick-start/swiftui/how-to-run-some-code-when-state-changes-using-onchange

extension Binding {
    func onChange(_ handler: @escaping (Value) -> Void) -> Binding<Value> {
        Binding(
            get: { self.wrappedValue },
            set: { newValue in
                self.wrappedValue = newValue
                handler(newValue)
            }
        )
    }
}
