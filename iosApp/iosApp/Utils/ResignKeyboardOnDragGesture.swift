//
//  ResignKeyboardOnDragGesture.swift
//  iosApp
//
//  Created by Suraj GP on 29/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct ResignKeyboardOnDragGesture: ViewModifier {
    private let gesture = DragGesture().onChanged { _ in
        UIApplication.shared.endEditing(true)
    }
    
    func body(content: Content) -> some View {
        content.gesture(self.gesture)
    }
}

extension View {
    
    func resignKeyboardOnDragGesture() -> some View {
        return modifier(ResignKeyboardOnDragGesture())
    }
}
