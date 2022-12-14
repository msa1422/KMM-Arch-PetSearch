//
//  PetInfoView.swift
//  ios
//
//  Created by Mohammed Sané on 05/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Shared
import SDWebImageSwiftUI

struct PetInfoView: View {
    
    @Environment(\.colorScheme) var colorScheme
    
    let petInfo: PetInfo
    
    let onClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: .zero) {
            // Pet Image
            // Way to get scaleType=CENTER_CROP (Android) "bg_paw_print_loaded"
            // Source https://stackoverflow.com/a/63651228
            Color.clear
                .overlay(
                    WebImage(url: URL(string: petInfo.photos.first?.medium ?? ""))
                        .resizable()
                        .placeholder {
                            SharedSvgImage("bg_paw_print_loaded")
                                .scaledToFill()
                        }
                        .indicator(.activity)
                        .transition(.fade)
                        .scaledToFill()
                        .saturation(0.38)
                )
                .aspectRatio(0.9, contentMode: .fill)
                .clipped()
            
            // Pet Name
            Text(petInfo.name)
                .style(.titleMedium)
                .foregroundColor(Color.onSurface(colorScheme))
                .lineLimit(1)
                .padding(.init(top: 10, leading: 12, bottom: .zero, trailing: 12))
                .frame(maxWidth: .infinity, alignment: .leading)
            
            // Pet Description
            Text(petInfo.shortDescription)
                .style(.bodySmall)
                .foregroundColor(Color.onSurface(colorScheme))
                .lineLimit(2)
                .opacity(0.75)
                .padding(.init(top: 4, leading: 12, bottom: 20, trailing: 12))
                .frame(maxWidth: .infinity, alignment: .leading)
        }
        .frame(maxWidth: .infinity)
        .background(Color.surface(colorScheme))
        .onTapGesture { onClick() }
    }
}

struct PetInfoView_Previews: PreviewProvider {
    static var previews: some View {
        PetInfoView(
            petInfo: PetInfo(id: 1, organizationId: "anm", url: "a", type: "Dog", species: "Dog", breeds: PetBreed(primary: "primary", secondary: "secondary", mixed: false, unknown: false), colors: PetColor(primary: "Brown", secondary: "MoreBrown", tertiary: "Little brown"), age: PetAge.adult, gender: PetGender.female, size: PetSize.extraLarge, coat: PetCoat.curly, name: "Barney Stinson", description: "Some is cute", shortDescription: "Very cute", photos: [], videos: [], status: PetStatus.adoptable, attributes: nil, environment: nil, tags: nil, contact: nil, publishedAt: "Now", distance: 0.0)
        ) {
            
        }
    }
}
