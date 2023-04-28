//
//  ContentView.swift
//  ios
//
//  Created by Mohammed Sané on 29/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import KMPNativeCoroutinesAsync
import Shared
import SwiftUI
import ToastSwiftUI
import UIPilot

struct ContentView: View {
    
    @StateObject var pilot = UIPilot(initial: NavigationScreenKt.HOME_DESTINATION)
    
    @State private var resourceMessageText: String?
    @State private var snackBar: MessageType.SnackBar?
    @State private var showSnackBar: Bool = false
    @State private var showToast: Bool = false
    @State private var messageDequeObserver: Task<(), Error>? = nil
    
    var body: some View {
        // Main Navigation Controller host
        UIPilotHost(pilot) { route in
            switch route {
                
            case _ where route.starts(with: NavigationScreenKt.HOME_DESTINATION) :
                HomeRoute().view(pilot: pilot, route: route)
                
            case _ where route.starts(with: NavigationScreenKt.PET_DETAIL_DESTINATION) :
                PetDetailRoute().view(pilot: pilot, route: route)
                
            // Workaround for default case, because Swift only truly verifies that a
            // switch block is exhaustive when working with enum types
            default : EmptyView()
            }
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
        .onAppear {
            if messageDequeObserver == nil {
                messageDequeObserver = Task {
                    for try await message in asyncSequence(for: MessageDeque.shared.invoke()) {
                        handle(resource: message)
                    }
                }
            }
        }
        .onDisappear {
            messageDequeObserver?.cancel()
            messageDequeObserver = nil
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
                DispatchQueue.main.asyncAfter(deadline: .now() + 5) {
                    Task { try await MessageDeque.shared.dequeue() }
                }
            }
        }
            
        case _ as MessageType.Toast : do {
            resourceMessageText = message.text
            showToast = true
            DispatchQueue.main.asyncAfter(deadline: .now()) {
                Task { try await MessageDeque.shared.dequeue() }
            }
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
