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
    
    @StateObject var viewModel = HomeViewModelDelegate()
    
    @State private var selectedTab: Int = 0
    
    var body: some View {
        VStack(alignment: .leading, spacing: .zero) {
            locationButton
            
            tabRow
            
            Divider()
            
            gridView
            
            Spacer()
        }
        .navigationTitle("")
        .navigationBarHidden(true)
        .edgesIgnoringSafeArea(.bottom)
        .background(Color.surface.ignoresSafeArea())
        .onAppear { viewModel.onAppear() }
        .onDisappear { viewModel.onDisappear() }
    }
}

extension HomeScreen {
    
    private var locationButton: some View {
        Button(action: {}) {
            VStack(alignment: .leading, spacing: .zero) {
                HStack(alignment: .center, spacing: .zero) {
                    Image(uiImage: SharedR.images().near_me.toUIImage()!)
                        .resizable()
                        .renderingMode(.template)
                        .frame(width: 24, height: 24)
                    
                    Text("New York City")
                        .style(.titleMedium)
                        .padding(.init(top: .zero, leading: 6, bottom: .zero, trailing:.zero))
                    
                    Image(uiImage: SharedR.images().arrow_drop_down.toUIImage()!)
                        .resizable()
                        .renderingMode(.template)
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
            tabs: viewModel.petTypes.compactMap({ $0.name }),
            selectedTab: $selectedTab.onChange { index in
                // First check if the index is same as already selectedTab or not
                if viewModel.pagingData.first?.type == viewModel.petTypes[selectedTab].name {
                    return
                }
                
                viewModel.paginationState = .loading
                
                // remove all items from the LazyGrid
                viewModel.pagingData.removeAll()
                
                viewModel.dispatch(
                    action: OnPetTypeTabChanged(tabName: viewModel.petTypes[index].name)
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
                ForEach(viewModel.pagingData, id: \.id) { petInfo in
                    PetInfoView(petInfo: petInfo) {
                        viewModel.dispatch(action: NavigateToPetDetail(petInfo: petInfo))
                    }
                    .onAppear {
                        // Very basic and definitely not production ready implementation of pagination
                        // Proper implementation and refinment is required.
                        // Will implement it soon as I keep learning SwiftUI
                        let data = viewModel.pagingData
                        
                        let thresholdIndex = data.index(data.endIndex, offsetBy: -5)
                        
                        if data.firstIndex(where: { $0.id == petInfo.id }) == thresholdIndex &&
                            viewModel.paginationState != .loading {
                            viewModel.paginationState = .loading
                            viewModel.dispatch(action: LoadPetListNextPage())
                        }
                    }
                }
            }
            
            ProgressView()
                .padding(.init(top: 32, leading: .zero, bottom: 64, trailing: .zero))
                .opacity(viewModel.paginationState == .loading ? 1 : 0)
        }
        .background(Color.background)
    }
}

struct HomeScreen_Previews: PreviewProvider {
    static var previews: some View {
        HomeScreen()
    }
}
