//
//  ObservableViewModel.swift
//  iosApp
//
//  Created by Suraj GP on 25/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import KMPNativeCoroutinesCombine
import Combine
import SwiftUI

class Dispatcher<State: VMState, Intent: VMIntent, Effect: VMEffect>: ObservableObject {
    
    private let viewModel: BaseViewModel<State, Intent, Effect>
    
    @Published var state: State? = nil
    @Published var effect: Effect? = nil
    
    private var cancellables: [AnyCancellable] = []
    
    init(viewModel: BaseViewModel<State, Intent, Effect>) {
        self.viewModel = viewModel
        
        self.cancellables.append(
            createPublisher(for: self.viewModel.stateNative)
                .receive(on: RunLoop.main)
                .sink { _ in } receiveValue: { value in
                    guard let state = value as? State else {
                        return
                    }
                    self.state = state
                }
        )
        
        self.cancellables.append(
            createPublisher(for: self.viewModel.effectNative)
                .receive(on: RunLoop.main)
                .sink { _ in } receiveValue: { value in
                    guard let effect = value as? Effect else {
                        return
                    }
                    self.effect = effect
                }
        )
    }
    
    func with(intent: Intent) {
        self.viewModel.onIntent(intent: intent)
    }
    
    deinit {
        self.cancellables.forEach { $0.cancel() }
        self.viewModel.onCleared()
    }
}
