//
//  PetDetailScreen.swift
//  ios
//
//  Created by Mohammed Sané on 29/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct PetDetailScreen: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    let viewModel: PetDetailViewModel
    
    @State private var state: PetDetailState?
    @State private var stateObserver: Closeable? = nil
    
    var body: some View {
        GeometryReader { proxy in
            
            ZStack(alignment: .topLeading) {
                
                ScrollView(.vertical, showsIndicators: false) {
                    
                    VStack(spacing: .zero) {
                        
                        ImagePager(
                            imageWidth: proxy.size.width,
                            topInsetHeight: proxy.safeAreaInsets.top,
                            images: state?.petInfo?.photos.compactMap({ $0.full }) ?? [String]()
                        ) { image in
                            // OnClick on event for image
                        }
                        
                        Divider()
                        
                        // Pet Name
                        Text(state?.petInfo?.name ?? "")
                            .bold()
                            .font(Font.headline)
                            .lineLimit(1)
                            .padding(.init(top: 20, leading: 24, bottom: .zero, trailing: 24))
                            .frame(maxWidth: .infinity, alignment: .leading)
                        
                        // Pet Description
                        Text(state?.petInfo?.shortDescription.replacingOccurrences(of: "\n", with: ", ") ?? "")
                            .font(Font.caption)
                            .lineLimit(2)
                            .opacity(0.75)
                            .padding(.init(top: 4, leading: 24, bottom: 24, trailing: 24))
                            .frame(maxWidth: .infinity, alignment: .leading)
                        
                        Divider()
                        
                        // Pet Description
                        Text(state?.petInfo?.description_ ?? "")
                            .font(Font.body)
                            .lineSpacing(1.4)
                            .padding(.init(top: 20, leading: 24, bottom: .zero, trailing: 24))
                            .frame(maxWidth: .infinity, alignment: .leading)
                        
                        
                        // Chracteristics grid
                        if state?.petInfo?.tags?.isEmpty == false {
                            
                            SectionTitle(title: CoreR.strings().characteristics.desc().localized().uppercased())
                            
                            LazyVGrid(
                                columns: [GridItem(.flexible(), spacing: 2), GridItem(.flexible(), spacing: 2)],
                                spacing: 2
                            ) {
                                ForEach(state?.petInfo?.tags ?? [], id: \.self) { tag in
                                    Text("• \(tag)")
                                        .font(Font.body)
                                        .padding(.init(top: 4, leading: 12, bottom: .zero, trailing: 12))
                                        .frame(maxWidth: .infinity, alignment: .leading)
                                }
                            }
                            .padding(.init(top: .zero, leading: 12, bottom: .zero, trailing: 12))
                        }
                        

                        // Attributes grid
                        if let attrs = state?.petInfo?.attributes {
                            
                            SectionTitle(title: CoreR.strings().attributes.desc().localized().uppercased())

                            let yes = CoreR.strings().yes.desc().localized()
                            let no = CoreR.strings().no.desc().localized()

                            // Create a map so that it can be used in LazyGrid
                            let attrMap = [
                                CoreR.strings().declawed.desc().localized().uppercased():  attrs.declawed ? yes : no,
                                CoreR.strings().spay_neuter.desc().localized().uppercased():  attrs.spayedNeutered ? yes : no,
                                CoreR.strings().spacial_needs.desc().localized().uppercased():  attrs.specialNeeds ? yes : no,
                                CoreR.strings().house_trained.desc().localized().uppercased():  attrs.houseTrained ? yes : no,
                                CoreR.strings().vaccinated.desc().localized().uppercased():  attrs.shotsCurrent ? yes : no
                            ]
                            
                            LazyVGrid(
                                columns: [GridItem(.flexible(), spacing: 2), GridItem(.flexible(), spacing: 2)],
                                spacing: 2
                            ) {
                                ForEach(attrMap.keys.compactMap({ $0 }), id: \.self) { attribute in
                                    
                                    VStack (alignment: .leading, spacing: 2) {
                                        
                                        Text(attribute)
                                            .bold()
                                            .font(Font.caption)
                                            .foregroundColor(Color.black.opacity(0.62))
                                        
                                        Text(attrMap[attribute] ?? no)
                                            .font(Font.body)
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

                
                // Custom back button
                Button(action: { presentationMode.wrappedValue.dismiss() }) {
                    Image(systemName: "chevron.left")
                        //.padding(.init(top: 4, leading: .zero, bottom: 4, trailing: 4))
                        .frame(width: 60, height: 60)
                        .background(
                            Rectangle()
                                .foregroundColor(Color.white.opacity(0.62))
                                .cornerRadius(18, corners: [.topRight, .bottomRight])
                        )
                }
                .padding(.init(top: proxy.safeAreaInsets.top, leading: .zero, bottom: .zero, trailing: .zero))
            
                
            }
            .background(Color.white)
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
