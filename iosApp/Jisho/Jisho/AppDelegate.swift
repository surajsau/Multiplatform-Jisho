//
//  AppDelegate.swift
//
//  Created by surajsau on 2022/12/15.
//

import Foundation
import UIKit
import shared
import DownloadFeature
import FirebaseCore

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        FirebaseApp.configure()
        KoinModuleKt.doInitKoin(downloadManager: NativeDownloadManager() as! DownloadManager)
        return true
    }
}
