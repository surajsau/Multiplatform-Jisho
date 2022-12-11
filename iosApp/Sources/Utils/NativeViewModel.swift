//
//  NativeViewModel.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import Foundation
import shared
import KMPNativeCoroutinesCombine
import Combine
import SwiftUI

public class NativeViewModel<S: UiState, VM: BaseViewModel<S>>: ObservableObject {

    public let viewModel: VM

    @Published public var state: S? = nil
    
    private var cancellables: [AnyCancellable] = []
    
    public init(viewModel: VM) {
        self.viewModel = viewModel
        
        cancellables.append(
            createPublisher(for: viewModel.stateNative)
                .receive(on: RunLoop.main)
                .sink(receiveCompletion: { _ in }, receiveValue: { state in
                    self.state = state
                })
        )
    }
    
    deinit {
        cancellables.forEach { $0.cancel() }
        viewModel.onCleared()
    }
}
