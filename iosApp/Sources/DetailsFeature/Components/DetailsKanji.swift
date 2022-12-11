//
//  DetailsKanji.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import shared

struct DetailsKanji: View {
    
    let items: [DetailsViewModel.KanjiItem]
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("Kanji in the word")
                .font(.title)
                .fontWeight(.semibold)
                .padding(.bottom, 8)
            
            VStack(spacing: 16) {
                ForEach(self.items, id: \.self) { item in
                    HStack(alignment: .center) {
                        Text(item.value)
                            .font(.body)
                            .fontWeight(.medium)
                            .padding(.trailing, 8)
                            .opacity(0.6)
                        
                        Text(item.meaning)
                            .lineLimit(1)
                            .font(.body)
                        
                        Spacer()
                    }
                    .padding()
                }
            }
        }
    }
}

struct DetailKanjiPreview: PreviewProvider {
    
    private static let items = [
        DetailsViewModel.KanjiItem(value: "鯵", meaning: "horse mackerel"),
        DetailsViewModel.KanjiItem(value: "暗", meaning: "darkness, shade, informal, grow dark, be blinded"
        ),
        DetailsViewModel.KanjiItem(value: "安", meaning: "relax, cheap, low, quiet, contentded, peaceful"
        )
    ]
    static var previews: some View {
        
        ZStack {
            DetailsKanji(items: self.items)
                .padding()
        }
    }
}
