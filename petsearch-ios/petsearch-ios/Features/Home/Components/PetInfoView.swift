//
//  PetInfoView.swift
//  ios
//
//  Created by Mohammed Sané on 05/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SDWebImageSwiftUI
import Shared
import SwiftUI

struct PetInfoView: View {
    var petInfo: PetInfo
    var onClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: .zero) {
            image
            name
            description
        }
        .frame(maxWidth: .infinity)
        .background(Color.surface)
        .onTapGesture(perform: onClick)
    }
}

private extension PetInfoView {
    private var image: some View {
        // Way to get scaleType=CENTER_CROP (Android) "bg_paw_print_loaded"
        // Source https://stackoverflow.com/a/63651228
        Color.clear
            .overlay(
                WebImage(url: URL(string: petInfo.photos.first?.medium ?? ""))
                    .resizable()
                    .placeholder {
                        Image(uiImage: SharedR.images().bg_paw_print_loaded.toUIImage()!)
                            .resizable()
                            .renderingMode(.original)
                            .scaledToFill()
                    }
                    .indicator(.activity)
                    .transition(.fade)
                    .scaledToFill()
                    .saturation(0.38)
                    .clipped()
            )
            .aspectRatio(0.9, contentMode: .fill)
            .clipped()
    }
    
    private var name: some View {
        Text(petInfo.name)
            .style(.titleMedium)
            .foregroundColor(.onSurface)
            .lineLimit(1)
            .padding(.init(top: 10, leading: 12, bottom: .zero, trailing: 12))
            .frame(maxWidth: .infinity, alignment: .leading)
    }
    
    private var description: some View {
        Text(petInfo.shortDescription)
            .style(.bodySmall)
            .foregroundColor(.onSurface)
            .lineLimit(2)
            .opacity(0.8)
            .padding(.init(top: 4, leading: 12, bottom: 20, trailing: 12))
            .frame(maxWidth: .infinity, alignment: .leading)
    }
}

#Preview {
    PetInfoView(
        petInfo: PetInfo(id: 1, organizationId: "anm", url: "a", type: "Dog", species: "Dog", breeds: PetBreed(primary: "primary", secondary: "secondary", mixed: false, unknown: false), colors: PetColor(primary: "Brown", secondary: "MoreBrown", tertiary: "Little brown"), age: PetAge.adult, gender: PetGender.female, size: PetSize.extraLarge, coat: PetCoat.curly, name: "Barney Stinson", description: "Some is cute", shortDescription: "Very cute", photos: [], videos: [], status: PetStatus.adoptable, attributes: nil, environment: nil, tags: nil, contact: nil, publishedAt: "Now", distance: 0.0),
        onClick: {}
    )
}
