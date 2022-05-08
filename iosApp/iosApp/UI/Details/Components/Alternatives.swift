//
//  Alternatives.swift
//  iosApp
//

import SwiftUI
import shared

struct DetailsAlternatives: View {
    
    let alternative: String
    
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text("Alternatives")
                    .font(.title)
                    .fontWeight(.semibold)
                    .foregroundColor(Color.Neomorph.text)
                    .padding(.bottom, 8)

                Text(self.alternative)
                    .font(.body)
                    .foregroundColor(Color.Neomorph.text)
            }
            
            Spacer()
        }
    }
}

struct DetailsAlternativePreview: PreviewProvider {
    static var previews: some View {
        DetailsAlternatives(alternative: "楽しい, たのしい")
    }
}
