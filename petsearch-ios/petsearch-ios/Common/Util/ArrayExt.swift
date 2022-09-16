//
//  ArrayExt.swift
//  ios
//
//  Created by Mohammed Sané on 13/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

// Source
// https://stackoverflow.com/a/46354989

public extension Array where Element: Hashable {
    func uniqued() -> [Element] {
        var seen = Set<Element>()
        return filter{ seen.insert($0).inserted }
    }
}
