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
    @StateObject var pilot = UIPilot(initial: NavigationScreen.homenavscreen.route)
    
    @State private var message: String?
    @State private var messageObserverTask: Task<(), Error>? = nil
    @State private var snackBar: MessageType.SnackBar?
    @State private var showSnackBar: Bool = false
    @State private var showToast: Bool = false
    
    var body: some View {
        // Main Navigation Controller host
        UIPilotHost(pilot) { route in
            switch route {
                
            case _ where route.starts(with: NavigationScreen.homenavscreen.route) :
                HomeRoute().view(pilot: pilot, route: route)
                
            case _ where route.starts(with: NavigationScreen.petdetailnavscreen.route):
                PetDetailRoute().view(pilot: pilot, route: route)
                
            // Workaround for default case, because Swift only truly verifies that a
            // switch block is exhaustive when working with enum types
            default : EmptyView()
            }
        }
        .edgesIgnoringSafeArea(.bottom)
        .toast(
            isPresenting: $showToast,
            message: String(message?.prefix(120) ?? ""),
            icon: nil,
            backgroundColor: Color.onSurface.opacity(0.9),
            textColor: Color.blue,
            autoDismiss: .after(5),
            onDisappear: { message = nil }
        )
        .snackBar(
            isShowing: $showSnackBar,
            text: message ?? "",
            snackBar: snackBar
        )
        .onAppear(perform: startMessageObserver)
        .onDisappear(perform: stopMessageObserver)
    }
}

extension ContentView {
    private func startMessageObserver() {
        if messageObserverTask == nil {
            messageObserverTask = Task {
                for try await message in MessageDeque.shared.invoke() {
                    handle(resource: message)
                }
            }
        }
    }
    
    private func stopMessageObserver() {
        messageObserverTask?.cancel()
        messageObserverTask = nil
    }
    
    private func handle(resource message: ResourceMessage) {
        switch message.messageType {
            
        case let snackBar as MessageType.SnackBar : do {
            if showSnackBar == false {
                self.message = message.text
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
            self.message = message.text
            self.showToast = true
            DispatchQueue.main.asyncAfter(deadline: .now()) {
                Task { try await MessageDeque.shared.dequeue() }
            }
        }
            
        default: break
        }
    }
}

#Preview {
    ContentView()
}
