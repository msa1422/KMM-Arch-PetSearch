//
//  SectionTitle.swift
//  ios
//
//  Created by Mohammed Sané on 07/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SectionTitle: View {
    var title: String
    
    var body: some View {
        HStack(alignment: .center) {
            Text(title)
                .style(.titleSmall)
                .foregroundColor(.onSurface)
                .opacity(0.62)
                .padding(.init(top: 8, leading: 24, bottom: 8, trailing: 24))
            
            Spacer()
        }
        .background(Color.background)
        .padding(.init(top: 24, leading: .zero, bottom: 4, trailing: .zero))
    }
}
