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
                
                VStack(spacing: 16) {
                    NavigationLink {
                        
                    } label: {
                        ReferenceRow(title: "Kana", description: "Hiragana & Katakana", color: Color(0xFF784F))
                    }
                    
                    NavigationLink {
                        KanjiResourceScreen()
                    } label: {
                        ReferenceRow(title: "Kanji", description: "Catalog of Kanji characters", color: Color(0xdb9d47))
                    }
                    
                    NavigationLink {
                        
                    } label: {
                        ReferenceRow(title: "JLPT", description: "Vocabulary resources for Japanese Language Proficiency Test", color: Color(0x3185fc))
                    }
                }
                .padding()
                
                Spacer()
            }
        }
        .navigationBarBackButtonHidden(true)
        .navigationBarHidden(true)
    }
}
