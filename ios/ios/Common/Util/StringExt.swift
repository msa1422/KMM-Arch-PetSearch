//
//  StringExt.swift
//  ios
//
//  Created by Mohammed Sané on 03/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation

// Source https://stackoverflow.com/a/52226561
public extension String {
    func valueOf(param queryParam: String) -> String? {
        guard let url = URLComponents(string: self) else { return nil }
        if let parameters = url.queryItems {
            return parameters.first(where: { $0.name == queryParam })?.value
        } else if let paramPairs = url.fragment?.components(separatedBy: "?").last?.components(separatedBy: "&") {
            for pair in paramPairs where pair.contains(queryParam) {
                return pair.components(separatedBy: "=").last
            }
            return nil
        } else {
            return nil
        }
    }
}


extension String {
    mutating func replace(_ originalString:String, with newString:String) {
        self = self.replacingOccurrences(of: originalString, with: newString)
    }
}


extension String {
    public var queryParameters: [String: String]? {
        guard
            let components = URLComponents(string: self),
            let queryItems = components.queryItems else { return nil }
        return queryItems.reduce(into: [String: String]()) { (result, item) in
            result[item.name] = item.value
        }
    }
}

// Source https://stackoverflow.com/a/44806984
//extension String {
//    func valueOf(param queryParam: String) -> String? {
//        guard let url = URLComponents(string: self) else { return nil }
//        return url.queryItems?.first(where: { $0.name == queryParam })?.value
//    }
//}
