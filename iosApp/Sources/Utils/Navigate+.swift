//
//  Navigate+.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI

extension View {

    func navigate(whenTrue toggle: Binding<Bool>) -> some View {
        NavigationLink(
            destination: self,
            isActive: toggle
        ) {
            EmptyView()
        }.hidden()
    }
}
