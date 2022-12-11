//
//  DetailsMeanings.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI
import shared

struct DetailsMeanings: View {
    
    let meanings: [DetailsViewModel.Meaning]
    
    var body: some View {
        
        HStack {
            VStack(alignment: .leading) {
                
                Text("Meanings")
                    .font(.title)
                    .fontWeight(.semibold)
                    .padding(.bottom, 8)
                
                ForEach(self.meanings, id: \.self) { meaning in
                    
                    VStack(alignment: .leading, spacing: 6) {
                        Text(meaning.pos)
                            .font(.body)
                            .opacity(0.6)
                        
                        Text(meaning.value)
                            .font(.body)
                    }
                    .padding(.bottom, 12)
                }
            }

            Spacer()
        }
    }
}

struct PreviewDetailsMeaning: PreviewProvider {
    
    private static let meanings = [
        DetailsViewModel.Meaning(pos: "Intonation", value: "that's too bad"),
        DetailsViewModel.Meaning(pos: "Adverb", value: "with a clatter, with a rattle"),
        DetailsViewModel.Meaning(pos: "Noun", value: "rough (personality, speech, etc.), unreserved, outspoken, boorish, ill-mannered")
    ]
    
    static var previews: some View {
        DetailsMeanings(meanings: self.meanings)
    }
}
