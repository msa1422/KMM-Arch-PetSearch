//
//  PetDetailHeader.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 12/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct PetDetailHeader: View {
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    private let safeAreaInsetTop = UIApplication.shared.windows.first?.safeAreaInsets.top ?? 0
    
    var petName: String
    var shortDescription: String
    var pagerHeight: CGFloat
    @Binding var scrollOffset: CGFloat
    
    var body: some View {
        ZStack(alignment: .leading) {
            VStack(alignment: .leading, spacing: .zero) {
                title
                subtitle
                Divider()
            }
            
            backButton
        }
        .background(Color.surface)
    }
}

// MARK: Views
private extension PetDetailHeader {
    private var backButton: some View {
        Button {
            if scrollOffset >= pagerHeight {
                presentationMode.wrappedValue.dismiss()
            }
        } label: {
            Image(systemName: "chevron.left")
                .frame(width: 60, height: 60)
        }
        .padding(.init(top: 0, leading: 0, bottom: 2, trailing: 0))
        .opacity(scrollOffset < pagerHeight ? 0 : 1)
    }
    
    private var title: some View {
        Text(petName)
            .style(.titleMedium)
            .foregroundColor(.onSurface)
            .lineLimit(1)
            .padding(.init(top: 16, leading: 24, bottom: .zero, trailing: 24))
            .frame(maxWidth: .infinity, alignment: .leading)
            .offset(x: 36 * getScrollProgress())
    }
    
    private var subtitle: some View {
        Text(shortDescription)
            .style(.bodySmall)
            .foregroundColor(.onSurface)
            .lineLimit(2)
            .opacity(0.75)
            .padding(.init(top: 4, leading: 24, bottom: 20, trailing: 24))
            .frame(maxWidth: .infinity, alignment: .leading)
            .offset(x: 36 * getScrollProgress())
    }
}

// MARK: Functions
private extension PetDetailHeader {
    private func getScrollProgress() -> CGFloat {
        return scrollOffset == .zero || pagerHeight == .zero
        ? .zero
        : min(1, (scrollOffset + safeAreaInsetTop) / pagerHeight)
    }
}
