//
//  PetDetailScreen.swift
//  ios
//
//  Created by Mohammed Sané on 29/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Shared
import ScalingHeaderScrollView

struct PetDetailScreen: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    let viewModel: PetDetailViewModel
    
    @State private var state: PetDetailState?
    @State private var stateObserver: Closeable? = nil
    
    @State private var scrollProgress: CGFloat = 0

    var body: some View {
        GeometryReader { proxy in
            ScalingHeaderScrollView {
                ZStack(alignment: .topLeading) {
                    Color.surface
                        .edgesIgnoringSafeArea(.all)
                        .zIndex(0)
                    
                    VStack(spacing: .zero) {
                        ImagePager(
                            imageWidth: proxy.size.width,
                            topInsetHeight: proxy.safeAreaInsets.top,
                            images: state?.petInfo?.photos.compactMap({ $0.full }) ?? [String]()
                        ) { image in
                            // OnClick on event for image
                        }
                        .opacity(1 - scrollProgress)
                        
                        Divider()
                            .opacity(1 - scrollProgress)
                        
                        // Pet Name
                        Text(state?.petInfo?.name ?? "")
                            .font(Font.titleMedium)
                            .foregroundColor(Color.onSurface)
                            .lineLimit(1)
                            .padding(.init(top: 16, leading: 24 + 36 * scrollProgress, bottom: .zero, trailing: 24))
                            .frame(maxWidth: .infinity, alignment: .leading)
                        
                        // Pet Description
                        Text(state?.petInfo?.shortDescription.replacingOccurrences(of: "\n", with: ", ") ?? "")
                            .font(Font.bodySmall)
                            .foregroundColor(Color.onSurface)
                            .lineLimit(2)
                            .opacity(0.75)
                            .padding(.init(top: 4, leading: 24 + 36 * scrollProgress, bottom: 20, trailing: 24))
                            .frame(maxWidth: .infinity, alignment: .leading)
                        
                        Divider()
                    }
                    .zIndex(1)
                    
                    // Custom back button
                    Button(action: { presentationMode.wrappedValue.dismiss() }) {
                        Image(systemName: "chevron.left")
                            .frame(width: 60, height: 60)
                            .background(
                                Rectangle()
                                    .foregroundColor(Color.surface.opacity(0.75))
                                    .cornerRadius(18, corners: [.topRight, .bottomRight])
                            )
                    }
                    .zIndex(2)
                    .padding(.init(top: proxy.safeAreaInsets.top + 6 + proxy.size.width * scrollProgress, leading: .zero, bottom: .zero, trailing: .zero))
                }
            } content: {
                VStack {
                    // Pet Description
                    Text(state?.petInfo?.description_ ?? "")
                        .font(Font.bodyMedium)
                        .foregroundColor(Color.onSurface)
                        .lineSpacing(1.4)
                        .padding(.init(top: 20, leading: 24, bottom: .zero, trailing: 24))
                        .frame(maxWidth: .infinity, alignment: .leading)
                    
                    // Chracteristics grid
                    if state?.petInfo?.tags?.isEmpty == false {
                        
                        SectionTitle(title: SharedR.strings().characteristics.desc().localized().uppercased())
                        
                        LazyVGrid(
                            columns: [GridItem(.flexible(), spacing: 2), GridItem(.flexible(), spacing: 2)],
                            spacing: 2
                        ) {
                            ForEach(state?.petInfo?.tags ?? [], id: \.self) { tag in
                                Text("• \(tag)")
                                    .font(Font.bodyMedium)
                                    .foregroundColor(Color.onSurface)
                                    .padding(.init(top: 4, leading: 12, bottom: .zero, trailing: 12))
                                    .frame(maxWidth: .infinity, alignment: .leading)
                            }
                        }
                        .padding(.init(top: .zero, leading: 12, bottom: .zero, trailing: 12))
                    }
                    
                    // Attributes grid
                    if let attrs = state?.petInfo?.attributes {
                        
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
                                        .font(Font.labelSmall)
                                        .foregroundColor(Color.onSurface.opacity(0.62))
                                    
                                    Text(attribute.1)
                                        .font(Font.bodyMedium)
                                        .foregroundColor(Color.onSurface)
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
            .height(
                min: proxy.safeAreaInsets.top + 75,
                max: proxy.safeAreaInsets.top + proxy.size.width + 75
            )
            .collapseProgress($scrollProgress)
            .allowsHeaderGrowth()
            .background(Color.surface)
            .ignoresSafeArea()
            .navigationBarBackButtonHidden(true)
            .navigationBarHidden(true)
            .onAppear {
                if stateObserver == nil { // Should be always true
                    stateObserver = viewModel.observeState().watch { state in
                        guard let ns = state else { return }
                        self.state = ns
                    }
                }
            }
            .onDisappear {
                stateObserver?.close()
                stateObserver = nil
            }
        }
    }
}

struct PetDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        PetDetailScreen(viewModel: PetDetailVmHelper().provide())
    }
}
