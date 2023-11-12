//
//  PetDetailScreen.swift
//  ios
//
//  Created by Mohammed Sané on 29/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import KMMViewModelSwiftUI
import Shared
import SwiftUI

struct PetDetailScreen: View {
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @StateViewModel var viewModel: PetDetailViewModel
    
    private let safeAreaInsetTop = UIApplication.shared.windows.first?.safeAreaInsets.top ?? .zero
    private let pagerWidth = UIScreen.main.bounds.width
    private let pagerHeight = UIScreen.main.bounds.width + (UIApplication.shared.windows.first?.safeAreaInsets.top ?? .zero)
    
    @State private var scrollOffset: CGFloat = 0
    @State private var backButtonOffset: CGFloat = 0

    var body: some View {
        ScrollView(showsIndicators: false) {
            ZStack(alignment: .topLeading) {
                content
                backButton
            }
        }
        .overlay(safeAreaInsetTopOverlay, alignment: .top)
        .background(Color.surface.ignoresSafeArea(edges: .all))
        .navigationBarBackButtonHidden(true)
        .navigationBarHidden(true)
    }
}

private extension PetDetailScreen {
    private var safeAreaInsetTopOverlay: some View {
        Color.surface
            .frame(height: safeAreaInsetTop)
            .ignoresSafeArea(.all, edges: .top)
            .opacity(scrollOffset > pagerHeight ? 1 : 0)
    }
    
    private var backButton: some View {
        Button(
            action: {
                if scrollOffset < pagerHeight {
                    presentationMode.wrappedValue.dismiss()
                }
            }
        ) {
            Image(systemName: "chevron.left")
                .frame(width: 60, height: 60)
                .background(
                    Rectangle()
                        .foregroundColor(.surface.opacity(0.75))
                        .cornerRadius(18, corners: [.topRight, .bottomRight])
                )
        }
        .padding(.init(top: safeAreaInsetTop + 6, leading: 0, bottom: 0, trailing: 0))
        .offset(y: backButtonOffset)
        .opacity(scrollOffset > pagerHeight ? 0 : 1)
    }
    
    private var content: some View {
        LazyVStack(alignment: .leading, spacing: 0, pinnedViews: [.sectionHeaders]) {
            pager

            Section(
                header: sectionHeader
            ) {
                VStack {
                    description
                    
                    if let tags = viewModel.petInfo?.tags,
                       !tags.isEmpty {
                        SectionTitle(
                            title: SharedR.strings().characteristics.desc().localized().uppercased())
                        
                        characteristicsGrid(tags: tags)
                    }
                    
                    if let attrs = viewModel.petInfo?.attributes {
                        SectionTitle(
                            title: SharedR.strings().attributes.desc().localized().uppercased())

                        attributesGrid(attrs: attrs)
                    }
                    
                    Spacer()
                        .frame(height: 72)
                }
            }
        }
    }
    
    private var pager: some View {
        GeometryReader { proxy in
            let offset = proxy.frame(in: .global).minY
            let _ = DispatchQueue.main.async {
                if -offset + safeAreaInsetTop >= .zero {
                    self.scrollOffset = -offset
                }
                self.backButtonOffset = -offset
            }
            
            ImagePager(photos: viewModel.petInfo?.photos ?? [PetPhoto]()) { image in
                // OnClick on event for image
            }
            .frame(width: pagerWidth, height: pagerHeight + max(offset, .zero))
            .offset(y: (offset > 0 ? -offset : 0))
        }
        .frame(height: pagerHeight)
    }
    
    private var sectionHeader: some View {
        PetDetailHeader(
            petName: viewModel.petInfo?.name ?? "",
            shortDescription: viewModel.petInfo?.shortDescription
                .replacingOccurrences(of: "\n", with: ", ") ?? "",
            pagerHeight: pagerHeight,
            scrollOffset: $scrollOffset
        )
    }
    
    private var description: some View {
        Text(viewModel.petInfo?.description_ ?? "")
            .style(.bodyMedium)
            .foregroundColor(.onSurface)
            .lineSpacing(1.4)
            .padding(.init(top: 20, leading: 24, bottom: .zero, trailing: 24))
            .frame(maxWidth: .infinity, alignment: .leading)
    }
    
    private func characteristicsGrid(tags: [String]) -> some View {
        LazyVGrid(
            columns: [GridItem(.flexible(), spacing: 2), GridItem(.flexible(), spacing: 2)],
            spacing: 2
        ) {
            ForEach(tags, id: \.self) { tag in
                Text("• \(tag)")
                    .style(.bodyMedium)
                    .foregroundColor(.onSurface)
                    .padding(.init(top: 4, leading: 12, bottom: .zero, trailing: 12))
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
        }
        .padding(.init(top: .zero, leading: 12, bottom: .zero, trailing: 12))
    }
    
    private func attributesGrid(attrs: PetAttributes) -> some View {
        VStack(alignment: .leading, spacing: 12) {
            Spacer()
                .frame(height: 0)
            
            HStack {
                attribute(
                    title: \.declawed,
                    value: attrs.declawed)
                
                attribute(
                    title: \.spay_neuter,
                    value: attrs.spayedNeutered)
            }
            
            HStack {
                attribute(
                    title: \.spacial_needs,
                    value: attrs.specialNeeds)
                
                attribute(
                    title: \.house_trained,
                    value: attrs.houseTrained)
            }
            
            attribute(
                title: \.vaccinated,
                value: attrs.shotsCurrent)
        }
        .padding(.horizontal, 12)
    }

    private func attribute(
        title: KeyPath<SharedR.strings, Shared.StringResource>,
        value: Bool
    ) -> some View {
        VStack (alignment: .leading, spacing: 2) {
            Text(SharedR.strings()[keyPath: title].desc().localized().uppercased())
                .bold()
                .style(.labelSmall)
                .foregroundColor(.onSurface.opacity(0.62))
            
            Text(
                value
                ? SharedR.strings().yes.desc().localized()
                : SharedR.strings().no.desc().localized())
            .style(.bodyMedium)
            .foregroundColor(.onSurface)
            .frame(maxWidth: .infinity, alignment: .leading)
        }
        .padding(.horizontal, 12)
        .frame(maxWidth: .infinity, alignment: .leading)
    }
}

#Preview {
    @KoinInject var viewModel: PetDetailViewModel
    return PetDetailScreen(viewModel: viewModel)
}
