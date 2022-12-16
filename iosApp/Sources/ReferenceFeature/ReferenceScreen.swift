//
//  ReferenceScreen.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import KanaFeature
import JlptFeature
import KanjiFeature
import Utils

enum Reference {
    case kana
    case kanji
    case jlpt
}

struct ReferenceScreen: View {

    @State var selectedReference: Reference?

    var body: some View {
        ZStack {
            VStack {
                VStack(spacing: 16) {
                    NavigationLink(tag: .kana, selection: self.$selectedReference) {
                        KanaResourceScreen()
                    } label: {
                        ReferenceRow(title: "Kana", description: "Hiragana & Katakana", color: Color("#FF784F"))
                    }

                    NavigationLink(tag: .kanji, selection: self.$selectedReference) {
                        KanjiResourceScreen()
                    } label: {
                        ReferenceRow(title: "Kanji", description: "Catalog of Kanji characters", color: Color("#db9d47"))
                    }
                    .isDetailLink(true)

                    NavigationLink(tag: .jlpt, selection: self.$selectedReference) {
                        JlptResourceScreen()
                    } label: {
                        ReferenceRow(title: "JLPT", description: "Vocabulary resources for Japanese Language Proficiency Test", color: Color("#3185fc"))
                    }
                    .isDetailLink(true)
                }
                .padding()

                Spacer()
            }
        }
    }
}
