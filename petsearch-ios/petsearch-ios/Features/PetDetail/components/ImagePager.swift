//
//  ImagePager.swift
//  ios
//
//  Created by Mohammed Sané on 07/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Shared
import SDWebImageSwiftUI

struct ImagePager: View {
    
    private let photos: [PetPhoto]
    private let onClick: (_ image: String) -> Void
    
    init(photos: [PetPhoto], onClick: @escaping (_: String) -> Void) {
        self.photos = photos
        self.onClick = onClick
    }
    
    var body: some View {
        if !photos.isEmpty {
            TabView {
                ForEach(photos, id: \.self) { photo in
                    Color.clear
                        .overlay(image(from: photo))
                        .clipped()
                }
            }
            .tabViewStyle(PageTabViewStyle())
        }
    }
    
    func image(from photo: PetPhoto) -> some View {
        WebImage(url: URL(string: photo.large))
            .resizable()
            .placeholder {
                if photos.firstIndex(of: photo) == 0 {
                    WebImage(url: URL(string: photo.medium))
                        .resizable()
                        .placeholder {
                            placeholder
                                .scaledToFill()
                        }
                        .cancelOnDisappear(true)
                        .indicator(.activity)
                        .scaledToFill()
                } else {
                    placeholder
                        .scaledToFill()
                }
            }
            .cancelOnDisappear(true)
            .indicator(.activity)
            .transition(.fade)
            .scaledToFill()
            .saturation(0.38)
            .onTapGesture { onClick(photo.large) }
    }
    
    var placeholder: Image {
        Image(uiImage: SharedR.images().bg_paw_print_loaded.toUIImage()!)
            .resizable()
            .renderingMode(.template)
    }
}
