//
//  PetDetailScreen.swift
//  ios
//
//  Created by Mohammed Sané on 29/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Shared
import KMMViewModelSwiftUI

struct PetDetailScreen: View {
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @StateViewModel var viewModel: PetDetailViewModel
    
    private let safeAreaInsetTop = UIApplication.shared.windows.first?.safeAreaInsets.top ?? 0
    private let pagerWidth = UIScreen.main.bounds.width
    private let pagerHeight = UIScreen.main.bounds.width + (UIApplication.shared.windows.first?.safeAreaInsets.top ?? 0)
    
    @State private var scrollOffset: CGFloat = 0
    @State private var backButtonOffset: CGFloat = 0

    var body: some View {
        ScrollView(.vertical, showsIndicators: false) {
            ZStack(alignment: .topLeading) {
                
                Color.surface.edgesIgnoringSafeArea(.all)
                
                LazyVStack(alignment: .leading, spacing: 0, pinnedViews: [.sectionHeaders]) {
                    
                    GeometryReader { reader -> AnyView in
                        
                        let offset = reader.frame(in: .global).minY

                        DispatchQueue.main.async {
                            if -offset + safeAreaInsetTop >= 0 {
                                self.scrollOffset = -offset
                            }
                            self.backButtonOffset = -offset
                        }
                        
                        
                        return AnyView(
                            ImagePager(photos: viewModel.petInfo?.photos ?? [PetPhoto]()) { image in
                                // OnClick on event for image
                            }
                            .frame(width: pagerWidth, height: pagerHeight + (offset > 0 ? offset : 0) )
                            .offset(y: (offset > 0 ? -offset : 0))
                        )
                    }
                    .frame(height: pagerHeight)

                    Section(
                        header: PetDetailHeader(
                            petName: viewModel.petInfo?.name ?? "",
                            shortDescription: viewModel.petInfo?.shortDescription
                                .replacingOccurrences(of: "\n", with: ", ") ?? "",
                            pagerHeight: pagerHeight,
                            scrollOffset: $scrollOffset
                        )
                    ) {
                        //
                        // Pet Description ..........................................................
                        Text(viewModel.petInfo?.description_ ?? "")
                            .style(.bodyMedium)
                            .foregroundColor(.onSurface)
                            .lineSpacing(1.4)
                            .padding(.init(top: 20, leading: 24, bottom: .zero, trailing: 24))
                            .frame(maxWidth: .infinity, alignment: .leading)
                        
                        // Chracteristics grid ......................................................
                        if let petTags = viewModel.petInfo?.tags {
                            
                            SectionTitle(title: SharedR.strings().characteristics.desc().localized().uppercased())
                            
                            LazyVGrid(
                                columns: [GridItem(.flexible(), spacing: 2), GridItem(.flexible(), spacing: 2)],
                                spacing: 2
                            ) {
                                ForEach(petTags, id: \.self) { tag in
                                    Text("• \(tag)")
                                        .style(.bodyMedium)
                                        .foregroundColor(.onSurface)
                                        .padding(.init(top: 4, leading: 12, bottom: .zero, trailing: 12))
                                        .frame(maxWidth: .infinity, alignment: .leading)
                                }
                            }
                            .padding(.init(top: .zero, leading: 12, bottom: .zero, trailing: 12))
                        }
                        
                        // Attributes grid ..........................................................
                        if let attrs = viewModel.petInfo?.attributes {
                            
                            SectionTitle(title: SharedR.strings().attributes.desc().localized().uppercased())

                            let yes = SharedR.strings().yes.desc().localized()
                            let no = SharedR.strings().no.desc().localized()

                            let attrNameList = [
                                SharedR.strings().declawed.desc().localized().uppercased(),
                                SharedR.strings().spay_neuter.desc().localized().uppercased(),
                                SharedR.strings().spacial_needs.desc().localized().uppercased(),
                                SharedR.strings().house_trained.desc().localized().uppercased(),
                                SharedR.strings().vaccinated.desc().localized().uppercased(),
                            ]
                            let attrValueList = [
                                attrs.declawed ? yes : no,
                                attrs.spayedNeutered ? yes : no,
                                attrs.specialNeeds ? yes : no,
                                attrs.houseTrained ? yes : no,
                                attrs.shotsCurrent ? yes : no,
                            ]
                            
                            LazyVGrid(
                                columns: [GridItem(.flexible(), spacing: 2), GridItem(.flexible(), spacing: 2)],
                                spacing: 2
                            ) {
                                ForEach(Array(zip(attrNameList, attrValueList)), id: \.0) { attribute in
                                    
                                    VStack (alignment: .leading, spacing: 2) {
                                        
                                        Text(attribute.0)
                                            .bold()
                                            .style(.labelSmall)
                                            .foregroundColor(.onSurface.opacity(0.62))
                                        
                                        Text(attribute.1)
                                            .style(.bodyMedium)
                                            .foregroundColor(.onSurface)
                                            .frame(maxWidth: .infinity, alignment: .leading)
                                    }
                                    .padding(.init(top: 4, leading: 12, bottom: 8, trailing: 12))
                                }
                            }
                            .padding(.init(top: .zero, leading: 12, bottom: .zero, trailing: 12))
                        }
                        
                        Spacer().frame(height: 72)
                    }
                }

                // Custom back button ...............................................................
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
        }
        .overlay(
            Color.surface
                .frame(height: safeAreaInsetTop)
                .ignoresSafeArea(.all, edges: .top)
                .opacity(scrollOffset > pagerHeight ? 1 : 0)
            ,
            alignment: .top
        )
        .background(Color.surface.ignoresSafeArea(edges: .all))
        .navigationBarBackButtonHidden(true)
        .navigationBarHidden(true)
    }
}

struct PetDetailHeader: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    private let petName: String
    private let shortDescription: String
    private let pagerHeight: CGFloat
    @Binding private var scrollOffset: CGFloat
    
    private let safeAreaInsetTop = UIApplication.shared.windows.first?.safeAreaInsets.top ?? 0
    
    fileprivate init(petName: String, shortDescription: String, pagerHeight: CGFloat, scrollOffset: Binding<CGFloat>) {
        self.petName = petName
        self.shortDescription = shortDescription
        self.pagerHeight = pagerHeight
        self._scrollOffset = scrollOffset
    }
    
    var body: some View {
        ZStack(alignment: .leading) {
            VStack(alignment: .leading, spacing: .zero) {
                //
                // Pet Name
                Text(petName)
                    .style(.titleMedium)
                    .foregroundColor(.onSurface)
                    .lineLimit(1)
                    .padding(.init(top: 16, leading: 24, bottom: .zero, trailing: 24))
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .offset(x: 36 * getScrollProgress())
                
                // Pet Description
                Text(shortDescription)
                    .style(.bodySmall)
                    .foregroundColor(.onSurface)
                    .lineLimit(2)
                    .opacity(0.75)
                    .padding(.init(top: 4, leading: 24, bottom: 20, trailing: 24))
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .offset(x: 36 * getScrollProgress())
                
                Divider()
            }
            
            Button(
                action: {
                    if scrollOffset >= pagerHeight {
                        presentationMode.wrappedValue.dismiss()
                    }
                }
            ) {
                Image(systemName: "chevron.left")
                    .frame(width: 60, height: 60)
            }
            .padding(.init(top: 0, leading: 0, bottom: 2, trailing: 0))
            .opacity(scrollOffset < pagerHeight ? 0 : 1)
        }
        .background(Color.surface)
    }
    
    private func getScrollProgress() -> CGFloat {
        if scrollOffset == 0 || pagerHeight == 0 {
            return 0
        }
        
        return min(1, (scrollOffset + safeAreaInsetTop) / pagerHeight)
    }
}

struct PetDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        @LazyKoin var viewModel: PetDetailViewModel
        
        PetDetailScreen(viewModel: viewModel)
    }
}
