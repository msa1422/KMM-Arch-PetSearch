//
//  SectionTitle.swift
//  ios
//
//  Created by Mohammed Sané on 07/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SectionTitle: View {
    
    let title: String
    
    var body: some View {
        HStack(alignment: .center) {
            Text(title)
                .font(Font.titleSmall)
                .tracking(0.07)
                .opacity(0.62)
                .padding(.init(top: 8, leading: 24, bottom: 8, trailing: 24))
            
            Spacer()
        }
        .background(Color.black.opacity(0.05))
        .padding(.init(top: 24, leading: .zero, bottom: 4, trailing: .zero))
    }
}
