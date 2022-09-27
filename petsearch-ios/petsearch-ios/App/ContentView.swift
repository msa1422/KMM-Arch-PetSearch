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
    
    @State private var resourceMessageText: String?
    @State private var snackBar: MessageType.SnackBar?
    @State private var showSnackBar: Bool = false
    @State private var showToast: Bool = false
    
    var body: some View {
        
        GeometryReader { proxy in
            ZStack(alignment: .bottom) {
                
                // Main Navigation Controller host
                UIPilotHost(pilot) { route in
                    
                    switch route {
                        
                    case _ where route.starts(with: NavigationScreenKt.HOME_DESTINATION) :
                        HomeRoute().view(pilot: pilot, route: route) { message in
                            handle(resource: message)
                        }
                        
                    case _ where route.starts(with: NavigationScreenKt.PET_DETAIL_DESTINATION) :
                        PetDetailRoute().view(pilot: pilot, route: route) { message in
                            handle(resource: message)
                        }
                        
                    // Workaround for default case, because Swift only truly verifies that a
                    // switch block is exhaustive when working with enum types
                    default : EmptyView()
                
                    }
                }
                
                // Safe Area Translucent BottomInset
                Rectangle()
                    .fill(Color.surface.opacity(0.38))
                    .frame(height: proxy.safeAreaInsets.bottom)
            }
            .edgesIgnoringSafeArea(.bottom)
            .toast(
                isPresenting: $showToast,
                message: String(resourceMessageText?.prefix(120) ?? ""),
                icon: nil,
                backgroundColor: Color.onSurface.opacity(0.9),
                textColor: Color.blue,
                autoDismiss: .after(5),
                onDisappear: { resourceMessageText = nil }
            )
            .snackBar(
                isShowing: $showSnackBar,
                text: resourceMessageText ?? "",
                snackBar: snackBar
            )
        }
    }
    
    private func handle(resource message: ResourceMessage) {
        switch message.messageType {
            
        case let snackBar as MessageType.SnackBar : do {
            if showSnackBar == false {
                resourceMessageText = message.text
                self.snackBar = snackBar
                withAnimation {
                    showSnackBar.toggle()
                }
                message.dequeueCallback()
            }
        }
            
        case _ as MessageType.Toast : do {
            resourceMessageText = message.text
            showToast = true
            message.dequeueCallback()
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
