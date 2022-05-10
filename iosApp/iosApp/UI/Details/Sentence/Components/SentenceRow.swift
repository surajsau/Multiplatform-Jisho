//
//  SentenceRow.swift
//  iosApp
//
//  Created by Suraj GP on 07/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct SentenceRow: View {
    
    let sentence: SentenceResult
    
    var body: some View {
        HStack {
            VStack(alignment: .leading, spacing: 4) {
                Text(self.sentence.japanese)
                    .font(.body)
                    .fontWeight(.semibold)
                    .multilineTextAlignment(.leading)
                    .foregroundColor(Color.Neomorph.text)
                
                Text(self.sentence.english)
                    .font(.body)
                    .multilineTextAlignment(.leading)
                    .foregroundColor(Color.Neomorph.text)
                    .opacity(0.6)
            }
            
            Spacer()
        }
        .padding()
        .neomorph()
    }
}

struct SentenceRowPreview: PreviewProvider {
    
    static var previews: some View {
        ZStack {
            Color.Neomorph.main
                .edgesIgnoringSafeArea(.all)

            SentenceRow(sentence: SentenceResult(id: 1, japanese: "私の時計はきちんと動いている。", english: "My watch is running all right."))
                .padding()
        }
    }
}
