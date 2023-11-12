//
//  TabItem.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 12/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TabItem: View {
    var label: String
    var isSelected: Bool
    var action: () -> Void
    
    @Namespace var animation: Namespace.ID
    
    var body: some View {
        Button(action: action) {
            VStack(spacing: CGFloat.zero) {
                // Bar Indicator
                if isSelected {
                    indicator
                } else {
                    Spacer()
                        .frame(height: 3)
                }
                
                text
            }
            .frame(alignment: .bottom)
            .fixedSize()
            .padding(.init(top: 0, leading: 12, bottom: 0, trailing: 12))
        }
    }
}

private extension TabItem {
    private var indicator: some View {
        RoundedRectangle(cornerSize: .init(width: 3, height: 3))
            .fill(Color.onSurface)
            .frame(height: 3)
            .matchedGeometryEffect(id: "TAB", in: animation)
    }
    
    private var text: some View {
        Text(label.uppercased())
            .style(isSelected ? .tabSelected : .tabUnselected)
            .foregroundColor(.onSurface)
            .frame(height: 52)
            // for making text visually centered
            .padding(.init(top: 0, leading: 0, bottom: 2, trailing: 0))
            // for avoiding scale animation on text
            .transaction { transaction in
                transaction.animation = nil
            }
    }
}
