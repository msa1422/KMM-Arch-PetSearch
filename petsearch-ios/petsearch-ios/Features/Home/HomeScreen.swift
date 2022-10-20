//
//  HomeScreen.swift
//  ios
//
//  Created by Mohammed Sané on 29/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Shared
import SDWebImageSwiftUI

struct HomeScreen: View {
    @Environment(\.colorScheme) var colorScheme
    
    let viewModel : HomeViewModel
        
    @State private var renderState: HomeRenderState? = nil
    @State private var renderStateObserver: Closeable? = nil
    
    @State private var petInfoList: [PetInfo] = []
    @State private var petInfoListObserver: Closeable? = nil
    
    @State private var paginationState = PaginationState.loading
    
    @State private var selectedTab: Int = 0
    
    var body: some View {
        VStack(alignment: .leading, spacing: .zero) {
            locationButton
            
            tabRow
            
            Divider()

            gridView
            
            // To push the TabRow to top when there are no items in GridView
            Spacer()
        }
        .navigationTitle("")
        .navigationBarHidden(true)
        .edgesIgnoringSafeArea(.bottom)
        .background(Color.surface(colorScheme).ignoresSafeArea())
        .onAppear {
            if renderStateObserver == nil {
                renderStateObserver = viewModel.observeRenderState().watch { state in
                    guard let renderState = state else { return }
                    self.renderState = renderState
                    
                    // Initialize the PetListInfoObserver inside the RenderStateObserver
                    // Because, if PetListInfoObserver is initialized outside RenderstateObserver,
                    // in OnAppear, the RenderState will be nil and
                    // hence PetListInfoObserver will not be initialized
                    if petInfoListObserver == nil {
                        self.renderState?.petPagingData.watch { infoList in
                            
                            // This condition checks any unwanted updates to LazyGrid
                            if (infoList?.firstObject as? PetInfo)?.type
                                == self.renderState?.petTypes?[selectedTab].name {
                                
                                guard let list = infoList?.compactMap({ $0 as? PetInfo }) else { return }
                                petInfoList = list.uniqued()
                                // .uniqued() because API is returning data with dulicate items,
                                // causing an exception in LazyVGrid
                                
                                paginationState = .idle
                            }
                        }
                    }
                }
            }
            
            if renderState == nil || renderState?.petTypes == nil {
                viewModel.action(action: HomeAction.GetPetTypes())
            }
        }
        .onDisappear {
            // If there is a cleaner way to handle the CommonFlow, you're welcome.
            renderStateObserver?.close()
            renderStateObserver = nil
            
            petInfoListObserver?.close()
            petInfoListObserver = nil
        }
    }
}

extension HomeScreen {
    
    private var locationButton: some View {
        Button(
            action: {
                
            }
        ) {
            VStack(alignment: .leading, spacing: .zero) {
                HStack(alignment: .center, spacing: .zero) {
                    SharedSvgImage("near_me", renderingMode: .template)
                        .frame(width: 24, height: 24)
                    
                    Text("New York City")
                        .style(.titleMedium)
                        .padding(.init(top: .zero, leading: 6, bottom: .zero, trailing:.zero))
                    
                    SharedSvgImage("arrow_drop_down", renderingMode: .template)
                        .frame(width: 24, height: 24)
                }
                Text("20 W 34th St., New York, United States")
                    .style(.bodySmall)
                    .padding(.init(top: 4, leading: 4, bottom: .zero, trailing: .zero))
            }
            .padding(.init(top: 4, leading: 20, bottom: 18, trailing: 24))
        }
    }
    
    private var tabRow: some View {
        TabRow(
            tabs: renderState?.petTypes?.compactMap({ $0.name }) ?? [String](),
            selectedTab: $selectedTab.onChange { index in
                // First check if the index is same as already selectedTab or not
                if petInfoList.first?.type == renderState?.petTypes?[selectedTab].name {
                    return
                }
                                            
                paginationState = .loading

                // remove all items from the LazyGrid
                petInfoList.removeAll()
                
                viewModel.action(
                    action: HomeAction.OnPetTypeTabSelected(
                        tabName: renderState?.petTypes?[index].name ?? ""
                    )
                )
            }
        )
    }
    
    private var gridView: some View {
        ScrollView(.vertical, showsIndicators: false) {
            LazyVGrid(
                columns: [GridItem(.flexible(), spacing: 2), GridItem(.flexible(), spacing: 2)],
                spacing: 2
            ) {
                ForEach(petInfoList, id: \.id) { petInfo in
                    PetInfoView(petInfo: petInfo) {
                        viewModel
                            .action(action: HomeAction.NavigateToPetDetail(petInfo: petInfo))
                    }
                    .onAppear {
                        // Very basic and definitely not production ready implementation of pagination
                        // Proper implementation and refinment is required.
                        // Will implement it soon as I keep learning SwiftUI
                        let thresholdIndex = petInfoList.index(petInfoList.endIndex, offsetBy: -5)
                        
                        if petInfoList.firstIndex(where: { $0.id == petInfo.id }) == thresholdIndex &&
                            paginationState != .loading {
                            paginationState = .loading
                            viewModel.action(action: HomeAction.LoadPetListNextPage())
                        }
                    }
                }
            }
            
            ProgressView()
                .padding(.init(top: 32, leading: .zero, bottom: 64, trailing: .zero))
                .opacity(paginationState == .loading ? 1 : 0)
        }
        .background(Color.background(colorScheme))
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen(viewModel: HomeVmHelper().provide())
    }
}
