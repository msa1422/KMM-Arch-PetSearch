//
//  NavRoute.swift
//  ios
//
//  Created by Mohammed Sané on 01/09/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Shared
import SwiftUI
import UIPilot

protocol NavRoute {
    associatedtype T : RouteNavigator
    associatedtype V : View
    
    var destination: NavigationScreen { get }
    
    var content: V { get }
    
    var viewModel: T { get }
}

@MainActor
extension NavRoute {
    func view(pilot: UIPilot<String>, route: String) -> some View {
        var navEventStream: Task<(), Error>? = nil
        
        return content
            .onAppear {
                if let viewModel = self.viewModel as? BaseViewModel<AnyObject, AnyObject, AnyObject> {
                    if navEventStream == nil {
                        if let arguments = extractArguments(from: route) {
                            viewModel.put(args: arguments)
                        }
                        
                        navEventStream = Task {
                            for try await event in viewModel.navigationEvent {
                                onNavEvent(pilot: pilot, event: event)
                            }
                        }
                    }
                }
            }
            .onDisappear {
                navEventStream?.cancel()
                navEventStream = nil
            }
    }
    
    func onNavEvent(pilot: UIPilot<String>, event: NavigationEvent) {
        switch event {
        case let navEvent as NavigationEvent.NavigateToRoute : do {
            if destination.route == navEvent.route {
                break
            }
            
            let currentRoute = appendArguments(to: navEvent.route, from: navEvent.args)
            pilot.push(currentRoute)
        }
            
        case let navEvent as NavigationEvent.NavigateAndPopUpToRoute : do {
            if destination.route == navEvent.route {
                break
            }
            
            let currentRoute = appendArguments(to: navEvent.route, from: navEvent.args)
            pilot.popTo(navEvent.popUpTo, inclusive: true)
            pilot.push(currentRoute)
        }
            
        case let navEvent as NavigationEvent.PopToRoute : do {
            if destination.route == navEvent.staticRoute {
                break
            }
            
            let currentRoute = appendArguments(to: navEvent.staticRoute, from: navEvent.args)
            pilot.popTo(currentRoute, inclusive: false)
        }
            
        case _ as NavigationEvent.NavigateUp : do {
            pilot.pop()
        }
            
        default : break
        }
    }
    
    private func extractArguments(from route: String) -> [String: String]? {
        if destination.args.size == 0 {
            return nil
        }
        
        var arguments: [String: String] = [:]
        
        for index in 0..<destination.args.size {
            if let arg = destination.args.get(index: index) as? String {
                arguments[arg] = route.valueOf(param: arg)
            }
        }
        
        return arguments
    }
    
    private func appendArguments(
        to route: String,
        from arguments: KotlinMutableDictionary<NSString, NSString>?
    ) -> String {
        return arguments?.reduce(route) { result, arg in
            let separator = result == route ? "/?" : "&"
            return "\(result)\(separator)\(arg.key)=\(arg.value)"
        } ?? route
    }
}
