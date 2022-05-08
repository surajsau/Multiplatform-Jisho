//
//  Kanjis.swift
//  iosApp
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
                .foregroundColor(Color.Neomorph.text)
                .padding(.bottom, 8)
            
            VStack(spacing: 16) {
                ForEach(self.items, id: \.self) { item in
                    HStack(alignment: .center) {
                        Text(item.value)
                            .font(.body)
                            .fontWeight(.medium)
                            .padding(.trailing, 8)
                            .opacity(0.6)
                            .foregroundColor(Color.Neomorph.text)
                        
                        Text(item.meaning)
                            .lineLimit(1)
                            .font(.body)
                            .foregroundColor(Color.Neomorph.text)
                        
                        Spacer()
                    }
                    .padding()
                    .neomorph()
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
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            DetailsKanji(items: self.items)
                .padding()
        }
    }
}
