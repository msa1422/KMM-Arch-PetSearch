//
//  ContentView.swift
//  ios
//
//  Created by Mohammed Sané on 29/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Shared
import UIPilot
import ToastSwiftUI

struct ContentView: View {
    
    @StateObject var pilot = UIPilot(initial: NavigationScreenKt.HOME_DESTINATION)
    
    @State private var showToast: Bool = false
    @State private var toastMessage: String?
    
    var body: some View {
        
        GeometryReader { proxy in
            ZStack(alignment: .bottom) {
                
                // Main Navigation Controller host
                UIPilotHost(pilot) { route in
                    
                    switch route {
                        
                    case _ where route.starts(with: NavigationScreenKt.HOME_DESTINATION) :
                        HomeRoute().view(pilot: pilot, route: route) { message in
                            handle(the: message)
                        }
                        
                    case _ where route.starts(with: NavigationScreenKt.PET_DETAIL_DESTINATION) :
                        PetDetailRoute().view(pilot: pilot, route: route) { message in
                            handle(the: message)
                        }
                        
                    // Workaround for default case, because Swift only truly verifies that a
                    // switch block is exhaustive when working with enum types
                    default : EmptyView()
                
                    }
                }
                
                // Safe Area Translucent BottomInset
                Rectangle()
                    .fill(Color.white.opacity(0.38))
                    .frame(height: proxy.safeAreaInsets.bottom)
                
            }
            .edgesIgnoringSafeArea(.bottom)
            .toast(
                isPresenting: $showToast,
                message: String(toastMessage?.prefix(120) ?? ""),
                icon: nil,
                backgroundColor: Color.black.opacity(0.9),
                textColor: Color.blue,
                autoDismiss: .after(5),
                onDisappear: { toastMessage = nil }
            )
            
        }
        

        
    }
    
    
    private func handle(the message: ResourceMessage) {
        switch message.messageType {
            
        case _ as MessageType.SnackBar : do {
            // To be implemented
        }
            
        case _ as MessageType.Toast : do {
            toastMessage = message.text
            showToast = true
        }
            
        default: break
        }
    }
    
}


struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
