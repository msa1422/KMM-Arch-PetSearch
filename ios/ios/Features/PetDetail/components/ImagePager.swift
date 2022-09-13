//
//  ImagePager.swift
//  ios
//
//  Created by Mohammed Sané on 07/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import SDWebImageSwiftUI

struct ImagePager: View {
    
    let imageWidth: CGFloat
    let topInsetHeight: CGFloat
    var images: [String]
    var onClick: (_ image: String) -> Void
    
    var body: some View {
        TabView {
            ForEach(images, id: \.self) { imgURL in
                Color.clear
                    .overlay(
                        WebImage(url: URL(string: imgURL))
                            .resizable()
                            .placeholder {
                                Image("pet_image_placeholder")
                                    .resizable()
                                    .scaledToFill()
                            }
                            .indicator(.activity)
                            .transition(.fade)
                            .scaledToFill()
                            .saturation(0.38)
                            .onTapGesture { onClick(imgURL) }
                    )
                    .clipped()
            }
        }
        .frame(width: imageWidth, height: imageWidth + topInsetHeight)
        .tabViewStyle(PageTabViewStyle())
    }
    
}
