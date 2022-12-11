//
//  KanjiGridItem.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI

struct KanjiGridItem: View {
    
    let kanji: String
    
    var body: some View {
        ZStack {
            Text(self.kanji)
                .font(.title3)
                .fontWeight(.semibold)
                .foregroundColor(Color.Neomorph.text)
        }
        .frame(width: 56, height: 56)
        .neomorph()
    }
}

struct KanjiGridItemPreview: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            KanjiGridItem(kanji: "楽")
        }
    }
}
