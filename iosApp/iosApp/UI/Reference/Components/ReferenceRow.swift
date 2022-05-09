//
//  Row.swift
//  iosApp
//

import SwiftUI

struct ReferenceRow: View {
    
    let title: String
    let description: String
    let color: Color
    
    var body: some View {
        HStack(alignment: .center) {
            Circle()
                .fill(self.color)
                .frame(maxHeight: .infinity)
                .padding()
                
            VStack(alignment: .leading) {
                Text(self.title)
                    .font(.title2)
                    .fontWeight(.semibold)
                    .foregroundColor(Color.Neomorph.text)
                
                Text(self.description)
                    .font(.title3)
                    .foregroundColor(Color.Neomorph.text)
            }
            
            Spacer()
        }
        .padding()
        .neomorph()
        .frame(height: 72)
    }
}

struct ReferenceRowPreview: PreviewProvider {
    static var previews: some View {
        ReferenceRow(title: "Kana", description: "Japanese syllables (Hiragana & Katakana)", color: Color.blue)
    }
}
