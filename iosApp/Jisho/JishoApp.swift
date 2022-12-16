//
//  JishoApp.swift
//
//  Created surajsau on 2022/12/15.
//

import SwiftUI

@main
struct JishoApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        WindowGroup {
            DownloadScreen()
        }
    }
}
