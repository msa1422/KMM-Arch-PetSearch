//
//  PetInfoView.swift
//  ios
//
//  Created by Mohammed Sané on 05/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImageSwiftUI

struct PetInfoView: View {
    
    let petInfo: PetInfo
    
    let onClick: () -> Void
    
    var body: some View {
        VStack(alignment: .leading, spacing: .zero) {
            
            // Pet Image
            // Way to get scaleType=CENTER_CROP (Android)
            // Source https://stackoverflow.com/a/63651228
            Color.clear
                .overlay(
                    WebImage(url: URL(string: petInfo.photos.first?.medium ?? ""))
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
                )
                .aspectRatio(0.9, contentMode: .fill)
                .clipped()
            

            
            // Pet Name
            Text(petInfo.name)
                .bold()
                .font(Font.headline)
                .lineLimit(1)
                .padding(.init(top: 10, leading: 12, bottom: .zero, trailing: 12))
                .frame(maxWidth: .infinity, alignment: .leading)
            
            // Pet Description
            Text(petInfo.shortDescription)
                .font(Font.caption)
                .lineLimit(2)
                .opacity(0.75)
                .padding(.init(top: 4, leading: 12, bottom: 20, trailing: 12))
                .frame(maxWidth: .infinity, alignment: .leading)
            
        }
        .frame(maxWidth: .infinity)
        .background(Color.white)
        .onTapGesture { onClick() }
    }
}

struct PetInfoView_Previews: PreviewProvider {
    static var previews: some View {
        PetInfoView(
            petInfo: PetInfo(id: 1, organizationId: "anm", url: "a", type: "Dog", species: "Dog", breeds: PetBreed(primary: "primary", secondary: "secondary", mixed: false, unknown: false), colors: PetColor(primary: "Brown", secondary: "MoreBrown", tertiary: "Little brown"), age: PetAge.adult, gender: PetGender.female, size: PetSize.extraLarge, coat: PetCoat.curly, name: "Barney Stinson", description: "Some is cute", shortDescription: "Very cute", photos: [], videos: [], status: PetStatus.adoptable, attributes: nil, environment: nil, tags: nil, contact: nil, published_at: "Now", distance: 0.0)
        ) {
            
        }
    }
}
