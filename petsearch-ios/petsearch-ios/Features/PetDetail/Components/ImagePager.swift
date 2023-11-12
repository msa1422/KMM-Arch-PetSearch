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
    var photos: [PetPhoto]
    var onClick: (_ image: String) -> Void
    
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
        } else {
            placeholder()
        }
    }
}

private extension ImagePager {
    private func image(from photo: PetPhoto) -> some View {
        WebImage(url: URL(string: photo.large))
            .resizable()
            .placeholder {
                if photos.firstIndex(of: photo) == 0 {
                    WebImage(url: URL(string: photo.medium))
                        .resizable()
                        .placeholder(content: placeholder)
                        .cancelOnDisappear(true)
                        .indicator(.activity)
                        .scaledToFill()
                } else {
                    placeholder()
                }
            }
            .cancelOnDisappear(true)
            .indicator(.activity)
            .transition(.fade)
            .scaledToFill()
            .saturation(0.38)
            .onTapGesture { onClick(photo.large) }
    }
    
    private func placeholder() -> some View {
        Image(uiImage: SharedR.images().bg_paw_print_loaded.toUIImage()!)
            .resizable()
            .renderingMode(.original)
            .scaledToFill()
    }
}
