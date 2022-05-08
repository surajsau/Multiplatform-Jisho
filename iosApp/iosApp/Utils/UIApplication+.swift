//
//  UIApplication+.swift
//  iosApp
//
//  Created by Suraj GP on 29/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import UIKit

extension UIApplication {
    
    func endEditing(_ force: Bool) {
        let scenes = UIApplication.shared.connectedScenes
        guard let windowScene = scenes.first as? UIWindowScene else {
            return
        }
        
        windowScene.windows.first?.endEditing(force)
    }
}
