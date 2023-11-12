//
//  SharedSvgImage.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 13/10/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Shared
import SDWebImageSwiftUI

struct SharedSvgImage: View {
    
    private let resource: String
    private let renderingMode: Image.TemplateRenderingMode
    
    init(_ resource: String, renderingMode: Image.TemplateRenderingMode = .original) {
        self.resource = resource
        self.renderingMode = renderingMode
    }
    
    var body: WebImage {
        WebImage(url: SharedR.assets.shared.nsBundle
            .url(forResource: resource, withExtension: "svg")
        )
        .resizable()
        .renderingMode(renderingMode)
    }
}
