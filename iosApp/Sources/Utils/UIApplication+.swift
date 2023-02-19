//
//  UIApplication+.swift
//  
//
//  Created by surajsau on 2022/12/16.
//

import Foundation
import UIKit

extension UIApplication {

    public func endEditing(_ force: Bool) {
        let scenes = UIApplication.shared.connectedScenes
        guard let windowScene = scenes.first as? UIWindowScene else {
            return
        }

        windowScene.windows.first?.endEditing(force)
    }
}
