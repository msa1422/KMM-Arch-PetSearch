//
//  TabRow.swift
//  ios
//
//  Created by Mohammed Sané on 04/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI


struct TabRow: View {
    var tabs: [String]
    @Binding var selectedTab: Int
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            ScrollViewReader { proxy in
                HStack(spacing: CGFloat.zero) {
                    ForEach(0 ..< tabs.count, id: \.self) { row in
                        TabItem(
                            label: tabs[row],
                            isSelected: selectedTab == row
                        ) {
                            withAnimation(.easeInOut) {
                                selectedTab = row
                            }
                        }
                    }
                }
                .padding(.init(top: 0, leading: 12, bottom: 0, trailing: 12))
                .onChange(of: selectedTab) { target in
                    withAnimation(.easeInOut) {
                        // anchor param for centering the selected item in scrollView
                        // However, there is a bug where the last couple of items in scrollView
                        // appear trying to center with janky animation, but bounce back to original position
                        proxy.scrollTo(target, anchor: UnitPoint.center)
                    }
                }
            }
        }
        .frame(height: 55)
    }
}

#Preview {
    TabRow(
        tabs: ["Tab 1", "Tab 2", "Tab 3"],
        selectedTab: .constant(0))
}
