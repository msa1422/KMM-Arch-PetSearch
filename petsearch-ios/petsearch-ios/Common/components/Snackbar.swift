//
//  Snackbar.swift
//  petsearch-ios
//
//  Created by Mohammed Sané on 27/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Shared

struct Snackbar: View {
    
    @Environment(\.colorScheme) private var colorScheme
    
    @Binding var isShowing: Bool
    private let presenting: AnyView
    
    private let text: String
    private let snackBar: MessageType.SnackBar?
    
    @State private var isActionTapped: Bool?
    private var isBeingDismissedByAction: Bool {
        snackBar?.action != nil
    }
    
    init<Presenting>(
        isShowing: Binding<Bool>,
        presenting: Presenting,
        text: String,
        snackBar: MessageType.SnackBar?
    ) where Presenting: View {
        self._isShowing = isShowing
        self.presenting = AnyView(presenting)
        self.text = text
        self.snackBar = snackBar
    }
    
    var body: some View {
        GeometryReader { proxy in
            ZStack(alignment: .center) {
                self.presenting
                VStack {
                    Spacer()
                    if self.isShowing {
                        HStack {
                            Text(self.text)
                                .style(.bodyMedium)
                                .foregroundColor(.surface(colorScheme))
                            
                            Spacer()
                            
                            if let action = self.snackBar?.action {
                                Text(action)
                                    .style(.titleMedium)
                                    //.foregroundColor(self.colorScheme == .light ? .white : .black)
                                    .foregroundColor(.surface(colorScheme))
                                    .onTapGesture {
                                        self.isActionTapped = true
                                        self.snackBar?.onAction()
                                        withAnimation {
                                            self.isShowing = false
                                        }
                                    }
                            }
                        }
                        .padding()
                        .shadow(radius: 8)
                        //.background(self.colorScheme == .light ? Color.black : Color.white)
                        .background(Color.onSurface(colorScheme))
                        .cornerRadius(8)
                        .padding(16)
                        .transition(.move(edge: .bottom).combined(with: .opacity))
                        .animation(.default)
                        .onAppear {
                            guard !self.isBeingDismissedByAction else { return }
                            DispatchQueue.main.asyncAfter(deadline: .now() + 4) {
                                withAnimation {
                                    self.isShowing = false
                                }
                            }
                        }
                        .onDisappear {
                            guard !(self.isActionTapped ?? true) else { return }
                            DispatchQueue.main.asyncAfter(deadline: .now()) {
                                snackBar?.onDismiss()
                            }
                        }
                    }
                }
            }
        }
    }
}

extension View {
    func snackBar(
        isShowing: Binding<Bool>,
        text: String,
        snackBar: MessageType.SnackBar?
    ) -> some View {
        Snackbar(
            isShowing: isShowing,
            presenting: self,
            text: text,
            snackBar: snackBar
        )
    }
}
