//
//  Color+.swift
//  iosApp
//
//  Created by Suraj GP on 02/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

public extension Color {

    struct Neomorph {
        public static let main = Color("Background")
        public static let lightShadow = Color("LightShadow")
        public static let darkShadow = Color("DarkShadow")
        public static let text = Color("Text")
    }
    
}

public extension Color {
    
    init(_ hex: UInt, alpha: Double) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xFF) / 255,
            green: Double((hex >> 8) & 0xFF) / 255,
            blue: Double(hex & 0xFF)/255,
            opacity: alpha
        )
    }
}
