//
//  SearchBar.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import Utils
import UIKit

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
