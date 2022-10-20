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
    
    @Environment(\.colorScheme) var colorScheme
    
    var tabs: [String]
    
    @Binding var selectedTab: Int
    @Namespace var animation: Namespace.ID
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            ScrollViewReader { proxy in
                HStack(spacing: CGFloat.zero) {
                    ForEach(0 ..< tabs.count, id: \.self) { row in
                        Button(
                            action: {
                                withAnimation(.easeInOut) {
                                    selectedTab = row
                                }
                            },
                            label: {
                                VStack(spacing: CGFloat.zero) {
                                    // Bar Indicator
                                    if selectedTab == row {
                                        RoundedRectangle(cornerSize: CGSize.init(width: 3, height: 3))
                                            .fill(Color.onSurface(colorScheme))
                                            .frame(height: 3)
                                            .matchedGeometryEffect(id: "TAB", in: animation)
                                    }
                                    else {
                                        Spacer().frame(height: 3)
                                    }
                                    
                                    // Text
                                    Text(tabs[row].uppercased())
                                        .style(selectedTab == row ? .tabSelected : .tabUnselected)
                                        .foregroundColor(Color.onSurface(colorScheme))
                                        .frame(width: .none, height: 52)
                                        // for making text visually centered
                                        .padding(.init(top: 0, leading: 0, bottom: 2, trailing: 0))
                                        // for avoiding scale animation on text
                                        .transaction { transaction in
                                            transaction.animation = nil
                                        }
                                }
                                .frame(alignment: .bottom)
                                .fixedSize()
                                .padding(.init(top: 0, leading: 12, bottom: 0, trailing: 12))
                            }
                        )
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

struct Tabs_Previews: PreviewProvider {
    static var previews: some View {
        TabRow(tabs: ["Tab 1", "Tab 2", "Tab 3"], selectedTab: .constant(0))
    }
}
