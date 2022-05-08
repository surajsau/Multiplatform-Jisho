//
//  DownloadScreen.swift
//  iosApp
//
//  Created by Suraj GP on 28/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct DownloadScreen: View {
    
    @ObservedObject var downloadManager = DownloadManager()
    
    var body: some View {
        ZStack(alignment: .center) {
            if self.downloadManager.doesDatabaseExist {
                HomeScreen()
            } else {
                ProgressView("Loading")
            }
        }
        .onAppear {
            try? self.downloadManager.checkIfDatabaseExists()
        }
        .onReceive(self.downloadManager.$doesDatabaseExist) {
            if !$0 {
                Task {
                    try? await self.downloadManager.download()
                    
                    try? await self.downloadManager.extract()
                }
            }
        }
    }
    
    
}
