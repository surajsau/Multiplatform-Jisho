//
//  KanjiItem.swift
//  iosApp
//
//  Created by Suraj GP on 09/05/22.
//  Copyright © 2022 orgName. All rights reserved.
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
