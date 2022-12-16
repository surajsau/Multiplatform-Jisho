//
//  DownloadScreen.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import Utils
import shared
import HomeFeature

public struct DownloadScreen: View {

    @ObservedObject var nativeVM = NativeViewModel(viewModel: DownloadViewModel())

    public init() {}

    public var body: some View {
        ZStack(alignment: .center) {
            if let state = nativeVM.state {
                if state.downloadFileExists {
                    HomeScreen()
                } else {
                    ProgressView("Loading")
                }
            }
        }
        .onAppear {
            nativeVM.viewModel.doInit()
        }
    }
}
