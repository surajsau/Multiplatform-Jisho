//
//  DownloadScreen.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import HomeFeature
import shared
import SwiftUI
import Utils

public struct DownloadScreen: View {

    @ObservedObject var nativeVM = NativeViewModel(
        viewModel: DownloadViewModel(downloadManager: NativeDownloadManager())
    )

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
    }
}
