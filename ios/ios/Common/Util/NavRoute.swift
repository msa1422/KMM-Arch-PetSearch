//
//  NavRoute.swift
//  ios
//
//  Created by Mohammed Sané on 01/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI
import UIPilot

protocol NavRoute {
    
    associatedtype T : RouteNavigator
    associatedtype V : View
    
    var route: String { get }
    
    var content: V { get }
    
    var viewModel: T { get }
    
    func getArguments() -> Array<String>
    
}


extension NavRoute {
    
    typealias BaseVm = BaseViewModel<AnyObject, AnyObject, AnyObject, AnyObject, AnyObject, AnyObject, AnyObject>
    
    
    func view(pilot: UIPilot<String>, route: String, messenger : @escaping (ResourceMessage) -> Void) -> some View {
                                        
        if let viewModel = self.viewModel as? BaseVm {
            
            viewModel.observeNavigationState().watch { state in
                            
                if state != nil {
                    
                    updateNavigationState(pilot: pilot, navigationState: state!) { newState in
                        viewModel.onNavComplete(state: newState)
                    }
                    
                    if !getArguments().isEmpty {
                        
                        let argsMap = KotlinMutableDictionary<NSString, NSString>()
                        
                        getArguments().forEach { argName in
                            if let arg = route.valueOf(param: argName) {
                                argsMap.setValue(arg, forKey: argName)
                            }
                        }
                        
                        viewModel.updateArgsInState(args: argsMap)
                    }
                }
            }
            
            
            viewModel.observeMessageDeque().watch { message in
                if let msg = message {
                    messenger(msg)
                }
            }
            
        }
    
        return content
        
    }
    
    func updateNavigationState(
        pilot: UIPilot<String>,
        navigationState: NavigationState,
        onComplete : @escaping (NavigationState) -> Void
    ) {
        
        switch navigationState {

        case let navState as NavigationState.NavigateToRoute : do {
                        
            var currentRoute: String = navState.route
            
            if route == currentRoute {
                break
            }
            
            let args: KotlinMutableDictionary<NSString, NSString>? = navState.args
            if args != nil {
                args!.forEach { entry in
                    
                    let key: String = entry.key as? String ?? ""
                    let value: String = entry.value as? String ?? ""
                    
                    currentRoute.replace("{\(key)}", with: value.isEmpty ? "null" : value)
                    
                }
            }
            pilot.push(currentRoute)
            onComplete(NavigationState.Idle())
        }
            
            
        case let navState as NavigationState.NavigateAndPopUpToRoute : do {
            
            var currentRoute: String = navState.route
            
            if route == currentRoute {
                break
            }
            
            let args: KotlinMutableDictionary<NSString, NSString>? = navState.args
            if args != nil {
                args!.forEach { entry in
                    
                    let key: String = entry.key as? String ?? ""
                    let value: String = entry.value as? String ?? ""
                    
                    currentRoute.replace("{\(key)}", with: value.isEmpty ? "null" : value)
                    
                }
            }
            
            // Add extension with push and popUpTo implementation in UIPilot
            pilot.push(currentRoute)
            onComplete(NavigationState.Idle())
        }

            
        case let navState as NavigationState.PopToRoute : do {
            
            var currentRoute: String = navState.staticRoute
            
            if route == currentRoute {
                break
            }
            
            let args: KotlinMutableDictionary<NSString, NSString>? = navState.args
            if args != nil {
                args!.forEach { entry in
                    
                    let key: String = entry.key as? String ?? ""
                    let value: String = entry.value as? String ?? ""
                    
                    currentRoute.replace("{\(key)}", with: value.isEmpty ? "null" : value)
                    
                }
            }
            pilot.push(currentRoute)
            onComplete(NavigationState.Idle())
        }
            
            
        case _ as NavigationState.NavigateUp : do {
            pilot.pop()
            onComplete(NavigationState.Idle())
        }
            
            
        case _ as NavigationState.Idle: fallthrough
        default : break
            
        }
        
    }
        
    
    // For some reason this implementation doesn't work
    // First time Navigate from Home to PetDetail works
    // Navigate back from PetDetail to Home works
    // But then Navigate from Home to PetDetail again fails
//    func view(pilot: UIPilot<String>, route: String, messenger : @escaping (ResourceMessage) -> Void) -> some View {
//
//        @State var navStateObserver: Closeable? = nil
//        @State var messageDequeObserver: Closeable? = nil
//
//        return content
//            .onAppear {
//
//                if let viewModel = self.viewModel as? BaseVm {
//
//                    if navStateObserver == nil {
//                        navStateObserver = viewModel.observeNavigationState().watch { state in
//
//                            if state != nil {
//
//                                updateNavigationState(pilot: pilot, navigationState: state!) { newState in
//                                    viewModel.onNavComplete(state: newState)
//                                }
//
//                                if !getArguments().isEmpty {
//
//                                    let argsMap = KotlinMutableDictionary<NSString, NSString>()
//
//                                    getArguments().forEach { argName in
//                                        if let arg = route.valueOf(param: argName) {
//                                            argsMap.setValue(arg, forKey: argName)
//                                        }
//                                    }
//
//                                    viewModel.updateArgsInState(args: argsMap)
//                                }
//                            }
//                        }
//                    }
//
//
//                    if messageDequeObserver == nil {
//                        messageDequeObserver = viewModel.observeMessageDeque().watch { message in
//                            if let msg = message {
//                                messenger(msg)
//                            }
//                        }
//                    }
//
//                }
//
//            }
//            .onDisappear {
//                navStateObserver?.close()
//                navStateObserver = nil
//
//                messageDequeObserver?.close()
//                messageDequeObserver = nil
//            }
//
//    }

}
