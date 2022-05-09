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
        HStack {
            Circle()
                .fill(self.color)
                .frame(width: 48, height: 48)
                .padding()
                
            VStack(alignment: .leading) {
                
                Text(self.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                    .foregroundColor(Color.Neomorph.text)
                
                Text(self.description)
                    .font(.caption)
                    .foregroundColor(Color.Neomorph.text)
            }
            
            Spacer()
        }
        .neomorph()
    }
}

struct ReferenceRowPreview: PreviewProvider {
    static var previews: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)
            
            ReferenceRow(title: "Kana", description: "Japanese syllables (Hiragana & Katakana)", color: Color.blue)
                .padding()
        }
    }
}
