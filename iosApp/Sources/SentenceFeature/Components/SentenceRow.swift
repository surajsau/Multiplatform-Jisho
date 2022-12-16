//
//  SentenceRow.swift
//  
//
//  Created by surajsau on 2022/12/11.
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

                Text(self.sentence.english)
                    .font(.body)
                    .multilineTextAlignment(.leading)
                    .opacity(0.6)
            }

            Spacer()
        }
        .padding()
    }
}

struct SentenceRowPreview: PreviewProvider {

    static var previews: some View {
        ZStack {
            SentenceRow(sentence: SentenceResult(id: 1, japanese: "私の時計はきちんと動いている。", english: "My watch is running all right."))
        }
    }
}
