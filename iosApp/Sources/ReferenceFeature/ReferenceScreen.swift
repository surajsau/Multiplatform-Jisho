//
//  ReferenceScreen.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI

enum Reference {
    case Kana
    case Kanji
    case Jlpt
}

struct ReferenceScreen: View {
   
    @State var selectedReference: Reference? = nil
    
    var body: some View {
        ZStack {
            VStack {
                VStack(spacing: 16) {
                    NavigationLink(tag: .Kana, selection: self.$selectedReference) {
                        KanaResourceScreen()
                    } label: {
                        ReferenceRow(title: "Kana", description: "Hiragana & Katakana", color: Color(0xFF784F))
                    }
                    
                    NavigationLink(tag: .Kanji, selection: self.$selectedReference) {
                        KanjiResourceScreen()
                    } label: {
                        ReferenceRow(title: "Kanji", description: "Catalog of Kanji characters", color: Color(0xdb9d47))
                    }
                    .isDetailLink(true)
                    
                    NavigationLink(tag: .Jlpt, selection: self.$selectedReference) {
                        JlptResourceScreen()
                    } label: {
                        ReferenceRow(title: "JLPT", description: "Vocabulary resources for Japanese Language Proficiency Test", color: Color(0x3185fc))
                    }
                    .isDetailLink(true)
                }
                .padding()
                
                Spacer()
            }
        }
    }
}
