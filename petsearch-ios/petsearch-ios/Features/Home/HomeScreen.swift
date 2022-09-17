//
//  HomeScreen.swift
//  ios
//
//  Created by Mohammed Sané on 29/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct HomeScreen: View {
    let viewModel : HomeViewModel
        
    @State private var renderState: HomeRenderState? = nil
    @State private var renderStateObserver: Closeable? = nil
    
    @State private var petInfoList: [PetInfo] = []
    @State private var petInfoListObserver: Closeable? = nil
    
    @State private var paginationState: PaginationState = .loading
    
    @State private var selectedTab: Int = 0
    
    var body: some View {
        VStack(alignment: .leading, spacing: .zero) {
            // Location Selector button
            Button(
                action: {
                    
                }
            ) {
                VStack(alignment: .leading, spacing: .zero) {
                    HStack(alignment: .center, spacing: .zero) {
                        Image("near_me")
                        Text("New York City")
                            .bold()
                            .font(Font.headline)
                            .padding(.init(top: .zero, leading: 6, bottom: .zero, trailing:.zero))
                        Image("arrow_drop_down")
                    }
                    Text("20 W 34th St., New York, United States")
                        .font(Font.caption)
                        .padding(.init(top: 4, leading: 4, bottom: .zero, trailing: .zero))
                }
                .padding(.init(top: 4, leading: 20, bottom: 18, trailing: 24))
            }
            
            // Material style TabLayout
            TabRow(
                tabs: renderState?.petTypes?.compactMap({ $0.name }) ?? [String](),
                selectedTab: $selectedTab.onChange { index in
                    Task {
                        do {
                            // First check if the index is same as already selectedTab or not
                            if petInfoList.first?.type == renderState?.petTypes?[selectedTab].name {
                                return
                            }
                                                        
                            paginationState = .loading

                            // remove all items from the LazyGrid
                            petInfoList.removeAll()
                            
                            try await viewModel.action(
                                action: HomeAction.OnPetTypeTabSelected(
                                    tabName: renderState?.petTypes?[index].name ?? ""
                                )
                            )
                        }
                        catch { print(error) }
                    }
                }
            )
            
            Divider()

            // GridView
            ScrollView(.vertical, showsIndicators: false) {
                LazyVGrid(
                    columns: [GridItem(.flexible(), spacing: 2), GridItem(.flexible(), spacing: 2)],
                    spacing: 2
                ) {
                    ForEach(petInfoList, id: \.id) { petInfo in
                        PetInfoView(petInfo: petInfo) {
                            Task {
                                do {
                                    try await viewModel.action(
                                        action: HomeAction.NavigateToPetDetail(petInfo: petInfo)
                                    )
                                }
                                catch { print(error) }
                            }
                        }
                        .onAppear {
                            // Very basic and definitely not production ready implementation of pagination
                            // Proper implementation and refinment is required.
                            // Will implement it soon as I keep learning SwiftUI
                            let thresholdIndex = petInfoList.index(petInfoList.endIndex, offsetBy: -5)
                            
                            if petInfoList.firstIndex(where: { $0.id == petInfo.id }) == thresholdIndex &&
                                paginationState != .loading {
                                Task {
                                    do {
                                        paginationState = .loading
                                        
                                        try await viewModel.action(
                                            action: HomeAction.LoadPetListNextPage()
                                        )
                                    }
                                    catch { print(error) }
                                }
                            }
                        }
                    }
                }
                
                ProgressView()
                    .padding(.init(top: 32, leading: .zero, bottom: 64, trailing: .zero))
                    .opacity(paginationState == .loading ? 1 : 0)
            }
            .background(Color.black.opacity(0.1))
            
            // To push the TabRow to top when there are no items in GridView
            Spacer()
        }
        .navigationTitle("")
        .navigationBarHidden(true)
        .edgesIgnoringSafeArea(.bottom)
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
                Task {
                    do { try await viewModel.action(action: HomeAction.GetPetTypes()) }
                    catch { print(error) }
                }
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

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen(viewModel: HomeVmHelper().provide())
    }
}
