//
//  NeomorphModifier.swift
//  iosApp
//
//  Created by Suraj GP on 07/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct NeomorphModifier: ViewModifier {
    
    let isCircle: Bool
    let cornerRadius: CGFloat
    
    func body(content: Content) -> some View {
        if self.isCircle {
            content
                .background(
                    Circle()
                        .fill(Color.Neomorph.main)
                        .softOuterShadow(darkShadow: Color.Neomorph.darkShadow, lightShadow: Color.Neomorph.lightShadow, offset: 4)
                )
        } else {
            content
                .background(
                    RoundedRectangle(cornerRadius: self.cornerRadius)
                        .fill(Color.Neomorph.main)
                        .softOuterShadow(darkShadow: Color.Neomorph.darkShadow, lightShadow: Color.Neomorph.lightShadow, offset: 4)
                )
        }
    }
}

extension View {
    
    func neomorph(isCircle: Bool = false, cornerRadius: CGFloat = 8) -> some View {
        modifier(NeomorphModifier(isCircle: isCircle, cornerRadius: cornerRadius))
    }
}
