//
//  JishoApp.swift
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import DownloadFeature

@main
struct JishoApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    var body: some Scene {
        WindowGroup {
            DownloadScreen()
        }
    }
}
