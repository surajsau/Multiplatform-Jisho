//
//  ListScreen.swift
//  iosApp
//

import SwiftUI

struct ReferenceScreen: View {
    
    var body: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            VStack {
                
                AppTopBar(navigateUpIcon: nil, title: "References")
                    .padding()
                
                VStack {
                    
                    ReferenceRow(title: "Kana", description: "Hiragana & Katakana", color: "Vocabulary resources for Japanese Language Proficiency Test")
                }
                .padding()
            }
        }
    }
}
