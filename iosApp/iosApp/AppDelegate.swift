//
//  AppDelegate.swift
//  iosApp
//
//  Created by Suraj GP on 24/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import UIKit
import Firebase
import shared

class AppDelegate: NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        FirebaseApp.configure()
        KoinModuleKt.doInitKoin()
        return true
    }
}
