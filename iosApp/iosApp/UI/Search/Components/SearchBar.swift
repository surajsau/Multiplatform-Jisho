//
//  SearchBar.swift
//  iosApp
//
//  Created by Suraj GP on 25/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared
import Neumorphic

struct SearchBar: View {
    
    @Binding var text: String
    
    @State private var isEditing: Bool = false
        
    var body: some View {
        HStack {
            HStack {
                Image(systemName: "magnifyingglass").font(Font.body.weight(.bold))

                TextField("search", text: $text, onEditingChanged: { _ in
                    withAnimation {
                        self.isEditing = true
                    }
                })
                
                Button(action: {
                    self.text = ""
                }) {
                    Image(systemName: "xmark.circle.fill").opacity(self.text.isEmpty ? 0 : 1)
                }
            }
            .padding()
            .background(
                RoundedRectangle(cornerRadius: 20).fill(Color.Neomorph.main)
                    .softInnerShadow(RoundedRectangle(cornerRadius: 20), darkShadow: Color.Neomorph.darkShadow, lightShadow: Color.Neomorph.lightShadow, spread: 0.5, radius: 2)
            )
            
            if self.isEditing  {
                Button("Cancel") {
                    UIApplication.shared.endEditing(true)
                    self.text = ""
                    
                    withAnimation {
                        self.isEditing = false
                    }
                }
                .foregroundColor(Color(.systemBlue))
            }
        }
        .padding(.horizontal)
    }
}
